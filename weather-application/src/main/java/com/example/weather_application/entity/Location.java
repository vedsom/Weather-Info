package com.example.weather_application.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Location {
    @Id
    private String pincode;
    private double latitude;
    private double longitude;

    // Getters and Setters
}
