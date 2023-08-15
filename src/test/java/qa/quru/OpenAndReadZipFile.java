package qa.quru;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class OpenAndReadZipFile {
    private final ClassLoader cl = OpenAndReadZipFile.class.getClassLoader();

//    Этот код будет добавлять все файлы с именем "имяфайла.расширение" в список filenames.
//    Затем мы проверяем, что список не пустой с помощью Assertions.assertFalse(filenames.isEmpty()).
//    Если список пустой, это значит, что ни одного файла с таким именем не найдено в архиве.
    @Test
    void viewZipFile () throws Exception{
        try(InputStream stream = cl.getResourceAsStream("homework.zip");
        ZipInputStream zis = new ZipInputStream(stream)){
            ZipEntry entry;
            List<String> filenames = new ArrayList<>();
            while ((entry = zis.getNextEntry())!= null){
               final String name = entry.getName();
               if (name.equals("textfile.csv")){
                   filenames.add(name);
               } else if (name.equals("junit5.pdf")) {
                   filenames.add(name);
               } else if (name.equals("xlsfile.xls")) {
                   filenames.add(name);
               }
                zis.closeEntry();
                Assertions.assertFalse(filenames.isEmpty());
            }
        }
    }

}




