package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class StringSchema {
    private Map<String, Predicate<String>> checks = new HashMap<>();
    private boolean required = false;

    public StringSchema required() {
        this.required = true;
        return this;
    }

    public StringSchema minLength(int length) {
        checks.put("minLength", s -> s != null && s.length() >= length);
        return this;
    }

    public StringSchema contains(String substring) {
        checks.put("contains", s -> s != null && s.contains(substring));
        return this;
    }

    public boolean isValid(String value) {
        // Если поле обязательное и значение null или пустая строка - false
        if (required && (value == null || value.isEmpty())) {
            return false;
        }
        // Если поле НЕ обязательное и значение null - true
        if (!required && value == null) {
            return true;
        }
        // Проверяем все добавленные правила
        return checks.values().stream().allMatch(p -> p.test(value));
    }
}
