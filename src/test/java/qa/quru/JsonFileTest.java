package qa.quru;

import model.JsonModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class JsonFileTest {
    private final ClassLoader cl = JsonFileTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Проверка содержимого json Файла")
    @Test
    void checkJsonFile() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("testJson.json");
             Reader reader = new InputStreamReader(stream)) {
            JsonModel jsonModel = objectMapper.readValue(reader, JsonModel.class);



            Assertions.assertEquals("John",jsonModel.getName());
            Assertions.assertEquals(25, jsonModel.getAge());
            Assertions.assertEquals("john@example.com", jsonModel.getEmail());

            Assertions.assertEquals("123 Main St", jsonModel.getAddress().getStreet());
            Assertions.assertEquals("New York", jsonModel.getAddress().getCity());
            Assertions.assertEquals("NY", jsonModel.getAddress().getState());
            Assertions.assertEquals("10001", jsonModel.getAddress().getZipcode());


            Assertions.assertEquals("SQL", jsonModel.getSkills().getDatabase());
            Assertions.assertEquals("Java", jsonModel.getSkills().getProgramming());



            }
        }
    }
