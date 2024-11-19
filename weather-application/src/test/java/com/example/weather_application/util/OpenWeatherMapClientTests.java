package com.example.weather_application.util;

import com.example.weather_application.dto.OpenWeatherResponse;
import com.example.weather_application.entity.Location;
import com.example.weather_application.entity.WeatherInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OpenWeatherMapClientTests {

    @MockBean
    private RestTemplate restTemplate;  // Mocking RestTemplate here

    @Test
    void testGetLocationByPincode() {
        OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient(); // Spring will inject RestTemplate automatically

        OpenWeatherResponse mockResponse = new OpenWeatherResponse();
        OpenWeatherResponse.Coord mockCoord = new OpenWeatherResponse.Coord();
        mockCoord.setLat(18.5685);  // Example latitude
        mockCoord.setLon(73.9158);  // Example longitude
        mockResponse.setCoord(mockCoord);

        // Simulate the response when RestTemplate makes a GET request
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(OpenWeatherResponse.class)))
                .thenReturn(mockResponse);

        Location location = openWeatherMapClient.getLocationByPincode("411014");
        assertNotNull(location);  // Assert location is not null
        assertEquals("411014", location.getPincode());  // Assert pincode is correct
        assertEquals(18.5685, location.getLatitude(), 0.001);  // Assert latitude is correct
        assertEquals(73.9158, location.getLongitude(), 0.001);  // Assert longitude is correct
    }

    @Test
    void testGetWeatherInfo() {
        OpenWeatherMapClient openWeatherMapClient = new OpenWeatherMapClient();  // Spring will inject RestTemplate automatically

        OpenWeatherResponse mockResponse = new OpenWeatherResponse();
        OpenWeatherResponse.Weather[] mockWeatherArray = new OpenWeatherResponse.Weather[1];
        OpenWeatherResponse.Weather mockWeather = new OpenWeatherResponse.Weather();
        mockWeather.setDescription("few clouds");  // Example weather description
        mockWeatherArray[0] = mockWeather;
        mockResponse.setWeather(mockWeatherArray);

        // Simulate the response when RestTemplate makes a GET request
        Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(OpenWeatherResponse.class)))
                .thenReturn(mockResponse);

        Location location = new Location();
        location.setPincode("411014");
        location.setLatitude(18.5685);
        location.setLongitude(73.9158);

        WeatherInfo weatherInfo = openWeatherMapClient.getWeatherInfo(location, "2020-10-15");
        assertNotNull(weatherInfo);  // Assert weatherInfo is not null
        assertEquals("411014", weatherInfo.getPincode());  // Assert pincode is correct
        assertEquals("2020-10-15", weatherInfo.getDate());  // Assert date is correct
        assertEquals("few clouds", weatherInfo.getWeatherDescription());  // Assert weather description is correct
    }
}
