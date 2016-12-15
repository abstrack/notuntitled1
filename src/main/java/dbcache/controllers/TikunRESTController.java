package dbcache.controllers;

import dbcache.model.Tikun;
import dbcache.repo.TikunRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.xml.ws.soap.Addressing;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by mikhailrofel on 30/11/2016.
 */
@RestController
@RequestMapping("/tikunim")
public class TikunRESTController {

    @Autowired
    TikunRepo tikunRepo;
    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping
    public List<Tikun> getTikunim(){
       return tikunRepo.findAll();
    }
    @DeleteMapping("/{id1}")

    public void deleteTikun(@PathVariable String id1){
      mongoTemplate.findAndRemove(new Query(where("id1").is(id1)),Tikun.class);

    }

    @PostMapping("/{num}")

    public void addTikun(@PathVariable String num){
    Tikun tik = new Tikun((String) new ObjectId().toString(), num,LocalDate.now(),"","","","","","","","","","","");
    tikunRepo.save(tik);
    }
}
