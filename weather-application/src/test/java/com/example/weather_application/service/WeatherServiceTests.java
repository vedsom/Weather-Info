package com.example.weather_application.service;

import com.example.weather_application.entity.Location;
import com.example.weather_application.entity.WeatherInfo;
import com.example.weather_application.repository.LocationRepository;
import com.example.weather_application.repository.WeatherInfoRepository;
import com.example.weather_application.util.OpenWeatherMapClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class WeatherServiceTests {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private LocationRepository locationRepository;

    @MockBean
    private WeatherInfoRepository weatherInfoRepository;

    @MockBean
    private OpenWeatherMapClient openWeatherMapClient;

    private Location mockLocation;
    private WeatherInfo mockWeatherInfo;

    @BeforeEach
    void setUp() {
        mockLocation = new Location();
        mockLocation.setPincode("411014");
        mockLocation.setLatitude(18.5204);
        mockLocation.setLongitude(73.8567);

        mockWeatherInfo = new WeatherInfo();
        mockWeatherInfo.setPincode("411014");
        mockWeatherInfo.setDate("2020-10-15");
        mockWeatherInfo.setWeatherDescription("clear sky");

        Mockito.when(locationRepository.findById(anyString())).thenReturn(java.util.Optional.of(mockLocation));
        Mockito.when(weatherInfoRepository.findById(anyString())).thenReturn(java.util.Optional.of(mockWeatherInfo));
        Mockito.when(openWeatherMapClient.getLocationByPincode(anyString())).thenReturn(mockLocation);
        Mockito.when(openWeatherMapClient.getWeatherInfo(mockLocation, "2020-10-15")).thenReturn(mockWeatherInfo);
    }

    @Test
    void testGetWeatherInfo() {
        WeatherInfo weatherInfo = weatherService.getWeatherInfo("411014", "2020-10-15");
        assertNotNull(weatherInfo);
        assertEquals("411014", weatherInfo.getPincode());
        assertEquals("2020-10-15", weatherInfo.getDate());
        assertEquals("clear sky", weatherInfo.getWeatherDescription());
    }
}
