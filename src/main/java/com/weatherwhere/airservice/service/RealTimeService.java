package com.weatherwhere.airservice.service;

import com.weatherwhere.airservice.domain.RealTimeAirEntity;
import com.weatherwhere.airservice.dto.RealTimeAirDto;

import javax.naming.event.ObjectChangeListener;
import java.text.ParseException;

public interface RealTimeService {

    Object getRealTimeAirData(String stationName) throws ParseException, org.json.simple.parser.ParseException;
    Object saveRealTimeAirData(String stationName) throws ParseException, org.json.simple.parser.ParseException;

    Object updateRealtimeAirDate() throws ParseException, org.json.simple.parser.ParseException;

    //Dto -> Entity 메서드
    default RealTimeAirEntity ToEntity(RealTimeAirDto dto) {
        RealTimeAirEntity entity = RealTimeAirEntity.builder()
                .stationName(dto.getStationName())
                .dataTime(dto.getDataTime())
                .so2Grade(dto.getSo2Grade())
                .khaiValue(dto.getKhaiValue())
                .so2Value(dto.getSo2Value())
                .coValue(dto.getCoValue())
                .pm10Value(dto.getPm10Value())
                .o3Grade(dto.getO3Grade())
                .khaiGrade(dto.getKhaiGrade())
                .pm25Value(dto.getPm25Value())
                .no2Grade(dto.getNo2Grade())
                .pm25Grade(dto.getPm25Grade())
                .coGrade(dto.getCoGrade())
                .no2Value(dto.getNo2Value())
                .pm10Grade(dto.getPm10Grade())
                .o3Value(dto.getO3Value())
                .build();
        return entity;
    }

    //Entity -> Dto 메서드
    default RealTimeAirDto ToDto(RealTimeAirEntity entity) {
        RealTimeAirDto dto = RealTimeAirDto.builder()
                .stationName(entity.getStationName())
                .dataTime(entity.getDataTime())
                .so2Grade(entity.getSo2Grade())
                .khaiValue(entity.getKhaiValue())
                .so2Value(entity.getSo2Value())
                .coValue(entity.getCoValue())
                .pm10Value(entity.getPm10Value())
                .o3Grade(entity.getO3Grade())
                .khaiGrade(entity.getKhaiGrade())
                .pm25Value(entity.getPm25Value())
                .no2Grade(entity.getNo2Grade())
                .pm25Grade(entity.getPm25Grade())
                .coGrade(entity.getCoGrade())
                .no2Value(entity.getNo2Value())
                .pm10Grade(entity.getPm10Grade())
                .o3Value(entity.getO3Value())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
