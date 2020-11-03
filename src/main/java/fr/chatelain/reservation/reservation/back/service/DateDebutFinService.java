package fr.chatelain.reservation.reservation.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.DateDebutFin;
import fr.chatelain.reservation.reservation.back.repository.DateDebutFinRepository;

@Service
public class DateDebutFinService {

    @Autowired
    private DateDebutFinRepository dateDebutFinRepository;

    public DateDebutFin save(DateDebutFin dateDebutFin) {
        return dateDebutFinRepository.save(dateDebutFin);
    }
}
