package qa.quru;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;


public class OpenAndReadZipFile {
    private final ClassLoader cl = OpenAndReadZipFile.class.getClassLoader();

    //    Этот код будет добавлять все файлы с именем "имяфайла.расширение" в список filenames.
//    Затем мы проверяем, что список не пустой с помощью Assertions.assertFalse(filenames.isEmpty()).
//    Если список пустой, это значит, что ни одного файла с таким именем не найдено в архиве.
    @DisplayName("Добавление файлов в список. Тест для самопроверки не для зачёта.")
    @Test
    void addFilesInListAtZipFile() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("homework.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry entry;
            List<String> filenames = new ArrayList<>();
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.equals("textfile.csv")) {
                    filenames.add(name);
                } else if (name.equals("junit5.pdf")) {
                    filenames.add(name);
                } else if (name.equals("xlsfile.xls")) {
                    filenames.add(name);
                }
                zis.closeEntry();
                assertThat(filenames.isEmpty());
            }
        }
    }
    @DisplayName("Проверка CSV файла в Zip архиве")
    @Test
    void testCSVFileInZip() throws Exception {
        boolean csvInZip = false;
        try (InputStream stream = cl.getResourceAsStream("homework.zip");
             ZipInputStream zis = new ZipInputStream(Objects.requireNonNull(stream))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().equals("textfile.csv")) {
                    csvInZip = true;
                    Reader reader = new InputStreamReader(zis);
                    CSVReader csvReader = new CSVReader(reader);
                    List<String[]> content = csvReader.readAll();

                    assertThat(content).hasSize(3);

                    assertThat(content.get(0)).containsExactly("Chingiz, learn autotesting");
                    assertThat(content.get(1)).containsExactly("Askarov, work hard");
                    assertThat(content.get(2)).containsExactly("Kamranovich, will be incredible");
                }
            }
        }
        assertThat(csvInZip).as("There is no such file");
    }
    @DisplayName("Проверка PDF файла в Zip архиве")
    @Test
    void testPdfFileInZip() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("homework.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".pdf")) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertTrue(pdf.text.contains("Table of Contents"));

                }
            }
        }

    }
    @DisplayName("Проверка XLSX файла в Zip архиве")
    @Test
    void testXlsxFileInZip() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("homework.zip");
             ZipInputStream zis = new ZipInputStream(stream)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertEquals("Май", xls.excel.getSheetAt(0).
                            getRow(4).
                            getCell(0).
                            getStringCellValue());
                }

            }
        }
    }

}




