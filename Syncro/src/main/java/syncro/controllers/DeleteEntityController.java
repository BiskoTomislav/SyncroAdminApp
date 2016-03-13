package syncro.controllers;
 
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import syncro.dao.mongo.ApplicationUsersMongoDAO;
import syncro.dao.mongo.ContactsMongoDAO;
import syncro.dao.mongo.PartnersMongoDAO;
import syncro.dao.mongo.ProjectsMongoDAO;
import syncro.dao.mongo.UsersMongoDAO;
import syncro.entities.ApplicationUser;
import syncro.entities.Contact;
import syncro.entities.Partner;
import syncro.entities.Project;
import syncro.entities.User;
import syncro.entities.User.ProjectInfo;
 
@Controller
class DeleteEntityController {
 
    @RequestMapping("/deleteUser")
    String deleteUser(
    		@RequestParam(value="userID", required=true) String userID,
    		Model model) {
    	
    	String error = "";
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();
    	
    	User user = new User();
		user.set_id(userID);

		try {
			usersDao.deleteUser(user);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	List<User> users = usersDao.findAllUsers();
    	
    	model.addAttribute("users", users);
    	model.addAttribute("error", error);
    	
        return "lists/users_list";
    }
    
    @RequestMapping("/deleteApplicationUser")
    String deleteApplicationUser(
    		@RequestParam(required=true) String userID,
    		Model model) {
    	
    	String error = "";
    	
    	ApplicationUsersMongoDAO applicationUsersDao = new ApplicationUsersMongoDAO();
    	
    	ApplicationUser applicationUser = new ApplicationUser();
    	applicationUser.set_id(userID);

		try {
			applicationUsersDao.deleteApplicationUser(applicationUser);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	model.addAttribute("applicationUser", new ApplicationUser());
    	model.addAttribute("applicationUsers", applicationUsersDao.findAllApplicationUsers());
    	
    	model.addAttribute("error", error);
    	
        return "administration";
    }
    
    @RequestMapping(value="/deleteProjectToUser", method=RequestMethod.POST)
    String deleteProjectToUser(

        	@RequestParam(value="id", required=false) String id, 
    		@RequestParam(value="projectId", required=true) String projectId,
    		Model model) {
    	
    	String error = "";
    	
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();

		
    	User user = usersDao.findUser(id);
    	
		User.ProjectInfo pi = new ProjectInfo();
		ProjectsMongoDAO projectDAO = new ProjectsMongoDAO();
		Project p = projectDAO.findProject(projectId);
		
		pi.setProject(p);
    	
    	user.getProjects().remove(pi);
    	usersDao.saveUser(user);
    	
    	model.addAttribute("userProjects", user.getProjects());
    	model.addAttribute("error", error);
    	
        return "lists/user_projects_list";
    }
    
    @RequestMapping("/deletePartner")
    String deletePartner(
    		@RequestParam(value="partnerID", required=true) String partnerID,
    		Model model) {
    	
    	String error = "";
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();
    	
    	Partner partner = new Partner();
    	partner.set_id(partnerID);

		try {
			partnersDao.deletePartner(partner);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	List<Partner> partners = partnersDao.findAllPartners();
    	
    	model.addAttribute("partners", partners);
    	model.addAttribute("error", error);
    	
        return "lists/partners_list";
    }
    
    @RequestMapping(value="/deleteProjectToPartner", method=RequestMethod.POST)
    String deleteProjectToPartner(

        	@RequestParam(value="id", required=false) String id, 
    		@RequestParam(value="projectId", required=true) String projectId,
    		Model model) {
    	
    	String error = "";
    	
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();

		
    	Partner partner = partnersDao.findPartner(id);
    	
    	Partner.ProjectInfo pi = new Partner.ProjectInfo();
		ProjectsMongoDAO projectDAO = new ProjectsMongoDAO();
		Project p = projectDAO.findProject(projectId);
		
		pi.setProject(p);
    	
		partner.getProjects().remove(pi);
		partnersDao.savePartner(partner);
    	
    	model.addAttribute("partnerProjects", partner.getProjects());
    	model.addAttribute("error", error);
    	
        return "lists/partner_projects_list";
    }
    
    @RequestMapping("/deleteContact")
    String deleteContact(
    		@RequestParam(value="contactID", required=true) String contactID,
    		Model model) {
    	
    	String error = "";
    	
    	ContactsMongoDAO contactsDao = new ContactsMongoDAO();
    	
    	Contact contact = new Contact();
    	contact.set_id(contactID);

		try {
			contactsDao.deleteContact(contact);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	List<Contact> contacts = contactsDao.findAllContacts();
    	
    	model.addAttribute("contacts", contacts);
    	model.addAttribute("error", error);
    	
        return "lists/contacts_list";
    }
    
    @RequestMapping("/deleteProject")
    String deleteProject(
    		@RequestParam(value="projectID", required=true) String projectID,
    		Model model) {
    	
    	String error = "";
    	
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	
    	Project project = new Project();
    	project = projectsDao.findProject(projectID);

		try {
			projectsDao.deleteProject(project);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	List<Project> projects = projectsDao.findAllProjects();
		if( "project".equals(project.getSubtype()) ) {
			projects = projectsDao.findAllWithTypeOfProject();
		}
    	
		if( "partnership".equals(project.getSubtype()) ) {
			projects = projectsDao.findAllWithTypeOfPartnerships();
		}
    	
    	model.addAttribute("projects", projects);
    	model.addAttribute("error", error);
    	
        return "lists/projects_list";
    }

}