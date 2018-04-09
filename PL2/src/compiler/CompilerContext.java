
package compiler;

import compiler.code.ExecutionEnvironmentEns2001;

import es.uned.lsi.compiler.code.ExecutionEnvironmentIF;
import es.uned.lsi.compiler.code.FinalCodeFactory;
import es.uned.lsi.compiler.code.FinalCodeFactoryIF;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;
import es.uned.lsi.compiler.semantic.ScopeManager;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.SemanticErrorManager;
import es.uned.lsi.compiler.syntax.SyntaxErrorManager;

/**
 * Class to manage compiler context.
 */

public class CompilerContext 
{ 
    private static LexicalErrorManager    lexicalErrorManager  = new LexicalErrorManager ();
    private static SyntaxErrorManager     syntaxErrorManager   = new SyntaxErrorManager ();
    private static SemanticErrorManager   semanticErrorManager = new SemanticErrorManager ();
    private static ScopeManagerIF         scopeManager         = new ScopeManager ();
    private static FinalCodeFactoryIF     finalCodeFactory     = new FinalCodeFactory ();
    private static ExecutionEnvironmentIF executionEnvironment = new ExecutionEnvironmentEns2001 ();
        
    /**
     * Returns the lexicalErrorManager.
     * @return Returns the lexicalErrorManager.
     */
    public static LexicalErrorManager getLexicalErrorManager ()
    {
        return lexicalErrorManager;
    }

    /**
     * Returns the syntaxErrorManager.
     * @return Returns the syntaxErrorManager.
     */
    public static SyntaxErrorManager getSyntaxErrorManager ()
    {
        return syntaxErrorManager;
    }

    /**
     * Returns the semanticErrorManager.
     * @return Returns the semanticErrorManager.
     */
    public static SemanticErrorManager getSemanticErrorManager ()
    {
        return semanticErrorManager;
    }

    /**
     * Returns the scopeManager.
     * @return Returns the scopeManager.
     */
    public static ScopeManagerIF getScopeManager ()
    {
        return scopeManager;
    }

    /**
     * Returns the finalCodeFactory.
     * @return Returns the finalCodeFactory.
     */
    public static FinalCodeFactoryIF getFinalCodeFactory ()
    {
        return finalCodeFactory;
    }

    /**
     * Returns the executionEnvironment.
     * @return Returns the executionEnvironment.
     */
    public static ExecutionEnvironmentIF getExecutionEnvironment ()
    {
        return executionEnvironment;
    }
    
}
