package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Personne;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public void save(Personne personne) {
        personneRepository.save(personne);
    }
}
