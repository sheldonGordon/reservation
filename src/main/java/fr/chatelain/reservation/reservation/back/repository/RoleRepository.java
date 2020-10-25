package fr.chatelain.reservation.reservation.back.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.chatelain.reservation.reservation.back.entities.Role;

public interface RoleRepository extends CrudRepository<Role, String> {

    public List<Role> findAllByLibelle(String libelle);
}
