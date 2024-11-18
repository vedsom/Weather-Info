package com.example.weather_application.dto;

import lombok.Data;

@Data
public class OpenWeatherResponse {
    private Main main;
    private Wind wind;
    private Weather[] weather;
    private Coord coord;

    // Getters and Setters
    @Data
    public static class Main {
        private double temp;
        private int pressure;
        private int humidity;

        // Getters and Setters
    }
    @Data
    public static class Wind {
        private double speed;
        private double deg;

        // Getters and Setters
    }
    @Data
    public static class Weather {
        private String description;

        // Getters and Setters
    }
    @Data
    public static class Coord {
        private double lat;
        private double lon;

        // Getters and Setters
    }
}
