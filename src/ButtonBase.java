import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class ButtonBase extends JButton {
    ButtonBase(String text) throws IOException, FontFormatException {

        Font font = Font.createFont(
                Font.TRUETYPE_FONT,
                getClass().getResourceAsStream("/Minecraft.ttf")
        ).deriveFont(40f);



        URL starURL = getClass().getResource("/starbutton.png");
        URL starPressedURL = getClass().getResource("/starbuttonpressed.png");
        ImageIcon star = new ImageIcon(starURL);
        ImageIcon starPressed = new ImageIcon(starPressedURL);



        this.setText(text);
        this.setSize(120,110);
        this.setIcon(star);
        this.setPressedIcon(starPressed);
        this.setBorderPainted(false);
        this.setFocusable(false);
        this.setContentAreaFilled(false);
        this.setBackground(null);
        this.setForeground(Color.BLACK);
        this.setHorizontalTextPosition(CENTER);
        this.setVerticalTextPosition(CENTER);
        this.setFont(font);

        this.addActionListener(e -> playSound("/sound.wav"));
    }

    private void playSound(String resourcePath) {
        try {
            BufferedInputStream buffered = new BufferedInputStream(
                    getClass().getResourceAsStream(resourcePath)
            );
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(buffered);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);

            FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-20f);

            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
