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
        document.setCharacter('H', properties);
        document.setCharacter('e', properties);
    }

    @Test
    public void testSetCharacter() {
        CharacterProperties properties = factory.getProperties("Arial", 12, "Red");
        document.setCharacter('l', properties);
        List<Character> chars = document.getCharacters();
        assertEquals(3, chars.size(), "Total characters count should be 3");
        assertEquals('l', chars.get(2).getCharacter(), "The third character should be 'l'");
    }

    @Test
    public void testSaveDocument() throws IOException {
        String filename = "testOutput.txt";
        document.saveDocument(filename);
        Path path = Path.of(filename);
        assertTrue(Files.exists(path), "File should exist after saving");

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            assertTrue(line.contains("H,Arial,12,Red"), "The file should contain the character properties");
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
        assertEquals(2, document.getCharacters().size(), "Total characters loaded should be 2");
        assertEquals('X', document.getCharacters().get(0).getCharacter(), "The first character should be 'X'");
        assertEquals("Arial", document.getCharacters().get(0).getProperties().getFont(), "Font of the first character should be Arial");

        Files.delete(Path.of(filename));
    }

    @Test
    public void testGetCharacters() {
        List<Character> chars = document.getCharacters();
        assertEquals(2, chars.size(), "There should be two characters in the document");
        assertEquals('H', chars.get(0).getCharacter(), "First character should be 'H'");
        assertEquals('e', chars.get(1).getCharacter(), "Second character should be 'e'");
        assertEquals("Red", chars.get(0).getProperties().getColor(), "Color of first character should be Red");
    }
}
