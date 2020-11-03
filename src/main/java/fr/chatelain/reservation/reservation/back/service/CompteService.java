package fr.chatelain.reservation.reservation.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Compte;
import fr.chatelain.reservation.reservation.back.repository.CompteRepository;

@Service
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    public Compte save(Compte compte) {
        return compteRepository.save(compte);
    }
}
