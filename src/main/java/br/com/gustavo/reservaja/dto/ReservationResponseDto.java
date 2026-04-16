package br.com.gustavo.reservaja.dto;

import java.time.LocalDateTime;

public record ReservationResponseDto(Long id, Long roomId, LocalDateTime startAt, LocalDateTime endAt) {
}
