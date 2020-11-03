package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Chambre;

public interface ChambreRepository extends CrudRepository<Chambre, String> {

}
