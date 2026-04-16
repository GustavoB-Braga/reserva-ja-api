package br.com.gustavo.reservaja.controller;

import br.com.gustavo.reservaja.dto.ReservationResponseDto;
import br.com.gustavo.reservaja.dto.reservation.ReservationRequestDto;
import br.com.gustavo.reservaja.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> listReservations() {
        List<ReservationResponseDto> response = service.listAllReservations();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
