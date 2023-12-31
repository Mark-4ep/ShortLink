package goit.devProjectTeam.link;

import goit.devProjectTeam.config.ProjectConstant;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkUtils {

    @Value("${app.validity_period}")
    private static long validityPeriod;

    @Value("${app.token_length}")
    private static long tokenLength;

    private LinkUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate() {
        List<Character> characters = new ArrayList<>();

        for (char i = 'a'; i <= 'z'; i++) {
            characters.add(i);
        }

        for (char i = 'A'; i <= 'Z'; i++) {
            characters.add(i);
        }

        for (char i = '0'; i <= '9'; i++) {
            characters.add(i);
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ProjectConstant.TOKEN_LENGTH; i++) {
            Collections.shuffle(characters);
            result.append(characters.get(0));
        }

        return result.toString();
    }

    public static Timestamp calculateExpireDate() {
        long currentTime = System.currentTimeMillis();
        long plusHours = currentTime + (ProjectConstant.VALIDITY_PERIOD_HOURS * 60 * 60 * 1000);
        return new Timestamp(plusHours);
    }

}
