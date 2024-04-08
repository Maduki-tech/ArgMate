package de.schlueter.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ObjectToJsonConverterTest {
    @Test
    public void convertToJson_test() {
        ImpleTest impleTest = new ImpleTest("john", "doe", "25", "1234");
        ObjectToJsonConverter sut = new ObjectToJsonConverter();

        String jsonString = sut.convertToJson(impleTest);

        assertEquals("{\"personAge\":\"25\",\"firstName\":\"John\",\"lastName\":\"Doe\"}",
                     jsonString);
    }
}
