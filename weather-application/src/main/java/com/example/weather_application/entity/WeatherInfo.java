package com.example.weather_application.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class WeatherInfo {
    @Id
    private String pincode;
    private String date;
    private String weatherDescription;

    // Getters and Setters
}

