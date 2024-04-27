import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DocumentTest {

    private Document document;
    private PropertiesFactory factory;

    @BeforeEach
    public void setUp() {
        document = new Document();
        factory = new PropertiesFactory();
        CharacterProperties properties = factory.getProperties("Arial", 12, "Red");
        document.addCharacter('H', properties);
        document.addCharacter('e', properties);
    }

    @Test
    public void testAddCharacter() {
        CharacterProperties properties = factory.getProperties("Arial", 12, "Red");
        document.addCharacter('l', properties);
        List<Character> chars = document.getCharacters();
        assertEquals(3, chars.size());
        assertEquals('l', chars.get(2).getCharacter());
    }

    @Test
    public void testSetCharacter() {
        CharacterProperties newProperties = factory.getProperties("Calibri", 14, "Blue");
        document.setCharacter(0, 'X', newProperties);
        assertEquals('X', document.getCharacters().get(0).getCharacter());
        assertEquals("Calibri", document.getCharacters().get(0).getProperties().getFont());
    }

    @Test
    public void testSaveDocument() throws IOException {
        String filename = "testOutput.txt";
        document.saveDocument(filename);
        Path path = Path.of(filename);
        assertTrue(Files.exists(path));

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            assertTrue(line.contains("H,Arial,12,Red"));
        } finally {
            Files.delete(path);
        }
    }

    @Test
    public void testLoadDocument() throws IOException {
        String filename = "testLoad.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("X,Arial,12,Red");
            writer.newLine();
            writer.write("Y,Calibri,14,Blue");
            writer.newLine();
        }

        document.loadDocument(filename, factory);
        assertEquals(2, document.getCharacters().size());
        assertEquals('X', document.getCharacters().get(0).getCharacter());
        assertEquals("Arial", document.getCharacters().get(0).getProperties().getFont());

        Files.delete(Path.of(filename));
    }
}
