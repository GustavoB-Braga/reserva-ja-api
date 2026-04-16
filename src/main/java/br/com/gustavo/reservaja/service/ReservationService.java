package br.com.gustavo.reservaja.service;

import br.com.gustavo.reservaja.dto.ReservationResponseDto;
import br.com.gustavo.reservaja.dto.reservation.ReservationRequestDto;
import br.com.gustavo.reservaja.model.Reservation;
import br.com.gustavo.reservaja.model.Room;
import br.com.gustavo.reservaja.model.User;
import br.com.gustavo.reservaja.repository.ReservationRepository;
import br.com.gustavo.reservaja.repository.RoomRepository;
import br.com.gustavo.reservaja.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


        Room room = roomRepository.findById(dto.roomId()).orElseThrow(() -> new RuntimeException("Room not found"));
        User user = userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User not found"));

        if (dto.startAt().isAfter(dto.endAt())) {
            throw new RuntimeException("Invalid time range");
        }

        if (repository.existsByRoomAndStartAtLessThanAndEndAtGreaterThan(room, dto.endAt(), dto.startAt())) {
            throw new RuntimeException("Time is already booked");
        }


        Reservation reservation = new Reservation(user, room, dto.startAt(), dto.endAt());

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


//TODO: corrigir o caso extremo em que endAt == startAt está sendo considerado um conflito (EDGE CASE)