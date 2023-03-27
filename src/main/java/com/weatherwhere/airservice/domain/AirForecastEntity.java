package com.weatherwhere.airservice.domain;

import java.time.LocalDate;

import com.weatherwhere.airservice.dto.AirForecastDto;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="air_forecast", schema = "air")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class AirForecastEntity extends BaseEntity{

    @EmbeddedId
    private AirForecastId airForecastId;

    @Column(name="forecast")
    private String forecast;
    @Column(name="reliability")
    private String reliability;

    /*
    public void update(AirForecastDto dto){
        this.airForecastId.getBaseDate()= dto.getBaseDate();
        this.city= dto.getCity();
        this.forecast= dto.getForecast();
        this.reliability= dto.getReliability();
    }*/
}
