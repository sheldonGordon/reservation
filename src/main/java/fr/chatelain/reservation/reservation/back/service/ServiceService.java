package fr.chatelain.reservation.reservation.back.service;

import java.util.List;

import org.apache.commons.collections.IteratorUtils;
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

    public List<Service> findAll() {
        Iterable<Service> it = serviceRepository.findAll();
        return IteratorUtils.toList(it.iterator());
    }
}
