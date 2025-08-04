package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiUtils {

    private static final Logger logger = LogManager.getLogger(ApiUtils.class);
    private static final HttpClient client = HttpClient.newHttpClient();


    public static HttpResponse<String> sendGetRequest(String countryCode, String postalCode, boolean encode) {
        String uri = buildUri(countryCode, postalCode, encode);

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();

            logger.info("Sending request to: {}", uri);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            logger.info("Status code: {}", response.statusCode());
            logger.info("Response body: {}", response.body());
            return response;

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException("HTTP request failed: " + uri, e);
        }
    }

    public static JsonObject parseJson(String responseBody) {
        try {
            return JsonParser.parseString(responseBody)
                    .getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse response body as JSON object: " + e.getMessage(), e);
        }
    }

    private static String buildUri(String country, String postal, boolean encode) {
        try {
            String c = encode ? URLEncoder.encode(country, StandardCharsets.UTF_8) : country;
            String p = encode ? URLEncoder.encode(postal, StandardCharsets.UTF_8) : postal;
            return String.format("http://api.zippopotam.us/%s/%s", c, p);
        } catch (Exception e) {
            throw new RuntimeException("Failed to build URI with given input", e);
        }

    }
}
