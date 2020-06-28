package Code;

import java.io.File;

public class Main {
	public static void main(String[] args) {
		String route = "C:/Users/leona/ProyectoGrupal/Activity/src/Code/Lexer.flex";
		generateLexer(route);
	}
	public static void generateLexer(String route) {
		File file = new File(route);
		JFlex.Main.generate(file);
	}
}
