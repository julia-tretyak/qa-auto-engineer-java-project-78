package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    private Validator v;

    @BeforeEach
    void setUp() {
        v = new Validator();
    }

    // ====== Тесты для StringSchema ======
    @Test
    void testStringSchemaBasic() {
        var schema = v.string();
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testStringSchemaRequired() {
        var schema = v.string().required();
        assertTrue(schema.isValid("hello"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testStringSchemaMinLength() {
        var schema = v.string().minLength(3);
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid("abc"));
        assertFalse(schema.isValid("hi"));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testStringSchemaContains() {
        var schema = v.string().contains("world");
        assertTrue(schema.isValid("hello world"));
        assertTrue(schema.isValid("world"));
        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testStringSchemaAllChecks() {
        var schema = v.string()
                .required()
                .minLength(5)
                .contains("test");

        assertTrue(schema.isValid("this is test string"));
        assertFalse(schema.isValid("test"));
        assertFalse(schema.isValid("this is not"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testStringSchemaChaining() {
        var schema = v.string().minLength(10).minLength(4);
        assertTrue(schema.isValid("Hexlet"));
    }

    @Test
    void testStringSchemaRequiredWithContains() {
        var schema = v.string().required().contains("hex");
        assertTrue(schema.isValid("hexlet"));
        assertTrue(schema.isValid("this is hex"));
        assertFalse(schema.isValid("hello"));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
    }

    // ====== Тесты для NumberSchema ======
    @Test
    void testNumberSchemaBasic() {
        var schema = v.number();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(-10));
        assertTrue(schema.isValid(0));
    }

    @Test
    void testNumberSchemaRequired() {
        var schema = v.number().required();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(-10));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testNumberSchemaPositive() {
        var schema = v.number().positive();
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testNumberSchemaPositiveWithRequired() {
        var schema = v.number().required().positive();
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));
    }

    @Test
    void testNumberSchemaRange() {
        var schema = v.number().range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertTrue(schema.isValid(null));
    }

    @Test
    void testNumberSchemaAllChecks() {
        var schema = v.number()
                .required()
                .positive()
                .range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(7));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(null));
    }

    @Test
    void testNumberSchemaChaining() {
        var schema = v.number().range(1, 10).range(5, 15);
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(3));
    }
}
