package goit.devProjectTeam.link;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkValidator {
    public static boolean linkValid(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode == 200;
        } catch (IOException e) {
            return false;
        }
    }
}