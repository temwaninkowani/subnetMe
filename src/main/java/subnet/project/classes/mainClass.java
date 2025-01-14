package subnet.project.classes;

import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;

public class mainClass {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        try {
            // Load Roboto font
            InputStream fontStream = new FileInputStream("app/src/main/resources/Fonts/Roboto/Roboto_SemiCondensed-Italic.ttf");  // Adjust path as needed
            Font robotoFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(14f); // Set size to 14

            // Set the custom font globally for all Swing components
            UIManager.put("Label.font", robotoFont);
            UIManager.put("Button.font", robotoFont);
            UIManager.put("TextField.font", robotoFont);
            UIManager.put("TextArea.font", robotoFont);
            UIManager.put("ComboBox.font", robotoFont);

            // Set FlatLaf look and feel
            javax.swing.UIManager.setLookAndFeel(new FlatDarculaLaf());

        } catch (FontFormatException | IOException e) {
            System.out.println("Failed to load custom font: " + e.getMessage());
        }

        // Launch the application
        new App();
    }
}
