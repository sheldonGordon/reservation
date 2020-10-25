package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Password;

public interface PasswordRepository extends CrudRepository<Password, String> {

}
