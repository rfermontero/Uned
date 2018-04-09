package compiler.lexical;

import compiler.syntax.sym;
import compiler.lexical.Token;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.lexical.LexicalError;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;

%%
 
%public
%class Scanner
%char
%line
%column
%cup
%unicode
%full

%implements ScannerIF
%scanerror LexicalError

%state COMENTARIOS

// incluir aqui, si es necesario otras directivas
%{
  LexicalErrorManager lexicalErrorManager = new LexicalErrorManager ();
  private int commentCount = 0;
  

  public Token nuevoToken(int tipoToken){
	Token token = new Token (tipoToken);
        token.setLine (yyline + 1);
   	token.setColumn (yycolumn + 1);
	token.setLexema (yytext());
	return token;
  }
  
  
%}  

%eofval{
	if (commentCount > 0){
	  String mensaje = "Fin de fichero. Los comentarios no estan balanceados correctamente.";
	  lexicalErrorManager.lexicalFatalError (mensaje);
	}else {	  
	  return nuevoToken(sym.EOF);
	}
%eofval}

LETRA=[a-zA-Z]
DIGITO=[0-9]
ESPACIO=[\n\ \t\b\012]
NUEVA_LINEA=\r|\n|\r\n
COMILLAS=[\"]
COM_INI="/*"
COM_FIN="*/"

NUMERO=0|[1-9]{DIGITO}*
CADENA={COMILLAS}([^\"])*{COMILLAS}
IDENTIFICADOR={LETRA}({LETRA}|{NUMERO})*

ESPACIOS = {NUEVA_LINEA}+ | {ESPACIO}+


%%
<YYINITIAL,COMENTARIOS> 
	{ESPACIOS} 		{ 
					}

<COMENTARIOS>{ 
	{COM_INI}		{
						commentCount++;
				
		 			}
		 			
	
	
	{COM_FIN}		{ 	
				
						commentCount--;
				
		 				if (commentCount == 0) 
		 					yybegin(YYINITIAL);
		 			}

	[^] 	{
				
							}

}

<YYINITIAL>{
	"#define"		{return nuevoToken(sym.DEFINE);}

	"else"			{return nuevoToken(sym.ELSE);}
        			
	"if"			{return nuevoToken(sym.IF);}
        		
	"int"			{return nuevoToken(sym.ENTERO);}
	
	"main"			{return nuevoToken(sym.MAIN);}
	
	"printc"		{return nuevoToken(sym.PRINTC);}
	
	"printi"		{return nuevoToken(sym.PRINTI);}
	
	"return"		{return nuevoToken(sym.RETURN);}
        			
	"void"			{return nuevoToken(sym.VOID);}
	     
	"while"			{return nuevoToken(sym.WHILE);}
	
	"("				{return nuevoToken(sym.PARIZQ);}
        			
	")"				{return nuevoToken(sym.PARDER);}
	      
	"["				{return nuevoToken(sym.CORIZQ);}
		
	"]"				{return nuevoToken(sym.CORDER);}
	
	"{"				{return nuevoToken(sym.LLAVEIZQ);}
	
	"}"				{return nuevoToken(sym.LLAVEDER);}
        			
	","				{return nuevoToken(sym.COMA);}

	";"				{return nuevoToken(sym.PUNTOYCOMA);}

	"+"             {return nuevoToken(sym.MAS);}
                    
	"<"				{return nuevoToken(sym.MENOR);}                    		      
	
	"!="			{return nuevoToken(sym.DISTINTO);}

	"&&"			{return nuevoToken(sym.AND);}
        			
	"="				{return nuevoToken(sym.ASIGNACION);}
	
	"&"				{return nuevoToken(sym.REFERENCIA);}        			
	
	{CADENA}		{return nuevoToken(sym.CADENA);}
	
	{NUMERO}		{return nuevoToken(sym.NUMERO);}
					     			 
	{IDENTIFICADOR}	{return nuevoToken(sym.IDENTIFICADOR);}
	
	{COM_INI}		{  
						yybegin(COMENTARIOS);
						commentCount++;
						

        			}

	{COM_FIN}		{ 	//No podemos encontrar el fin de comentarios en el estado inicial
					
						LexicalError error = new LexicalError ();
                        error.setLine (yyline + 1);
                        error.setColumn (yycolumn + 1);
                        error.setLexema (yytext());
                        lexicalErrorManager.lexicalInfo ("Comentarios mal balanceados.");
                        lexicalErrorManager.lexicalFatalError (error);
                        
					}   
	
	// error en caso de coincidir con ningún patrón
	[^]				{                                               
                    	LexicalError error = new LexicalError ();
                        error.setLine (yyline + 1);
                        error.setColumn (yycolumn + 1);
                        error.setLexema (yytext ());
                        lexicalErrorManager.lexicalFatalError (error);
					}
}	


    
    



                         


