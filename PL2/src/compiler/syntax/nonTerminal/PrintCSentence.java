package compiler.syntax.nonTerminal;

public class PrintCSentence extends AbstractSentence {
    private final String sentence;

    public PrintCSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }
}
