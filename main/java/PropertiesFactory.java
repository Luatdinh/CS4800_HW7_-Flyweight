import java.util.HashMap;
import java.util.Map;
public class PropertiesFactory {
    private Map<String, CharacterProperties> properties;

    public PropertiesFactory() {
        this.properties = new HashMap<>();
    }
    public CharacterProperties getProperties(String font, int size, String color) {
        String characterKey = font + size + color;
        if (!properties.containsKey(characterKey)) {
            properties.put(characterKey, new CharacterProperties(font, size, color));
        }
        return properties.get(characterKey);
    }
}