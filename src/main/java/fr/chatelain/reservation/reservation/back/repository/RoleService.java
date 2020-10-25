package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Role;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findRoleByLibelle(String libelle) {
        return roleRepository.findAllByLibelle(libelle).get(0);
    }
}
