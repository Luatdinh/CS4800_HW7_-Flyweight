import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Document {
    private List<Character> characters;

    public Document() {
        this.characters = new ArrayList<>();
    }

    public void setCharacter(int index, char character, CharacterProperties properties) {
        if (index >= 0 && index < characters.size()) {
            characters.set(index, new Character(character, properties));
        } else {
            System.err.println("Index out of bounds: " + index);
        }
    }

    public void addCharacter(char character, CharacterProperties properties) {
        characters.add(new Character(character, properties));
    }


    public List<Character> getCharacters() {
        return characters;
    }

    public void saveDocument(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Character ch : characters) {
                writer.write(ch.getCharacter() + ","
                        + ch.getProperties().getFont() + ","
                        + ch.getProperties().getSize() + ","
                        + ch.getProperties().getColor());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadDocument(String filename, PropertiesFactory factory) {
        characters.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    char character = parts[0].charAt(0);
                    String font = parts[1];
                    int size = Integer.parseInt(parts[2]);
                    String color = parts[3];

                    CharacterProperties properties = factory.getProperties(font, size, color);
                    characters.add(new Character(character, properties));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error in formatting the document data: " + e.getMessage());
        }
    }
}
