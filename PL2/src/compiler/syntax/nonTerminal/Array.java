package compiler.syntax.nonTerminal;

public class Array extends NonTerminal {

    private final String name;
    private final int columns;
    private final int rows;

    public Array(String name, int columns, int rows) {
        this.name = name;
        this.columns = columns;
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }
}
