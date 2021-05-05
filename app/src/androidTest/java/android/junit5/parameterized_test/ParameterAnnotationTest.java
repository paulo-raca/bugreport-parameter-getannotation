package android.junit5.parameterized_test;

import androidx.annotation.Nullable;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Parameter;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ParameterAnnotationTest {
    @Test
    public void test() {
    }

    // Works!
    @ParameterizedTest
    @ValueSource(strings = {"a", "b"})
    @Order(1)
    void testParameterized(String value) {
    }

    // Works
    @Test
    @Order(2)
    public void testGetParameterAnnotationEmpty() throws Exception {
        for (Parameter parameter : getClass().getDeclaredMethod("testParameterized", String.class).getParameters()) {
            parameter.getDeclaredAnnotation(Nullable.class);
        }
    }

    // Crashes
    @ParameterizedTest
    @ValueSource(strings = {"a", "b"})
    @Order(3)
    void testParameterizedWithAnnotation(@Nullable String value) {
    }


    // Crashes
    @Test
    @Order(4)
    public void testGetParameterAnnotationNotEmpty() throws Exception {
        for (Parameter parameter : getClass().getDeclaredMethod("testParameterizedWithAnnotation", String.class).getParameters()) {
            parameter.getDeclaredAnnotation(Nullable.class);
        }
    }
}