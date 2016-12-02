package dbcache.controllers;

import dbcache.model.Tikun;
import dbcache.repo.TikunRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.xml.ws.soap.Addressing;
import java.util.List;

/**
 * Created by mikhailrofel on 30/11/2016.
 */
@RestController
@RequestMapping("/tikunim")
public class TikunRESTController {

    @Autowired
    TikunRepo tikunRepo;

    @RequestMapping
    public List<Tikun> getTikunim(){
       return tikunRepo.findAll();
    }
}
