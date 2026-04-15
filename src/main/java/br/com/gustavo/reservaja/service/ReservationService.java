package br.com.gustavo.reservaja.service;

import br.com.gustavo.reservaja.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository repository;

    public void createReservation(/*criar DTO*/) {

//        if (repository.existsByRoomAndStartAtLessThanAndEndAtGreaterThan()){
//            throw new RuntimeException("HORÁRIO RESERVADO");
//        }

    }
}
