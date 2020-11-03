package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.CodePromo;

@Service
public class CodePromoService {

    @Autowired
    private CodePromoRepository codePromoRepository;

    public CodePromo save(CodePromo codePromo) {
        return codePromoRepository.save(codePromo);
    }
}
