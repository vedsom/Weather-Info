package com.example.weather_application.controller;

import com.example.weather_application.entity.WeatherInfo;
import com.example.weather_application.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void testGetWeather() throws Exception {
        WeatherInfo mockWeatherInfo = new WeatherInfo();
        mockWeatherInfo.setPincode("411014");
        mockWeatherInfo.setDate("2020-10-15");
        mockWeatherInfo.setWeatherDescription("clear sky");

        Mockito.when(weatherService.getWeatherInfo(anyString(), anyString())).thenReturn(mockWeatherInfo);

        mockMvc.perform(get("/weather")
                .param("pincode", "411014")
                .param("date", "2020-10-15"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pincode").value("411014"))
                .andExpect(jsonPath("$.date").value("2020-10-15"))
                .andExpect(jsonPath("$.weatherDescription").value("clear sky"));
    }
}

