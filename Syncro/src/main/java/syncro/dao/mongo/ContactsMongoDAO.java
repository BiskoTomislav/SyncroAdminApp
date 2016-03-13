package syncro.dao.mongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import syncro.dao.config.SpringMongoConfig;
import syncro.entities.Contact;

public class ContactsMongoDAO{

	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	private static MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    
    public java.util.List<Contact> findAllContacts() {		
		return mongoOperation.findAll(Contact.class);
    }
    
    
    public Contact findContact(String id) {
    	return mongoOperation.findById(id, Contact.class);
    }
    
    public void saveContact (Contact contact) {
    	mongoOperation.save(contact);
    }
    
    public void deleteContact (Contact contact) {
    	mongoOperation.remove(contact);
    }
}
