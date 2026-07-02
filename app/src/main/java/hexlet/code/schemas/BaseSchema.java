package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new HashMap<>();
    protected boolean required = false;

    public boolean isValid(T value) {
        if (required && value == null) {
            return false;
        }
        if (!required && value == null) {
            return true;
        }
        return checks.values().stream().allMatch(p -> p.test(value));
    }
}