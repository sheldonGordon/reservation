package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Chambre;

@Service
public class ChambreService {
    @Autowired
    private ChambreRepository chambreRepository;

    public Chambre save(Chambre chambre) {
        return chambreRepository.save(chambre);
    }
}
