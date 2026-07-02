package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        checks.put("positive", n -> n != null && n > 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        checks.put("range", n -> n != null && n >= min && n <= max);
        return this;
    }
}
