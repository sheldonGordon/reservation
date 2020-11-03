package fr.chatelain.reservation.reservation.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Chambre;
import fr.chatelain.reservation.reservation.back.repository.ChambreRepository;

@Service
public class ChambreService {
    @Autowired
    private ChambreRepository chambreRepository;

    public Chambre save(Chambre chambre) {
        return chambreRepository.save(chambre);
    }
}
