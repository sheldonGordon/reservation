package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;

import fr.chatelain.reservation.reservation.back.entities.Service;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public Service save(Service service) {
        return serviceRepository.save(service);
    }
}
