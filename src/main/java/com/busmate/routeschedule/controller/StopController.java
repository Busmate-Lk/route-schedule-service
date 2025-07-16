package com.busmate.routeschedule.controller;

import com.busmate.routeschedule.dto.request.StopRequest;
import com.busmate.routeschedule.dto.response.StopResponse;
import com.busmate.routeschedule.service.StopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stops")
@RequiredArgsConstructor
public class StopController {
    private final StopService stopService;

    @PostMapping
    public ResponseEntity<StopResponse> createStop(@Valid @RequestBody StopRequest request, Authentication authentication) {
        String userId = authentication.getName();
        StopResponse response = stopService.createStop(request, userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StopResponse> getStopById(@PathVariable UUID id) {
        StopResponse response = stopService.getStopById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StopResponse>> getAllStops() {
        List<StopResponse> responses = stopService.getAllStops();
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StopResponse> updateStop(@PathVariable UUID id, @Valid @RequestBody StopRequest request, Authentication authentication) {
        String userId = authentication.getName();
        StopResponse response = stopService.updateStop(id, request, userId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStop(@PathVariable UUID id) {
        stopService.deleteStop(id);
        return ResponseEntity.noContent().build();
    }
}