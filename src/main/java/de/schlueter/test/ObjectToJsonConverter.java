package de.schlueter.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ObjectToJsonConverter
 */
public class ObjectToJsonConverter {
    private void CheckIfSerializable(Object obj) {
        if (Objects.isNull(obj)) {
            throw new IllegalArgumentException("The object to serialize must not be null.");
        }

        Class<?> clazz = obj.getClass();

        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new IllegalArgumentException(
                "The object to serialize must be annotated with JsonSerializable.");
        }
    }

    private void initializeObject(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(obj);
            }
        }
    }

    private String getJsonString(Object obj) throws Exception {
        Class<?> clazz = obj.getClass();
        Map<String, String> jsonElementMap = new HashMap<>();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementMap.put(getKey(field), (String)field.get(obj));
            }
        }

        String jsonString =
            jsonElementMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field){
        String value = field.getAnnotation(JsonElement.class).key();
        return value.isEmpty() ? field.getName() : value;
    }

    public String convertToJson(Object obj) throws IllegalArgumentException {
        try {
            CheckIfSerializable(obj);
            initializeObject(obj);
            return getJsonString(obj);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
