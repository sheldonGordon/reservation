package fr.chatelain.reservation.reservation.beans;

import javax.faces.view.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.chatelain.reservation.reservation.back.service.ChambreService;

@Component
@ViewScoped
public class ServiceBean {
    @Autowired
    private ChambreService chambreService;

    public int nbChambre() {
        return chambreService.findAll().size();
    }
}
