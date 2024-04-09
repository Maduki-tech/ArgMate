package de.schlueter.annotations;

import java.lang.reflect.Field;

// TODO: This still need to be tested and a interface should be created
public class ArgumentImpl {

    private String getArgument(Object obj) throws Exception{
        Class<?> clazz = obj.getClass();
        StringBuilder argument = new StringBuilder();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Argument.class)) {
                argument.append(getKey(field)).append(" ").append(field.get(obj)).append(" ");
            }
        }
        return argument.toString();
    }

    private String getKey(Field field){
        Argument argument = field.getAnnotation(Argument.class);
        if (argument.shortName().isEmpty()) {
            return "--" + argument.longName();
        } else {
            return "-" + argument.shortName();
        }
    }
}
