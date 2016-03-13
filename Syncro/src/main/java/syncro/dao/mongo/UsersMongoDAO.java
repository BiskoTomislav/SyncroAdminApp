package syncro.dao.mongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import syncro.dao.config.SpringMongoConfig;
import syncro.entities.User;

public class UsersMongoDAO {

	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	private static MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    
    public java.util.List<User> findAllUsers() {		
		return mongoOperation.findAll(User.class);
    }
    
    public User findUser(String id) {
    	return mongoOperation.findById(id, User.class);
    }
    
    public void saveUser (User user) {
    	mongoOperation.save(user);
    }
    
    public void deleteUser (User user) {
    	mongoOperation.remove(user);
    }
}
