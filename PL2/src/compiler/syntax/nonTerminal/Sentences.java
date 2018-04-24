package compiler.syntax.nonTerminal;

import java.util.ArrayList;
import java.util.List;

public class Sentences extends NonTerminal {

    private List<AbstractSentence> sentences = new ArrayList<>();
    private BlockSentences blockSentences;
    private TypesDeclaration body;

    public Sentences(BlockSentences blockSentences, TypesDeclaration body) {
        this.blockSentences = blockSentences;
        this.body = body;
    }

    public Sentences(TypesDeclaration body) {
        this.body = body;
        this.blockSentences = null;
    }

    public Sentences(BlockSentences sentence) {
        this.blockSentences = sentence;
        this.body = null;
    }

    public Sentences(AbstractSentence sentence) {
        sentences.add(sentence);
    }

    public void add(NonTerminal sentence) {
        if (sentence instanceof AbstractSentence) {
            sentences.add((AbstractSentence) sentence);
        } else if (sentence instanceof BlockSentences) {
            blockSentences.add((BlockSentences) sentence);
        }
    }

    public Sentences(List<AbstractSentence> blockSentences) {
        this.sentences = sentences;
    }

    public BlockSentences getBlockSentences() {
        return blockSentences;
    }

    public TypesDeclaration getBody() {
        return body;
    }

    public List<AbstractSentence> getSentences(){
        return this.sentences;
    }
}
