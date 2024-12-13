import javax.swing.*;
import java.lang.*;

public class WeatherApp {

    public static void main(String[] args) {
        // Set up the frame
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a panel for holding components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Set up the initial zip code input screen
        WeatherAppScreens.createZipCodeScreen(panel, frame);

        // Display the frame
        frame.add(panel);
        frame.setVisible(true);
    }
}