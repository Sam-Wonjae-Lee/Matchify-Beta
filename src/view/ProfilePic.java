package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ProfilePic {

    public static void displayImageFrame(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            BufferedImage originalImage = ImageIO.read(url);

            // Resize the image to fit within a 300x300 pixel frame without cropping
            Image scaledImage = originalImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

            JLabel label = new JLabel(new ImageIcon(scaledImage));
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(label);

            // Set the size of the frame to 300 by 300 pixels
            frame.setSize(300, 300);

            // Center the frame horizontally and position it at the top vertically
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
