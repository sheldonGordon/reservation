package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Option;

public interface OptionRepository extends CrudRepository<Option, String> {

}
