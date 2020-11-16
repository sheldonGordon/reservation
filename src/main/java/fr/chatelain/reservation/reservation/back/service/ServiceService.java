package fr.chatelain.reservation.reservation.back.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import fr.chatelain.reservation.reservation.back.entities.Service;
import fr.chatelain.reservation.reservation.back.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    public Set<Service> findAll() {
        Iterable<Service> it = serviceRepository.findAll();
        return new HashSet<Service>((Collection) it);
    }
}
