package dbcache.repo;

import dbcache.Application;
import dbcache.model.Tikun;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Meehaeel on 24-Nov-16.
 */

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(Application.class)
public class TikunRepoTest {
    @Autowired
    TikunRepo tikunRepo;
    @Test
    public void runTest (){
        tikunRepo.save(new Tikun());
    }
}