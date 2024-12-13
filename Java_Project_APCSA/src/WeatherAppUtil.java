import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WeatherAppUtil {

    // Initialize a logger so we can see any user errors
    private static final Logger logger = Logger.getLogger(WeatherAppUtil.class.getName());

    public static String[] getWeatherData(String zipCode) {

        // API Data
        String apiKey = "d8cca12f850929b7e8aa7af7aa57b5f3";
        String baseUrl = "https://api.openweathermap.org/data/2.5/weather";

        // Construct the full URL with the zip code and API key
        String fullUrl = baseUrl + "?zip=" + zipCode + ",US&appid=" + apiKey;

        String[] arr = null;
        try {
            // Create an HTTP client
            HttpClient client = HttpClient.newHttpClient();

            // Create an HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(fullUrl))
                    .GET()
                    .build();

            // Ask and you shall receive
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Parse the JSON response into a JsonObject
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();

            System.out.println(jsonResponse);

            // The response gives us a really ugly JSON file, so let's make it less ugly
            String cityName = jsonResponse.get("name").getAsString();

            double temperature = jsonResponse.getAsJsonObject("main").get("temp").getAsDouble();
            temperature = temperature - 273.15; // Convert from Kelvin to Celsius
            BigDecimal temp = new BigDecimal(temperature).setScale(1, RoundingMode.HALF_UP); // This gives us a slightly off double, so we round
            double roundedTemperature = temp.doubleValue();

            String weatherDescription = jsonResponse.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

            // Pack weather data so we can return it
            arr = new String[3];
            arr[0] = cityName;
            arr[1] = String.valueOf(roundedTemperature);
            arr[2] = weatherDescription;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred: " + e.getMessage(), e);
        }

        // Return array of data
        return arr;
    }
}
