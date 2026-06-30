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
    void testStringSchema() {
        var schema = v.string();
        assertTrue(schema.isValid("hello"));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
    }
}
