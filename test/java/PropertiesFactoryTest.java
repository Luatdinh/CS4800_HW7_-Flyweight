import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PropertiesFactoryTest {

    private PropertiesFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new PropertiesFactory();
    }

    @Test
    public void testGetProperties_Caching() {
        CharacterProperties properties1 = factory.getProperties("Arial", 12, "Red");
        CharacterProperties properties2 = factory.getProperties("Arial", 12, "Red");
        assertSame(properties1, properties2, "Expected the same object for cache hit");
    }

    @Test
    public void testGetProperties_NoCachingForDifferentProperties() {
        CharacterProperties properties1 = factory.getProperties("Arial", 12, "Red");
        CharacterProperties properties2 = factory.getProperties("Calibri", 14, "Blue");
        assertNotSame(properties1, properties2, "Expected different objects for different properties");
    }

    @Test
    public void testGetProperties_ConsistentCreation() {
        CharacterProperties properties1 = factory.getProperties("Arial", 12, "Red");
        CharacterProperties properties2 = factory.getProperties("Calibri", 14, "Blue");
        CharacterProperties properties3 = factory.getProperties("Verdana", 16, "Green");
        assertEquals("Arial", properties1.getFont());
        assertEquals(12, properties1.getSize());
        assertEquals("Red", properties1.getColor());
        assertEquals("Calibri", properties2.getFont());
        assertEquals(14, properties2.getSize());
        assertEquals("Blue", properties2.getColor());
        assertEquals("Verdana", properties3.getFont());
        assertEquals(16, properties3.getSize());
        assertEquals("Green", properties3.getColor());
    }
}
