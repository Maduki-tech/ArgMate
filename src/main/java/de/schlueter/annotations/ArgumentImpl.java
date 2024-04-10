package de.schlueter.annotations;

import java.lang.reflect.Field;

public class ArgumentImpl implements ArgumentInterface {

    private Object obj;

    public ArgumentImpl(Object obj){
        this.obj = obj;
    }
    @Override
    public void parseArgs(String[] args) throws Exception {
        System.out.println("Parsing arguments");
        Class<?> clazz = obj.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Argument.class)) {
                System.out.println("Found argument");
                field.setAccessible(true);
                String value = parseForArgumentValue(field, args);
                field.set(obj, value);
            }
        }

    }

    private String parseForArgumentValue(Field field, String[] args){
        String shortName = field.getAnnotation(Argument.class).shortName();
        String longName = field.getAnnotation(Argument.class).longName();
        String value = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-" + shortName) || args[i].equals("--" + longName)) {
                value = args[i + 1];
                break;
            }
        }

        return value;
    }
}
