package compiler.syntax.nonTerminal;


/**
 * Constant class definition:
 * Uses name and value (only integer)
 */
public class Constant<T> extends NonTerminal {

    private final String name;
    private final String type;
    private final T value;

    public Constant(String name, String type, T value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }


    public T getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
