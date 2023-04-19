package com.weatherwhere.airservice.service.airforecast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.weatherwhere.airservice.domain.airforecast.AirForecastEntity;
import com.weatherwhere.airservice.domain.airforecast.AirForecastId;
import com.weatherwhere.airservice.dto.airforecast.AirForecastDTO;
import com.weatherwhere.airservice.dto.airforecast.SearchAirForecastDTO;
import com.weatherwhere.airservice.dto.ResultDTO;
import com.weatherwhere.airservice.repository.airforecast.AirForecastRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class GetAirForecastDataServiceImpl implements GetAirForecastDataService{
    private final AirForecastRepository airForecastRepository;

    /*
    @Override
    @Transactional
    public ResultDTO<AirForecastDTO> getAirForecastOneDay(SearchAirForecastDTO searchAirForecastDto){
        AirForecastId airForecastId = new AirForecastId(searchAirForecastDto.getBaseDate(),searchAirForecastDto.getCity());
        AirForecastDTO data = new AirForecastDTO();
        try {
            AirForecastEntity airForecastEntity = airForecastRepository.findByAirForecastId(airForecastId)
                .orElseThrow(() -> new NoSuchElementException());
            data = entityToDto(airForecastEntity);

            log.info("주간예보: {}",data);
        }catch (Exception e) {
            e.getStackTrace();
            log.error(e.getMessage());
        }
        return ResultDTO.of(HttpStatus.OK.value(),"대기 주간예보를 조회하는데 성공하였습니다.",data);
    }
*/

    // 해당 위치 5일 대기오염 주간예보 DB 가져오기
    @Override
    @Transactional
    public ResultDTO<List<AirForecastDTO>> getFiveDaysDataOfLocation(SearchAirForecastDTO searchAirForecastDto) {
        AirForecastId airForecastId = new AirForecastId();
        airForecastId.setBaseDate(searchAirForecastDto.getBaseDate());
        airForecastId.setCity(searchAirForecastDto.getCity());

        List<AirForecastDTO> fiveDaysData = new ArrayList<>();
        try {
            for (int i=0; i<5; i++) {

                LocalDate searchDate = airForecastId.getBaseDate().plusDays(i);// 호출한 LocalDate 객체에 일(day)이 더해진 LocalDate 객체를 반환합니다.
                AirForecastId searchId = new AirForecastId(searchDate,airForecastId.getCity());

                // db에서 해당 날짜가 없을 때  예외처리!
                AirForecastEntity airForecastEntity = airForecastRepository.findByAirForecastId(searchId)
                    .orElseThrow(() -> new NoSuchElementException());
                fiveDaysData.add(entityToDto(airForecastEntity));
            }
            log.info("5일의 주간예보: {}",fiveDaysData);

        } catch (NoSuchElementException e) {
            // db에서 찾는 데이터 없을 경우
            e.getStackTrace();
            log.warn("db에 5일의 주간예보 데이터 없음");
        } catch (Exception e) {
            e.getStackTrace();
            log.warn(e.getMessage());
        }
        return ResultDTO.of(HttpStatus.OK.value(),"대기 주간예보 5일 데이터를 조회하는데 성공하였습니다.", fiveDaysData);
    }
}
