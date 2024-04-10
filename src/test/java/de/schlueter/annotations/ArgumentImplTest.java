package de.schlueter.annotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ArgumentImplTest {

    private ArgumentImpl argumentImpl;
    private Object obj;

    @BeforeEach
    public void setup() {
        obj = Mockito.mock(Object.class);
        argumentImpl = new ArgumentImpl(obj);
    }

    @Test
    public void parseArgs_correctlySetsFieldValues() throws Exception {
        Field field = Mockito.mock(Field.class);
        when(field.isAnnotationPresent(Argument.class)).thenReturn(true);
        when(field.getAnnotation(Argument.class)).thenReturn(new Argument() {
            public String shortName() { return "n"; }
            public String longName() { return "name"; }

            @Override
            public String description() {
                return null;
            }

            public Class<? extends Annotation> annotationType() { return Argument.class; }
        });
        when(obj.getClass().getDeclaredFields()).thenReturn(new Field[]{field});

        argumentImpl.parseArgs(new String[]{"-n", "John"});

        Mockito.verify(field).set(obj, "John");
    }

    @Test
    public void parseArgs_handlesMissingArgumentValue() throws Exception {
        Field field = Mockito.mock(Field.class);
        when(field.isAnnotationPresent(Argument.class)).thenReturn(true);
        when(field.getAnnotation(Argument.class)).thenReturn(new Argument() {
            public String shortName() { return "n"; }
            public String longName() { return "name"; }

            @Override
            public String description() {
                return null;
            }

            public Class<? extends Annotation> annotationType() { return Argument.class; }
        });
        when(obj.getClass().getDeclaredFields()).thenReturn(new Field[]{field});

        argumentImpl.parseArgs(new String[]{"-n"});

        Mockito.verify(field).set(obj, null);
    }

    @Test
    public void parseArgs_handlesArgumentNotFound() throws Exception {
        Field field = Mockito.mock(Field.class);
        when(field.isAnnotationPresent(Argument.class)).thenReturn(true);
        when(field.getAnnotation(Argument.class)).thenReturn(new Argument() {
            public String shortName() { return "n"; }
            public String longName() { return "name"; }

            @Override
            public String description() {
                return null;
            }

            public Class<? extends Annotation> annotationType() { return Argument.class; }
        });
        when(obj.getClass().getDeclaredFields()).thenReturn(new Field[]{field});

        argumentImpl.parseArgs(new String[]{"-m", "John"});

        Mockito.verify(field).set(obj, null);
    }
}