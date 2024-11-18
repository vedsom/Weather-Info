package com.example.weather_application.controller;

import com.example.weather_application.entity.WeatherInfo;
import com.example.weather_application.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherInfo getWeather(@RequestParam String pincode, @RequestParam String date) {
        return weatherService.getWeatherInfo(pincode, date);
    }
}
