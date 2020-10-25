package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Compte;

public interface CompteRepository extends CrudRepository<Compte, String> {

}
