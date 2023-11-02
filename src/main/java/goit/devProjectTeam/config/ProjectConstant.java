package goit.devProjectTeam.config;

import lombok.Getter;

@Getter
public class ProjectConstant {
    private ProjectConstant() {
    }
    public static final int TOKEN_LENGTH = 6;
    public static final long VALIDITY_PERIOD_HOURS = 72;

    public static long CACHE_PERIOD_MINUTES = 15;
    public static long CACHE_SIZE = 10000;

}
