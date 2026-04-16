package br.com.gustavo.reservaja.dto.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ReservationRequestDto(
        @NotNull Long roomId,
        @NotNull @Future LocalDateTime startAt,
        @NotNull @Future LocalDateTime endAt) {

}
