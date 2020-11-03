package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, String> {

}
