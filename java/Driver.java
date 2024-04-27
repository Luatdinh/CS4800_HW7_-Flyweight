public class Driver {
    public static void main(String[] args) {
        PropertiesFactory factory = new PropertiesFactory();
        Document document = new Document();
        String text = "HelloWorldCS5800";
        CharacterProperties initialProperties = factory.getProperties("Arial", 12, "Red");

        for (int i = 0; i < text.length(); i++) {
            document.addCharacter(text.charAt(i), initialProperties);
        }

        String[] fonts = {"Arial", "Calibri", "Verdana", "Arial"};
        int[] sizes = {12, 14, 16, 12};
        String[] colors = {"Red", "Blue", "Black", "Blue"};
        int partLength = text.length() / 4;

        for (int i = 0; i < 4; i++) {
            int start = i * partLength;
            int end = (i + 1) * partLength;
            if (i == 3) end = text.length();
            CharacterProperties properties = factory.getProperties(fonts[i], sizes[i], colors[i]);

            for (int j = start; j < end; j++) {
                document.setCharacter(j, text.charAt(j), properties);
            }
        }

        document.saveDocument("output.txt");
        System.out.println("Document saved! Now loading...");
        document.loadDocument("output.txt", factory);
        System.out.println("Loaded Document:");

        for (Character letter : document.getCharacters()) {
            System.out.println("Char: " + letter.getCharacter() + ", Font: " + letter.getProperties().getFont()
                    + ", Size: " + letter.getProperties().getSize() + ", Color: " + letter.getProperties().getColor());
        }
    }
}
