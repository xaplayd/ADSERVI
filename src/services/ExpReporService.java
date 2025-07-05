package services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ExpReporService {

	public static void expReportAs(String type, String path, String nameArchive,
			TableView<ObservableList<String>> data) {

		String nomeArquivo = nameArchive;
		String caminho = path;

		if (type == "pdf") {

			if (nomeArquivo.isEmpty() || caminho.isEmpty()) {
				System.out.println("Nome do arquivo ou caminho não preenchido.");
				return;
			}

			File arquivo = Paths.get(caminho, nomeArquivo + ".pdf").toFile();

			Document document = new Document();

			try {
				PdfWriter.getInstance(document, new FileOutputStream(arquivo));
				document.open();

				PdfPTable table = new PdfPTable(data.getColumns().size());

				// Cabeçalhos
				for (TableColumn<ObservableList<String>, ?> col : data.getColumns()) {
					String header = col.getText() != null ? col.getText() : "";
					table.addCell(new PdfPCell(new Phrase(header)));
				}

				// Dados
				for (ObservableList<String> linha : data.getItems()) {
					for (String celula : linha) {
						table.addCell(new PdfPCell(new Phrase(celula != null ? celula : "")));
					}
				}

				document.add(table);
				System.out.println("Arquivo PDF exportado com sucesso.");

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				document.close();
			}
		} else if (type == "csv") {

			if (nomeArquivo.isEmpty() || caminho.isEmpty()) {
				System.out.println("Nome do arquivo ou caminho não preenchido.");
				return;
			}

			File arquivo = Paths.get(caminho, nomeArquivo + "." + "csv").toFile();

			try (PrintWriter writer = new PrintWriter(arquivo)) {
				// Cabeçalhos
				List<String> headers = data.getColumns().stream().map(col -> col.getText() != null ? col.getText() : "")
						.collect(Collectors.toList());
				writer.println(String.join(";", headers));

				// Dados
				for (ObservableList<String> linha : data.getItems()) {
					List<String> dados = linha.stream().map(val -> val != null ? val : "").collect(Collectors.toList());
					writer.println(String.join(";", dados));
				}

				System.out.println("Arquivo exportado com sucesso: " + arquivo.getAbsolutePath());

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (type == "xlsx") {
			if (nomeArquivo.isEmpty() || caminho.isEmpty()) {
				System.out.println("Nome do arquivo ou caminho não preenchido.");
				return;
			}

			File arquivo = Paths.get(caminho, nomeArquivo + ".xlsx").toFile();

			try (Workbook workbook = new XSSFWorkbook()) {
				Sheet sheet = workbook.createSheet("Setores");

				// Cabeçalho
				Row headerRow = sheet.createRow(0);
				for (int i = 0; i < data.getColumns().size(); i++) {
					TableColumn<ObservableList<String>, ?> col = data.getColumns().get(i);
					String header = col.getText() != null ? col.getText() : "Coluna " + i;
					headerRow.createCell(i).setCellValue(header);
				}

				// Dados
				for (int i = 0; i < data.getItems().size(); i++) {
					ObservableList<String> linha = data.getItems().get(i);
					Row row = sheet.createRow(i + 1);
					for (int j = 0; j < linha.size(); j++) {
						Object valor = linha.get(j);
						Cell cell = row.createCell(j);
						if (valor == null) {
							cell.setCellValue("");
						} else if (valor instanceof Number) {
							cell.setCellValue(((Number) valor).doubleValue());
						} else if (valor instanceof Boolean) {
							cell.setCellValue((Boolean) valor);
						} else {
							cell.setCellValue(valor.toString());
						}
					}
				}

				try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
					workbook.write(fileOut);
				}

				System.out.println("Arquivo XLSX exportado com sucesso.");

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (type == "txt") {
			if (nomeArquivo.isEmpty() || caminho.isEmpty()) {
				System.out.println("Nome do arquivo ou caminho não preenchido.");
				return;
			}

			File arquivo = Paths.get(caminho, nomeArquivo + "." + "txt").toFile();

			try (PrintWriter writer = new PrintWriter(arquivo)) {
				// Cabeçalhos
				List<String> headers = data.getColumns().stream().map(col -> col.getText() != null ? col.getText() : "")
						.collect(Collectors.toList());
				writer.println(String.join("\t", headers));

				// Dados
				for (ObservableList<String> linha : data.getItems()) {
					List<String> dados = linha.stream().map(val -> val != null ? val : "").collect(Collectors.toList());
					writer.println(String.join("\t", dados));
				}

				System.out.println("Arquivo exportado com sucesso: " + arquivo.getAbsolutePath());

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
