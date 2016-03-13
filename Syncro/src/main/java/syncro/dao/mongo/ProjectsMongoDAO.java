package syncro.dao.mongo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import syncro.dao.config.SpringMongoConfig;
import syncro.entities.Project;
import syncro.entities.User;

public class ProjectsMongoDAO {

	private static ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	private static MongoOperations mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");
    
    public java.util.List<Project> findAllProjects() {		
		return mongoOperation.findAll(Project.class);
    }
    
    public java.util.List<Project> findAllWithTypeOfPartnerships() {	
    	
    	Query query = new Query();
    	
    	query.addCriteria(Criteria.where("SUBTYPE").is("partnership"));
    	
		return mongoOperation.find(query, Project.class);
    }
    
    public java.util.List<Project> findAllWithTypeOfProject() {	
    	
    	Query query = new Query();
    	
    	query.addCriteria(Criteria.where("SUBTYPE").is("project"));
    	
		return mongoOperation.find(query, Project.class);
    }
    
    public java.util.List<Project> findAllProjectsWithoutUserInThem(User user) {		
		return mongoOperation.findAll(Project.class);
    }
    
    public Project findProject(String id) {
    	return mongoOperation.findById(id, Project.class);
    }
    
    public void saveProject (Project project) {
    	mongoOperation.save(project);
    }
    
    public void deleteProject (Project project) {
    	mongoOperation.remove(project);
    }
}
