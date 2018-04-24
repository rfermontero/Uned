package compiler.syntax.nonTerminal;

import java.util.List;

public class VariableDeclarations extends NonTerminal {

    private final List<VariableDeclaration> variableDeclarations;
    private final Sentences sentences;

    public VariableDeclarations(List<VariableDeclaration> variableDeclarations, Sentences sentences) {
        this.variableDeclarations = variableDeclarations;
        this.sentences = sentences;
    }

    public List<VariableDeclaration> getVariableDeclaration() {
        return variableDeclarations;
    }

    public Sentences getSentences() {
        return sentences;
    }

    public void addVar(VariableDeclaration var){
        variableDeclarations.add(var);
    }

    public void addSentence(AbstractSentence sentence) {
        sentences.add(sentence);
    }
}
