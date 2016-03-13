package syncro.controllers;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import syncro.dao.mongo.ContactsMongoDAO;
import syncro.dao.mongo.PartnersMongoDAO;
import syncro.dao.mongo.ProjectsMongoDAO;
import syncro.dao.mongo.UsersMongoDAO;
import syncro.entities.Contact;
import syncro.entities.Partner;
import syncro.entities.Project;
import syncro.entities.User;
import syncro.entities.User.ProjectInfo;
 
@Controller
class EditEntityController {
 
    @RequestMapping(value="/editUser", method = RequestMethod.POST)
    String editUser(
    	@RequestParam(value="_id", required=true) String _id, 
   		@RequestParam("profile_img") MultipartFile profileImg,
   		User user,
    	Model model) {
    	
    	user.set_id(_id);
    	
    	String error = "";

    	UsersMongoDAO usersDao = new UsersMongoDAO();
    	User meta = usersDao.findUser(_id);
    	user.setProfileImage(meta.getProfileImage());
    	user.setProjects((meta.getProjects()));
    	
		byte[] imageBytes;
		try {
			imageBytes = profileImg.getBytes();
			final String encoded = Base64.getEncoder().encodeToString(imageBytes);
			
			if(imageBytes.length > 0) {
				user.setProfileImage("data:image/gif;base64," + encoded);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		
		try {
			usersDao.saveUser(user);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	List<User> users = usersDao.findAllUsers();
    	
    	model.addAttribute("users", users);
    	model.addAttribute("error", error);
    	
        return "lists/users_list";
    }

    @RequestMapping(value="/addProjectToUser", method=RequestMethod.POST)
    String addProjectToUser(

    	@RequestParam(value="userId", required=false) String userId, 
   		@RequestParam(value="projectId", required=false) String projectId, 
   		@RequestParam(value="userProjectStatus", required=false) String userProjectStatus, 
   		@RequestParam(value="userProjectInfo", required=false) String userProjectInfo,
    	Model model) {
    	
    	String error = "";
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();

		
    	User user = usersDao.findUser(userId);
    	
		User.ProjectInfo pi = new ProjectInfo();
		ProjectsMongoDAO projectDAO = new ProjectsMongoDAO();
		Project p = projectDAO.findProject(projectId);
		
		pi.setProject(p);
		pi.setInfo(userProjectInfo);
		pi.setStatus(userProjectStatus);
    	
    	user.getProjects().add(pi);
    	usersDao.saveUser(user);
    	
    	model.addAttribute("userProjects", user.getProjects());
    	model.addAttribute("error", error);
    	
        return "lists/user_projects_list";
    }
    
    @RequestMapping(value="/editUsersProject", method=RequestMethod.POST)
    String editUsersProject(

    	@RequestParam(value="id", required=false) String id, 
   		@RequestParam(value="projectId", required=false) String projectId, 
   		@RequestParam(value="userProjectStatus", required=false) String userProjectStatus, 
   		@RequestParam(value="userProjectInfo", required=false) String userProjectInfo,
    	Model model) {
    	
    	String error = "";
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();

		
    	User user = usersDao.findUser(id);
    	
		User.ProjectInfo pi = new ProjectInfo();
		ProjectsMongoDAO projectDAO = new ProjectsMongoDAO();
		Project p = projectDAO.findProject(projectId);
		
		pi.setProject(p);
    	
		pi = user.getProjects().get(user.getProjects().indexOf(pi));
		
		pi.setInfo(userProjectInfo);
		pi.setStatus(userProjectStatus);
		
    	usersDao.saveUser(user);
    	
    	model.addAttribute("userProjects", user.getProjects());
    	model.addAttribute("error", error);
    	
        return "lists/user_projects_list";
    }
    
    @RequestMapping("/editProject")
    String editProject(
    		@RequestParam(value="unique_value", required=true) String uniqueValue, 
    		Project project, 
    		Model model) {
    	
    	String error = "";
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	project.set_id(uniqueValue);

		try {
			projectsDao.saveProject(project);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
		List<Project > projects = new ArrayList<>();
		if( "project".equals(project.getSubtype()) ) {
			projects = projectsDao.findAllWithTypeOfProject();
		}
    	
		if( "partnership".equals(project.getSubtype()) ) {
			projects = projectsDao.findAllWithTypeOfPartnerships();
		}
		
    	model.addAttribute("projects", projects);
    	model.addAttribute("error", error);
    	
        return "lists/" + project.getSubtype() + "s_list";
    }
    
    @RequestMapping("/editContact")
    String editContact(
    		@RequestParam(value="unique_value", required=true) String uniqueValue, 
    		@RequestParam(value="companyName", required=false) String companyName, 
    		@RequestParam(value="country", required=false) String country, 
    		@RequestParam(value="address", required=false) String address, 
    		@RequestParam(value="email", required=false) String email,
    		@RequestParam(value="phone", required=false) String phone, 
    		@RequestParam(value="webSite", required=false) String webSite, 
    		
    		@RequestParam(value="facebook", required=false) String facebook, 
    		@RequestParam(value="personName", required=false) String personName, 
    		@RequestParam(value="personRole", required=false) String personRole, 
    		
    		@RequestParam(value="personEmail", required=false) String personEmail,
    		@RequestParam(value="personPhone", required=false) String personPhone, 
    		
    		Model model) {
    	
    	String error = "";
    	
    	ContactsMongoDAO contactsDao = new ContactsMongoDAO();
    	
    	Contact contact = new Contact();

    	contact.set_id(uniqueValue);
    	contact.getData().setCompanyName(companyName);
    	contact.getData().setCountry(country);
    	contact.getData().setAddress(address);
    	contact.getData().setEmail(email);
    	contact.getData().setPhone(phone);
    	contact.getData().setWebSite(webSite);
    	contact.getData().setFacebook(facebook);

    	
    	contact.getContactPerson().setPersonName(personName);
    	contact.getContactPerson().setPersonRole(personRole);
    	contact.getContactPerson().setPersonEmail(personEmail);
    	contact.getContactPerson().setPersonPhone(personPhone);
		

		
		try {
			contactsDao.saveContact(contact);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	List<Contact > contacts = contactsDao.findAllContacts();
    	
    	model.addAttribute("contacts", contacts);
    	model.addAttribute("error", error);
    	
        return "lists/contacts_list";
    }

    @RequestMapping("/editPartner")
    String editPartner(
    		@RequestParam(value="_id", required=true) String _id, 
    		Partner partner,
    		Model model) {
    	partner.set_id(_id); 
    	String error = "";
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();
    	
    	Partner meta = partnersDao.findPartner(_id);
    	partner.setProjects((meta.getProjects()));
		
		try {
			partnersDao.savePartner(partner);
		} catch (Exception e) {
			error = e.getMessage();
		}
		
    	List<Partner > partners = partnersDao.findAllPartners();
    	
    	model.addAttribute("partners", partners);
    	model.addAttribute("error", error);
    	
        return "lists/partners_list";
    }
    
    @RequestMapping(value="/addProjectToPartner", method=RequestMethod.POST)
    String addProjectToPartner(

    	@RequestParam(value="partnerId", required=false) String partnerId, 
   		@RequestParam(value="projectId", required=false) String projectId, 
   		@RequestParam(value="partnerProjectStatus", required=false) String partnerProjectStatus, 
   		@RequestParam(value="partnerProjectInfo", required=false) String partnerProjectInfo,
    	Model model) {
    	
    	String error = "";
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();

		
    	Partner partner = partnersDao.findPartner(partnerId);
    	
    	Partner.ProjectInfo pi = new Partner.ProjectInfo();
		ProjectsMongoDAO projectDAO = new ProjectsMongoDAO();
		Project p = projectDAO.findProject(projectId);
		
		pi.setProject(p);
		pi.setInfo(partnerProjectInfo);
		pi.setStatus(partnerProjectStatus);
    	
		partner.getProjects().add(pi);
		partnersDao.savePartner(partner);
    	
    	model.addAttribute("partnerProjects", partner.getProjects());
    	model.addAttribute("error", error);
    	
        return "lists/partner_projects_list";
    }
    
    @RequestMapping(value="/editPartnersProject", method=RequestMethod.POST)
    String editPartnersProject(

    	@RequestParam(value="id", required=false) String id, 
   		@RequestParam(value="projectId", required=false) String projectId, 
   		@RequestParam(value="partnerProjectStatus", required=false) String partnerProjectStatus, 
   		@RequestParam(value="partnerProjectInfo", required=false) String partnerProjectInfo,
    	Model model) {
    	
    	String error = "";
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();

		
    	Partner partner = partnersDao.findPartner(id);
    	
    	Partner.ProjectInfo pi = new Partner.ProjectInfo();
		ProjectsMongoDAO projectDAO = new ProjectsMongoDAO();
		Project p = projectDAO.findProject(projectId);
		
		pi.setProject(p);
    	
		pi = partner.getProjects().get(partner.getProjects().indexOf(pi));
		
		pi.setInfo(partnerProjectInfo);
		pi.setStatus(partnerProjectStatus);
		
		partnersDao.savePartner(partner);
    	
    	model.addAttribute("partnerProjects", partner.getProjects());
    	model.addAttribute("error", error);
    	
        return "lists/partner_projects_list";
    }
    
}