
package compiler.test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import compiler.CompilerContext;

import es.uned.lsi.compiler.code.FinalCodeFactoryIF;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.semantic.Scope;
import es.uned.lsi.compiler.semantic.ScopeIF;
import es.uned.lsi.compiler.semantic.ScopeManagerIF;
import es.uned.lsi.compiler.semantic.SemanticErrorManager;
import es.uned.lsi.compiler.semantic.symbol.SymbolIF;
import es.uned.lsi.compiler.semantic.symbol.SymbolTableIF;
import es.uned.lsi.compiler.semantic.type.TypeIF;
import es.uned.lsi.compiler.semantic.type.TypeTableIF;
import es.uned.lsi.compiler.syntax.SyntaxErrorManager;

/**
 * Test class for final checking (semantic & code generation).
 */

public class FinalTestCase
{
    private static SemanticErrorManager semanticErrorManager = null;
    private static String sourceFileExtension = ".muned";  
    
    /**
     * Constructor for FinalTestCase.
     */
    public FinalTestCase ()
    {
    }

    /**
     * Tests the parser against a test file.
     * @param fileName The path of the file test.
     */
    @SuppressWarnings({"unchecked","rawtypes"})
	public void testSemantic (String fileName)
    {
        SyntaxErrorManager syntaxErrorManager = new SyntaxErrorManager ();
        
        try 
        {
        	syntaxErrorManager.syntaxInfo ("Input file > " + fileName);

            FinalCodeFactoryIF finalCodeFactory = CompilerContext.getFinalCodeFactory ();            
            String ensName = fileName.replaceFirst (sourceFileExtension+"$", ".ens");
            syntaxErrorManager.syntaxInfo ("Output file > " + ensName);
            File outputFile = new File (ensName);
            finalCodeFactory.setFile (outputFile);
            
            FileReader aFileReader = new FileReader (fileName);
            ScannerIF aScanner = null; 
            
            try 
    		{
                // Llamada por introspeccion a las clases compiler.lexical.Scanner y 
                // compiler.syntax.parser ya que no existen hasta invocar a la tarea build
                // En la entrega de Febrero se generará una excepción en caso de no estar 
                // comentadas las líneas correspondientes en el fichero parser.cup
            	
            	 // reflect Scanner
                //Scanner
                Class scannerClass = null;
                try {
                scannerClass = Class.forName ("compiler.lexical.Scanner");   			
                }
                catch (Exception e1){
                	System.err.println ("Error! Scanner or parser not implemented." +
                			"Use build ant task to obtain Scanner.java, parser.java and sym.java \n");
                }
       			Constructor scannerConstructor = scannerClass.getConstructor(Reader.class);
       			aScanner = (ScannerIF) scannerConstructor.newInstance(aFileReader);
                // reflect parser
       			Class parserClass = Class.forName ("compiler.syntax.parser");
    			Constructor parserConstructor = parserClass.getConstructor(java_cup.runtime.Scanner.class);
    			
    			// reflect call parser.pase()
    			Method parseMethod =  parserClass.getMethod("parse");
    			parseMethod.invoke(parserConstructor.newInstance(aScanner));
    			    			
                logSemantics ();
    			
    		} 
    		catch (Exception e) {
    		
    			e.printStackTrace();
    			    			
    		}
            
    		
        }
        catch (Exception e)
        {
            e.printStackTrace ();
        }
    }

    /**
     * Logs the semantic info.
     */
    private void logSemantics ()
    {
        logTypeTables ();
        logSymbolTables ();
    }
    
    /**
     * Logs all type tables.
     */
    private void logTypeTables ()
    {
        semanticErrorManager.semanticDebug ("** TYPE TABLES **");
        ScopeManagerIF scopeManager = CompilerContext.getScopeManager ();
        Iterator<ScopeIF> scopesIt = scopeManager.getAllScopes ().iterator ();
        List<TypeTableIF> dumpedTypeTables = new ArrayList<TypeTableIF> ();
        while (scopesIt.hasNext ())
        {
            Scope aScope = (Scope) scopesIt.next ();
            TypeTableIF aTypeTable = aScope.getTypeTable ();
            if (!dumpedTypeTables.contains (aTypeTable)) logTypeTable (aTypeTable);
        }
    }
    
    /**
     * Logs a type table
     * @param type Table
     */
    private void logTypeTable(TypeTableIF typeTable)
    {	
        Iterator<TypeIF> typesIt = typeTable.getTypes ().iterator ();
        while (typesIt.hasNext ())
        {
            TypeIF aType = (TypeIF) typesIt.next ();
            semanticErrorManager.semanticDebug (aType);
        }
    }
    
    /**
     * Logs all symbol tables.
     */
    private void logSymbolTables ()
    {
        semanticErrorManager.semanticDebug ("** SYMBOL TABLES **");
        ScopeManagerIF scopeManager = CompilerContext.getScopeManager ();
        Iterator<ScopeIF> scopesIt = scopeManager.getAllScopes ().iterator ();
        while (scopesIt.hasNext ())
        {
            ScopeIF scope = (ScopeIF) scopesIt.next ();
            logSymbolTable (scope.getSymbolTable ());
        }
    }

    /**
     * Logs a symbol table.
     * @param symbolTable the symbol table to log.
     */
    private void logSymbolTable (SymbolTableIF symbolTable)
    {
        semanticErrorManager.semanticDebug ("SYMBOL TABLE ["
                                            + symbolTable.getScope ().getName ()
                                            + "]");
        Iterator<SymbolIF> symbolsIt = symbolTable.getSymbols ().iterator ();
        while (symbolsIt.hasNext ())
        {
            SymbolIF aSymbol = (SymbolIF) symbolsIt.next ();
            semanticErrorManager.semanticDebug (aSymbol);
        }
    }

    /**
     * Starts the parser test case.
     * @param args Arguments Array containing the path to the input file at the
     *             first element.
     */
    public static void main (String args[])
    {
    	
        if (args.length < 1 || args.length > 1)
        {
        	 System.err.println ("Use:  java FinalTestCase \"file\"");
        }
        else
        {
            FinalTestCase finalTest = new FinalTestCase ();
            String fileName = args[0];
            semanticErrorManager = CompilerContext.getSemanticErrorManager ();
            finalTest.testSemantic (fileName);
        }
    }
}
