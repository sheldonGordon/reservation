package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Option;

@Service
public class OptionService {

    @Autowired
    private OptionRepository optionRepository;

    public Option save(Option option) {
        return optionRepository.save(option);
    }
}
