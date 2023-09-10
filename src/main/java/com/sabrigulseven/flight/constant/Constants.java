package com.sabrigulseven.flight.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String API_URL;

    @Value("${flight-api.api-url}")
    public void setApiUrl(String apiUrl){
        Constants.API_URL=apiUrl;
    }

}
