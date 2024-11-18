package com.example.weather_application.util;

import com.example.weather_application.entity.*;
import com.example.weather_application.dto.OpenWeatherResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OpenWeatherMapClient {

    private static final String API_KEY = "34925c30c8dd490a325fb10a6c9e5c3a";
    
    public Location getLocationByPincode(String pincode) {
        // Use OpenWeather API to get latitude and longitude
        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + pincode + ",IN&appid=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);
        
        // Extract lat, lon from response
        double latitude = response.getCoord().getLat();
        double longitude = response.getCoord().getLon();
        
        Location location = new Location();
        location.setPincode(pincode);
        location.setLatitude(latitude);
        location.setLongitude(longitude);
        return location;
    }

    public WeatherInfo getWeatherInfo(Location location, String date) {
        // Use OpenWeather API to get weather info for the given location and date
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + location.getLatitude() + "&lon=" + location.getLongitude() + "&appid=" + API_KEY;
        RestTemplate restTemplate = new RestTemplate();
        OpenWeatherResponse response = restTemplate.getForObject(url, OpenWeatherResponse.class);
        
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setPincode(location.getPincode());
        weatherInfo.setDate(date);
        weatherInfo.setWeatherDescription(response.getWeather()[0].getDescription()); // Extract weather description
        return weatherInfo;
    }
}