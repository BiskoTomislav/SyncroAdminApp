package syncro.dao.mongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import syncro.dao.config.SpringMongoConfig;
import syncro.entities.Partner;

public class PartnersMongoDAO {

	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	private static MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    
    public java.util.List<Partner> findAllPartners() {		
		return mongoOperation.findAll(Partner.class);
    }
    
    public Partner findPartner(String id) {
    	return mongoOperation.findById(id, Partner.class);
    }
    
    public void savePartner (Partner partner) {
    	mongoOperation.save(partner);
    }
    
    public void deletePartner (Partner partner) {
    	mongoOperation.remove(partner);
    }
}
