package com.example.weather_application.repository;

import com.example.weather_application.entity.WeatherInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherInfoRepository extends JpaRepository<WeatherInfo, String> {
}
