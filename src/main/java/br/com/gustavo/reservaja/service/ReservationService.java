package br.com.gustavo.reservaja.service;

import br.com.gustavo.reservaja.dto.ReservationResponseDto;
import br.com.gustavo.reservaja.dto.reservation.ReservationRequestDto;
import br.com.gustavo.reservaja.exception.BusinessException;
import br.com.gustavo.reservaja.exception.ResourceNotFoundException;
import br.com.gustavo.reservaja.model.Reservation;
import br.com.gustavo.reservaja.model.Room;
import br.com.gustavo.reservaja.model.User;
import br.com.gustavo.reservaja.repository.ReservationRepository;
import br.com.gustavo.reservaja.repository.RoomRepository;
import br.com.gustavo.reservaja.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    UserRepository userRepository;

    public ReservationResponseDto createReservation(ReservationRequestDto dto) {
        LocalDateTime start = dto.startAt().withNano(0);
        LocalDateTime end = dto.endAt().withNano(0);

        Room room = roomRepository.findById(dto.roomId()).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        User user = userRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (start.isAfter(end)) {
            throw new BusinessException("Invalid time range");
        }

        if (repository.existsByRoomAndStartAtLessThanAndEndAtGreaterThan(room, end, start)) {
            throw new BusinessException("Time is already booked");
        }


        Reservation reservation = new Reservation(user, room, start, end);

        Reservation saved = repository.save(reservation);
        return new ReservationResponseDto(saved.getId(), saved.getRoom().getId(), saved.getStartAt(), saved.getEndAt());
    }

    public List<ReservationResponseDto> listAllReservations() {
        List<ReservationResponseDto> listReservations = repository.findAll()
                .stream()
                .map(r -> new ReservationResponseDto(r.getId(), r.getRoom().getId(), r.getStartAt(), r.getEndAt()))
                .collect(Collectors.toList());

        return listReservations;
    }
}
