package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Compte;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    public Compte save(Compte compte) {
        return compteRepository.save(compte);
    }
}
