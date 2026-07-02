package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

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

    @Override
    public boolean isValid(String value) {
        if (required && (value == null || value.isEmpty())) {
            return false;
        }
        if (!required && value == null) {
            return true;
        }
        return checks.values().stream().allMatch(p -> p.test(value));
    }
}
