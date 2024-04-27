import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

public class CharacterTest {

    @Test
    public void testCharacterCreation() {
        CharacterProperties properties = new CharacterProperties("Arial", 12, "Red");
        Character character = new Character('a', properties);

        assertEquals('a', character.getCharacter(), "Character should match constructor input.");
        assertSame(properties, character.getProperties(), "Properties should be the same object as passed to constructor.");
    }

    @Test
    public void testSetProperties() {
        CharacterProperties properties1 = new CharacterProperties("Arial", 12, "Red");
        CharacterProperties properties2 = new CharacterProperties("Calibri", 14, "Blue");

        Character character = new Character('a', properties1);
        character.setProperties(properties2);

        assertNotSame(properties1, character.getProperties(), "Properties should not be the same object after setting new one.");
        assertSame(properties2, character.getProperties(), "Properties should be updated to the new instance.");
    }
}
