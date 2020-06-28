package Code;
import static Code.Tokens.*;
%%
%class Lexer
%type Tokens
LMP = [(]?[\-]?([0-9]+)[)]??([(]?([\-+/*\^]([0-9]+))?([0-9]+)?[)]?)*
%{
	public String lexeme;
%}
%%
"+" {return Suma;}
"-" {return Resta;}
"*" {return Multiplicacion;}
"/" {return Division;}
"^" {return Potencia;}
"(" {return AbrePar;}
")" {return CierraPar;}
^{LMP} {lexeme=yytext(); return Identificador;}
 . {return Error;}