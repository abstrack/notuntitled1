package dbcache;

import dbcache.model.Tikun;
import dbcache.repo.TikunRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static dbcache.util.Quickstart.init;

@SpringBootApplication
public class Application {
    @Autowired
    TikunRepo tikunRepo;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Tikun getTikun() throws Exception {
       List<Tikun> tikuns = init();
        tikunRepo.deleteAll();
        tikunRepo.insert(tikuns);
      return new Tikun();
   }
}
