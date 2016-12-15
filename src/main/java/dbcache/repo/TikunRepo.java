package dbcache.repo;

import dbcache.model.Tikun;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Created by Meehaeel on 24-Nov-16.
 */
public interface TikunRepo extends MongoRepository<Tikun,ObjectId>{
    @Query(value = "{'id1' : ?0}", delete = true)
    public void deleteBy(String id1);
}
