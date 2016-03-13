package syncro.controllers;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
 
@Controller
class AddEntityController {
 
    @RequestMapping("/addUser")
    String addUser(
    		@RequestParam("profile_img") MultipartFile profileImg,
    		User user,
    		Model model) {
    	
    	String error = "";

		byte[] imageBytes;
		try {
			if (profileImg.getBytes().length > 0) {
				imageBytes = profileImg.getBytes();
				final String encoded = Base64.getEncoder().encodeToString(
						imageBytes);
				user.setProfileImage("data:image/gif;base64," + encoded);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();

		try {
			usersDao.saveUser(user);
		} catch (Exception e) {
			error = e.getMessage();
		}

    	model.addAttribute("user", user);
    	model.addAttribute("projects", projectsDao.findAllProjects());
    	model.addAttribute("error", error);
    	
        return "edit_forms/edit_user_form";
    }
    
    @RequestMapping("/addApplicationUser")
    String addApplicationUser(
    		ApplicationUser applicationUser,
    		Model model) {
    	
    	String error = "";
    	
    	ApplicationUsersMongoDAO applicationUsersDao = new ApplicationUsersMongoDAO();

		try {
			applicationUsersDao.saveApplicationUser(applicationUser);
		} catch (Exception e) {
			error = e.getMessage();
		}

    	model.addAttribute("applicationUser", new ApplicationUser());
    	
    	model.addAttribute("applicationUsers", applicationUsersDao.findAllApplicationUsers());
    	
    	model.addAttribute("error", error);
    	
        return "administration";
    }
    
    @RequestMapping("/addProject")
    String addProject(
    		Project project,
    		@RequestParam(value="subtype", required=false) String subtype, 
    		Model model) {
    	
    	String error = "";
    	
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	
    	project.setSubtype(subtype);
    	
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
    	
        return "lists/" + subtype + "s_list";
    }
    
    @RequestMapping("/addContact")
    String addContact(
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

    @RequestMapping("/addPartner")
    String addPartner(
    		Partner partner,
    		Model model) {
    	
    	String error = "";
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();

		try {
			partnersDao.savePartner(partner);
		} catch (Exception e) {
			error = e.getMessage();
		}
		ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
		
		model.addAttribute("projects", projectsDao.findAllProjects());
    	model.addAttribute("partner", partner);
    	model.addAttribute("error", error);
    	
        return "edit_forms/edit_partner_form";
    }
}