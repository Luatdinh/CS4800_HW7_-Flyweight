public class Character {
    private char character;
    private CharacterProperties properties;

    public Character(char character, CharacterProperties properties) {
        this.character = character;
        this.properties = properties;
    }

    public void setProperties(CharacterProperties properties) {
        this.properties = properties;
    }
    public CharacterProperties getProperties() {
        return properties;
    }
    public char getCharacter() {
        return character;
    }
}