import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FormSubmitExample {

    public static void main(String[] args) {
        // URL of the form processing script
        String url = "http://example.com/forms/contact.php";

        try {
            // Create a URL object
            URL obj = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            // Set the request method to POST
            connection.setRequestMethod("POST");

            // Enable input/output streams
            connection.setDoOutput(true);

            // Set the parameters to be sent
            String urlParameters = "name=John&email=john@example.com&subject=Hello&message=TestMessage";

            // Convert the parameters to bytes
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);

            // Set the content length of the request
            connection.setRequestProperty("Content-Length", Integer.toString(postData.length));

            // Write the parameters to the output stream
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.write(postData);
            }

            // Get the response from the server
            int responseCode = connection.getResponseCode();

            // Read the response
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                // Print the response
                System.out.println("Response Code: " + responseCode);
                System.out.println("Response Message: " + response.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
