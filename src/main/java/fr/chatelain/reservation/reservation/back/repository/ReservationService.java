package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Reservation;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
