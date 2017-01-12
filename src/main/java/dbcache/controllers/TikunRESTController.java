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
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
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
        List<Tikun> tikunim = tikunRepo.findAll();
        Collections.reverse(tikunim);
return tikunim;

    }
    @DeleteMapping("/{id1}")
    public void deleteTikun(@PathVariable String id1){
      mongoTemplate.findAndRemove(new Query(where("id1").is(id1)),Tikun.class);

    }

    @PostMapping()
    public void addTikun(@RequestBody Tikun tikun){
   // Tikun tik = new Tikun((String) new ObjectId().toString(), num,LocalDate.now(),"","","","","","","","","","","");
       try{
           String cId = tikun.getId1().toString();
           mongoTemplate.findAndRemove(new Query(where("id1").is(cId)), Tikun.class);
           tikunRepo.save(tikun);
       }
       catch (NullPointerException e){
           LocalDate dateN = LocalDate.now();
           tikun.setDate(dateN);

           StringBuilder sb = new StringBuilder();
           sb.append("");
           int i = dateN.getMonthValue();
           if(i<=9){
               sb.append("0"+i);
           }
          else{ sb.append(i);}
           String strI = sb.toString();
           tikun.setMonth(strI);
           String hodStart = "01"+"."+strI+"."+dateN.getYear();
           LocalDate monStart = LocalDate.parse(hodStart, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
           String hodEnd = dateN.lengthOfMonth()+"."+strI+"."+dateN.getYear();
           LocalDate monEnd = LocalDate.parse(hodEnd, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
           Query query = new Query();
           query.addCriteria(Criteria.where ("date").gte(monStart).lte(monEnd));
           Long counthod = mongoTemplate.count(query,Tikun.class);
           StringBuilder sbn = new StringBuilder();
           sbn.append("");
           sbn.append(counthod+1);
           tikun.setNumber(sbn.toString());
           tikun.setId1( new ObjectId().toString());
           tikunRepo.save(tikun);
       }

    }
    @GetMapping("/edit/{id1}")
    public Tikun editTikun(@PathVariable String id1){
     List  <Tikun> tikim=  mongoTemplate.find(new Query(where("id1").is(id1)), Tikun.class);
    try{ Tikun tik = tikim.get(0);
        System.out.println(tik.getIMEI());
     return tik;}
     catch (IndexOutOfBoundsException e){
         System.out.println("no such rep");
        return null;
     }
    }
}
