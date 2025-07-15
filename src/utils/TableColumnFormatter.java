package utils;

import java.util.function.UnaryOperator;

import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;

public class TableColumnFormatter {

		public static String formatarNomeColunaAutomaticamente(String nomeOriginal) {
			StringBuilder formatado = null;
			try{
				if (nomeOriginal == null || nomeOriginal.isEmpty()) return "";
			
				String[] palavras = nomeOriginal.split("_");
				formatado = new StringBuilder();
				for (String palavra : palavras) {
					if (!palavra.isEmpty()) {
						formatado.append(Character.toUpperCase(palavra.charAt(0)));
						if (palavra.length() > 1) {
							formatado.append(palavra.substring(1).toLowerCase());
						}
						formatado.append(" ");
					}
				}
			}catch (Exception exception) {
				exception.getMessage();
			}
			return formatado.toString().trim();
		}
		
		public static TextFormatter<String> numericIntegerFormatter() {
			UnaryOperator<Change> filter = change -> {
				String novoTexto = change.getControlNewText();
				return novoTexto.matches("\\d*") ? change : null;
			};
			return new TextFormatter<>(filter);
		}
		
		public static TextFormatter<String> numericDecimalFormatter() {
			UnaryOperator<Change> filter = change -> {
				String novoTexto = change.getControlNewText();
				return novoTexto.matches("\\d*(\\.\\d*)?") ? change : null;
			};
			return new TextFormatter<>(filter);
		}
}
