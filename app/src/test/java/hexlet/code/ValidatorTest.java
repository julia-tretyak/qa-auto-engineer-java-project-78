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
        assertTrue(schema.isValid(null)); // null валиден, если required не вызван
    }

    @Test
    void testStringSchemaContains() {
        var schema = v.string().contains("world");
        assertTrue(schema.isValid("hello world"));
        assertTrue(schema.isValid("world"));
        assertFalse(schema.isValid("hello"));
        assertTrue(schema.isValid(null)); // null валиден, если required не вызван
    }

    @Test
    void testStringSchemaAllChecks() {
        var schema = v.string()
                .required()
                .minLength(5)
                .contains("test");

        assertTrue(schema.isValid("this is test string"));
        assertFalse(schema.isValid("test")); // слишком короткая
        assertFalse(schema.isValid("this is not")); // не содержит "test"
        assertFalse(schema.isValid("")); // required
        assertFalse(schema.isValid(null)); // required
    }

    @Test
    void testStringSchemaChaining() {
        var schema = v.string().minLength(10).minLength(4);
        assertTrue(schema.isValid("Hexlet")); // последний вызов minLength(4) перетирает предыдущий
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
}
