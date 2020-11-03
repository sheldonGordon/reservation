package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Photos;

public interface PhotosRepository extends CrudRepository<Photos, String> {

}
