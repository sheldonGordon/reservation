package fr.chatelain.reservation.reservation.back.service;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
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

    public List<Chambre> findAll() {
        Iterable<Chambre> it = chambreRepository.findAll();
        return IteratorUtils.toList(it.iterator());
    }
}
