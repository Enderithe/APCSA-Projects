import javax.swing.*;
import java.awt.*;

public class WeatherAppScreens {

    // Method for creating the initial screen to get zip code
    public static void createZipCodeScreen(JPanel panel, JFrame frame) {
        System.out.println("Creating Zip Code Screen");

        // Clear the panel so we start with a clean slate
        panel.removeAll();

        // Add a prompt for the zip code
        JLabel zipCodePrompt = new JLabel("Enter Zip Code:");
        JTextField zipCodeField = new JTextField(10);  // Text field for zip code input
        JButton getWeatherButton = new JButton("Get Weather");

        // Add the components to the panel
        panel.add(zipCodePrompt);
        panel.add(zipCodeField);
        panel.add(getWeatherButton);

        // Button action listener
        getWeatherButton.addActionListener(e -> {
            String zipCode = zipCodeField.getText().trim();  // Get the zip code entered by the user
            if (zipCode.length() == 5) {
                String[] weatherData = WeatherAppUtil.getWeatherData(zipCode);
                if (weatherData != null) {
                    showWeatherData(panel, frame, weatherData);
                } else {
                    showErrorScreen(panel, frame, "Failed to fetch weather data.");
                }
            } else {
                showErrorScreen(panel, frame, "Invalid zip code. Please enter a valid 5-digit zip code.");
            }
        });
    }

    // Method for showing errors
    public static void showErrorScreen(JPanel panel, JFrame frame, String message) {
        // Clear the panel
        panel.removeAll();

        JLabel errorLabel = new JLabel(message, SwingConstants.CENTER);
        errorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JButton returnButton = new JButton("Return");

        // Add the components to the panel
        panel.add(errorLabel);
        panel.add(returnButton);

        // Action listener for the return button
        returnButton.addActionListener(e -> createZipCodeScreen(panel, frame));

        // Refresh the panel
        frame.revalidate();
        frame.repaint();
    }

    // Method for displaying weather data
    public static void showWeatherData(JPanel panel, JFrame frame, String[] weatherData) {
        // Clear the panel
        panel.removeAll();

        // Create a label with a title
        JLabel title = new JLabel("Weather Information", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        // Create labels for displaying weather data
        JLabel cityLabel = new JLabel("City: " + weatherData[0]);
        JLabel tempLabel = new JLabel("Temperature: " + weatherData[1] + "°C");
        JLabel conditionLabel = new JLabel("Condition: " + weatherData[2]);

        // Add the labels to the panel
        panel.add(title);
        panel.add(cityLabel);
        panel.add(tempLabel);
        panel.add(conditionLabel);

        // Refresh the panel
        frame.revalidate();
        frame.repaint();
    }
}
