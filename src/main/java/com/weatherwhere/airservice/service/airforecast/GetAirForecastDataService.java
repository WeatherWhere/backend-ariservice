package com.weatherwhere.airservice.service.airforecast;

import com.weatherwhere.airservice.domain.airforecast.AirForecastEntity;
import com.weatherwhere.airservice.dto.airforecast.AirForecastDto;
import com.weatherwhere.airservice.dto.airforecast.SearchAirForecastDto;
import com.weatherwhere.airservice.dto.ResultDTO;

public interface GetAirForecastDataService {

    // 해당 위치 7일 대기오염 주간예보 DB 가져오기
    // startDate에서 필요한 형식은 yyyy-MM-dd
    ResultDTO<Object> getSevenDaysDataOfLocation(SearchAirForecastDto searchAirForecastDto) throws Exception;

    // 하루 주간예보 가져오기
    ResultDTO<AirForecastDto> getAirForecastOneDay(SearchAirForecastDto searchAirForecastDto);


    // Entity->DTO
    default AirForecastDto entityToDto(AirForecastEntity airForecastEntity){
        AirForecastDto airForecastDto=AirForecastDto.builder()
            .forecast(airForecastEntity.getForecast())
            .baseDate(airForecastEntity.getAirForecastId().getBaseDate())
            .city(airForecastEntity.getAirForecastId().getCity())
            .reliability(airForecastEntity.getReliability())
            .build();
        return airForecastDto;
    }
}
