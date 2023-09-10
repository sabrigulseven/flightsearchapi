package com.sabrigulseven.flight.controller;

import com.sabrigulseven.flight.api.AdminApi;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@RateLimiter(name = "basic")
public class AdminController implements AdminApi {

    @GetMapping("/admin-data")
    public ResponseEntity<String> getAdminData() {
        return ResponseEntity.ok("GET:: admin controller");
    }
}
