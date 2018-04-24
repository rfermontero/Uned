package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class TypesDeclaration extends NonTerminal {

    private VariableDeclarations varsDeclaration;
    private final List<Array> arrays;

    public TypesDeclaration(VariableDeclarations varsDeclaration) {
        this.varsDeclaration = varsDeclaration;
        this.arrays = new ArrayList<>();
    }

    public TypesDeclaration(List<Array> arrays) {
        this.arrays = arrays;
    }

    public VariableDeclarations getVarsDeclaration() {
        return varsDeclaration;
    }

    public List<Array> getTypes() {
        return arrays;
    }

    public void addType(Array type) {
        arrays.add(type);
    }

    public void addTypesDeclaration(TypesDeclaration typesDeclaration) {
        addVarsDeclaration(typesDeclaration.varsDeclaration);
        addArrays(typesDeclaration.arrays);
    }

    private void addVarsDeclaration(VariableDeclarations vars) {
        if (varsDeclaration == null) {
            this.varsDeclaration = vars;
        } else {
            for (VariableDeclaration variableDeclaration : vars.getVariableDeclaration()) {
                varsDeclaration.addVar(variableDeclaration);
            }
            for (AbstractSentence sentence : vars.getSentences().getSentences()) {
                varsDeclaration.addSentence(sentence);
            }
        }
    }

    private void addArrays(List<Array> arrays) {
        for (Array array : arrays) {
            addType(array);
        }
    }
}
