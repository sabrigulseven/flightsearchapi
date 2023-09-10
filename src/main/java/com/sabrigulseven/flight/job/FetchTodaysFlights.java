package com.sabrigulseven.flight.job;

import com.sabrigulseven.flight.constant.Constants;
import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Component
public class FetchTodaysFlights {

    private final FlightService flightService;
    private static final Logger logger = LoggerFactory.getLogger(FetchTodaysFlights.class);
    public FetchTodaysFlights(FlightService flightService) {
        this.flightService = flightService;
    }

    @Scheduled(cron = "0 0 0 * * ?")  // Her gün gece yarısında çalışır
    public void processDailyFlights() {
        logger.info("Scheduled task for processing daily flights started.");
        List<FlightDto> savedFlights = flightService.saveFlights(fetchTodaysFlightsFromApi());
        logger.info("Scheduled task for processing daily flights completed. Saved {} flights.", savedFlights.size());
    }

    private List<FlightDto> fetchTodaysFlightsFromApi() {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = Constants.API_URL + "/flights/search?departureDate=" + LocalDate.now(); // Bugünkü uçuşları getiren API endpoint'i
        ResponseEntity<List<FlightDto>> response = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<FlightDto>>() {
                }
        );
        return response.getBody();
    }
}
