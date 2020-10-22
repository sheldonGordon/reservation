package fr.chatelain.reservation.reservation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.entities.Personne;

@Service
public interface PersonneRepository extends CrudRepository<Personne, String> {

}
