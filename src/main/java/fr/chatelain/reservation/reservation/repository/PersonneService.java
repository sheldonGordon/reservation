package fr.chatelain.reservation.reservation.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.entities.Personne;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public void save(Personne personne) {
        personneRepository.save(personne);
    }
}
