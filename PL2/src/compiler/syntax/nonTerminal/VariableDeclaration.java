package compiler.syntax.nonTerminal;

import java.util.List;

public class VariableDeclaration extends NonTerminal {

    private final List<GenericVariable> varsDeclaration;

    public VariableDeclaration(List<GenericVariable> varsDeclaration) {
        this.varsDeclaration = varsDeclaration;
    }

    public List<GenericVariable> getVarsDeclaration() {
        return varsDeclaration;
    }
}
