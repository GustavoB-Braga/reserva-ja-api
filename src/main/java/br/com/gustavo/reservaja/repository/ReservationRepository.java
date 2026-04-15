package br.com.gustavo.reservaja.repository;

import br.com.gustavo.reservaja.model.Reservation;
import br.com.gustavo.reservaja.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByRoomAndStartAtLessThanAndEndAtGreaterThan(Room room, LocalDateTime endAt, LocalDateTime startAt);
}
