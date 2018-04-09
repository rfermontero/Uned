package compiler.semantic.type;

import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.type.TypeBase;

public class TypeArray extends TypeBase {

    private final int row;
    private final int column;

    /**
     * Constructor for TypeArray.
     *
     * @param scope The declaration scope.
     * @param name  The name of the type.
     */
    public TypeArray(ScopeIF scope, String name, int row, int column) {
        super(scope, name);
        this.row = row;
        this.column = column;
    }

    /**
     * Returns the size of the type.
     *
     * @return the size of the type.
     */
    @Override
    public int getSize() {
        // TODO: Student work
        return 1;
    }
}
