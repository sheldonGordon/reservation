package fr.chatelain.reservation.reservation.back.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Photos;
import fr.chatelain.reservation.reservation.back.repository.PhotosRepository;

@Service
public class PhotosService {

    @Autowired
    private PhotosRepository photosRepository;

    public Photos save(Photos photos) {
        return photosRepository.save(photos);
    }
}
