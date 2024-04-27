import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CharacterPropertiesTest {

    @Test
    public void testCharacterPropertiesCreation() {
        CharacterProperties properties = new CharacterProperties("Arial", 12, "Red");

        assertEquals("Arial", properties.getFont(), "Font should be correctly initialized.");
        assertEquals(12, properties.getSize(), "Size should be correctly initialized.");
        assertEquals("Red", properties.getColor(), "Color should be correctly initialized.");
    }
}
