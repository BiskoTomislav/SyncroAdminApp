package syncro.dao.mongo;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import syncro.dao.config.SpringMongoConfig;
import syncro.entities.ApplicationUser;

public class ApplicationUsersMongoDAO {

	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	private static MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    
    public java.util.List<ApplicationUser> findAllApplicationUsers() {		
		return mongoOperation.findAll(ApplicationUser.class);
    }
    
    public ApplicationUser findApplicationUser(String id) {
    	return mongoOperation.findById(id, ApplicationUser.class);
    }
    
    public List<ApplicationUser> findApplicationUserByUsername(String username) {
    	
    	Query query = new Query();
    	
    	query.addCriteria(Criteria.where("USERNAME").is(username));
    	
    	return mongoOperation.find(query, ApplicationUser.class);
    }
    
    public void saveApplicationUser (ApplicationUser user) {
    	mongoOperation.save(user);
    }
    
    public void deleteApplicationUser (ApplicationUser user) {
    	mongoOperation.remove(user);
    }
}
