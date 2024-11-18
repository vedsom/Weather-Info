package com.example.weather_application.service;

import com.example.weather_application.entity.*;
import com.example.weather_application.repository.*;
import com.example.weather_application.util.OpenWeatherMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private WeatherInfoRepository weatherInfoRepository;

    @Autowired
    private OpenWeatherMapClient openWeatherMapClient;

    public WeatherInfo getWeatherInfo(String pincode, String date) {
        Location location = locationRepository.findById(pincode).orElse(null);
        if (location == null) {
            // Call OpenWeatherMap API to get lat, lon and weather data
            location = openWeatherMapClient.getLocationByPincode(pincode);
            locationRepository.save(location);
        }

        // Check if weather data already exists for this pincode and date
        WeatherInfo weatherInfo = weatherInfoRepository.findById(pincode).orElse(null);
        if (weatherInfo == null || !weatherInfo.getDate().equals(date)) {
            weatherInfo = openWeatherMapClient.getWeatherInfo(location, date);
            weatherInfoRepository.save(weatherInfo);
        }

        return weatherInfo;
    }
}