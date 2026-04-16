package br.com.gustavo.reservaja.controller;

import br.com.gustavo.reservaja.dto.ReservationResponseDto;
import br.com.gustavo.reservaja.dto.reservation.ReservationRequestDto;
import br.com.gustavo.reservaja.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService service;

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationRequestDto dto) {
        ReservationResponseDto response = service.createReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
