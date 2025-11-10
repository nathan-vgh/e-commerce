package com.vriur.gateway_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/default")
    public Mono<ResponseEntity<String>> defaultFallback() {
        return Mono.just(ResponseEntity.status(503).body("Service is currently unavailable. Please try again later."));
    }
}
