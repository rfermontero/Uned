package compiler.lexical;

import compiler.syntax.sym;
import compiler.lexical.Token;
import es.uned.lsi.compiler.lexical.ScannerIF;
import es.uned.lsi.compiler.lexical.LexicalError;
import es.uned.lsi.compiler.lexical.LexicalErrorManager;

// incluir aqui, si es necesario otras importaciones

%%
 
%public
%class Scanner
%char
%line
%column
%cup

%ignorecase

%implements ScannerIF
%scanerror LexicalError



%{
  LexicalErrorManager lexicalErrorManager = new LexicalErrorManager ();

  private int comment_count = 0;
  private String cadena = "";

  private Token newToken( int id, int line, int column, String lexema ) {
       Token token = new Token ( id );
       token.setLine( line );
       token.setColumn( column );
       token.setLexema( lexema  );
      
       return token;
  }

  

  private void error( int line, int column, String lexema ) {
      error( line, column, lexema, null );
  }

  private void error( int line, int column, String lexema, String mensaje ) {
      LexicalError error = new LexicalError (mensaje);
      error.setLine ( line );
      error.setColumn ( column );
      error.setLexema ( lexema );
      lexicalErrorManager.lexicalFatalError ( error);
      
  }
%}

// Especificación de expresiones regulares.
Entero = [0-9]+
FinLinea = \r | \n | \r\n
EspacioBlanco = {FinLinea } | [ \t\f]
Identificador = [A-Za-z][A-Za-z0-9]*
IdNoValida    = [0-9][A-Za-z0-9]*

%state YYCOMMENT YYSTRING
%%

<YYINITIAL> {

    "(*"             { yybegin(YYCOMMENT); comment_count++; }
    "*)"             { error( yyline+1, yycolumn+1, yytext (), "Fin de comentario, sin inicio" ); }

    "-"              { return newToken( sym.MINUS, yyline+1, yycolumn+1, yytext() ); }
    "*"              { return newToken( sym.PRODUCTO, yyline+1, yycolumn+1, yytext() ); }

    "="              { return newToken( sym.IGUAL, yyline+1, yycolumn+1, yytext() ); }
    ">"              { return newToken( sym.MAYOR, yyline+1, yycolumn+1, yytext() ); }
    ":="             { return newToken( sym.DOSPUNTOSIGUAL, yyline+1, yycolumn+1, yytext() ); }

    "("              { return newToken( sym.PARENIZQ, yyline+1, yycolumn+1, yytext() ); }
    ")"              { return newToken( sym.PARENDER, yyline+1, yycolumn+1, yytext() ); }
    "["              { return newToken( sym.CORCHIZQ, yyline+1, yycolumn+1, yytext() ); }
    "]"              { return newToken( sym.CORCHDER, yyline+1, yycolumn+1, yytext() ); }

    ","              { return newToken( sym.COMA, yyline+1, yycolumn+1, yytext() ); }
    ";"              { return newToken( sym.PUNTOYCOMA, yyline+1, yycolumn+1, yytext() ); }
    ":"              { return newToken( sym.DOSPUNTOS, yyline+1, yycolumn+1, yytext() ); }
    ".."             { return newToken( sym.PUNTOPUNTO, yyline+1, yycolumn+1, yytext() ); }

    "ARRAY"          { return newToken( sym.ARRAY, yyline+1, yycolumn+1, yytext() ); }
    "OF"             { return newToken( sym.OF, yyline+1, yycolumn+1, yytext() ); }

    "MODULE"         { return newToken( sym.MODULE, yyline+1, yycolumn+1, yytext() ); }
    "BEGIN"          { return newToken( sym.BEGIN, yyline+1, yycolumn+1, yytext() ); }
    "END"            { return newToken( sym.END, yyline+1, yycolumn+1, yytext() ); }
    "PROCEDURE"      { return newToken( sym.PROCEDURE, yyline+1, yycolumn+1, yytext() ); }
    "RETURN"         { return newToken( sym.RETURN, yyline+1, yycolumn+1, yytext() ); }

    "CONST"          { return newToken( sym.CONST, yyline+1, yycolumn+1, yytext() ); }
    "TYPE"           { return newToken( sym.TYPE, yyline+1, yycolumn+1, yytext() ); }
    "VAR"            { return newToken( sym.VAR, yyline+1, yycolumn+1, yytext() ); }

    "INTEGER"        { return newToken( sym.INTEGER, yyline+1, yycolumn+1, yytext() ); }
    "BOOLEAN"        { return newToken( sym.BOOLEAN, yyline+1, yycolumn+1, yytext() ); }

    "TRUE"           { return newToken( sym.TRUE, yyline+1, yycolumn+1, yytext() ); }
    "FALSE"          { return newToken( sym.FALSE, yyline+1, yycolumn+1, yytext() ); }

    "OR"             { return newToken( sym.OR, yyline+1, yycolumn+1, yytext() ); }
    "NOT"            { return newToken( sym.NOT, yyline+1, yycolumn+1, yytext() ); }

    "IF"             { return newToken( sym.IF, yyline+1, yycolumn+1, yytext() ); }
    "ELSE"           { return newToken( sym.ELSE, yyline+1, yycolumn+1, yytext() ); }
    "THEN"           { return newToken( sym.THEN, yyline+1, yycolumn+1, yytext() ); }
    "DO"             { return newToken( sym.DO, yyline+1, yycolumn+1, yytext() ); }
    "FOR"            { return newToken( sym.FOR, yyline+1, yycolumn+1, yytext() ); }
    "TO"             { return newToken( sym.TO, yyline+1, yycolumn+1, yytext() ); }

    "WRITESTRING"    { return newToken( sym.WRITESTRING, yyline+1, yycolumn+1, yytext() ); }
    "WRITEINT"       { return newToken( sym.WRITEINT, yyline+1, yycolumn+1, yytext() ); }
    "WRITELN"        { return newToken( sym.WRITELN, yyline+1, yycolumn+1, yytext() ); }

    {Entero}         { return newToken( sym.ENTERO, yyline+1, yycolumn+1, yytext() ); }

    {Identificador}  { return newToken( sym.IDENTIFICADOR, yyline+1, yycolumn+1, yytext() ); }


    \"               { yybegin(YYSTRING); }

    {EspacioBlanco } { /* Saltar los espacios en blanco */ }       

    {IdNoValida}     { error( yyline+1, yycolumn+1, yytext (), "Identificador no Valido" ); }

    // error en caso de no coincidir con ningún patrón
    [^]              { error( yyline+1, yycolumn+1, yytext (), "No coincide con ningún patron" ); }
}

<YYSTRING> {
    \"             { yybegin(YYINITIAL); return newToken( sym.STRING, yyline+1, yycolumn+1, cadena ); }
    [^\"]+         { cadena = yytext(); }

    <<EOF>>        { error( yyline+1, yycolumn+1, yytext (), "Cadena de caracteres sin cerrar" );
                     yybegin(YYINITIAL); }
}

<YYCOMMENT> {
    "(*"           { comment_count++; }
    "*)"           { 
		if (--comment_count == 0) yybegin(YYINITIAL); }

    

    <<EOF>>        {error( yyline+1, yycolumn+1, yytext (), "Comentarios mal balanceado" ); 
                     yybegin(YYINITIAL); }

	[^]            { /* Ignorar cualquier otro caracter */ }
}
