package br.com.gustavo.reservaja.exception;

public record ErrorResponse(java.time.LocalDateTime now, int value, String status, String message) {
}
