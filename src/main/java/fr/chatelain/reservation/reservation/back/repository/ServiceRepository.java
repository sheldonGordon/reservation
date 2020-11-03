package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Service;

public interface ServiceRepository extends CrudRepository<Service, String> {

}
