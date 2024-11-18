package com.example.weather_application.repository;

import com.example.weather_application.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, String> {
}
