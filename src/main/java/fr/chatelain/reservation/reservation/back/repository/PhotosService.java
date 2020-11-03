package fr.chatelain.reservation.reservation.back.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.chatelain.reservation.reservation.back.entities.Photos;

@Service
public class PhotosService {

    @Autowired
    private PhotosRepository photosRepository;

    public Photos save(Photos photos) {
        return photosRepository.save(photos);
    }
}
