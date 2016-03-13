package syncro.controllers;
 
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
 
@Controller
class ViewController {
 
    @RequestMapping("/login")
    String login(@RequestParam(value="view", required=false, defaultValue="login") String view, Model model) {

        return "login";
    }
    
    // Error page
    @RequestMapping("/error.html")
    public String error(HttpServletRequest request, Model model) {
      model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
      Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
      String errorMessage = null;
      if (throwable != null) {
        errorMessage = throwable.getMessage();
      }
      model.addAttribute("errorMessage", errorMessage);
      return "error.html";
    }
    
    @RequestMapping("/administration")
    String administration(@RequestParam(value="view", required=false, defaultValue="administration") String view, Model model) {
    	String error = "";
    	try {
			model.addAttribute("applicationUser", new ApplicationUser());
			
			ApplicationUsersMongoDAO applicationUsersDao = new ApplicationUsersMongoDAO();
			model.addAttribute("applicationUsers", applicationUsersDao.findAllApplicationUsers());
		} catch (Exception e) {
			error = e.getMessage();
			e.printStackTrace();
		}
    	
		model.addAttribute("error", error);
    	
        return "administration";
    }
	
    @RequestMapping("/users")
    String users(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
    	
    	model.addAttribute("name", view);
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();
    	
    	List<User> users = usersDao.findAllUsers();

    	model.addAttribute("users", users);
    	
        return "lists/users_list";
    }
    
    @RequestMapping("/getAdditionalUserInfo")
    String getAdditionalUserInfo(@RequestParam(value="unique_value", required=true) String uniqueValue, Model model) {
    	
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();
    	
    	User user = usersDao.findUser(uniqueValue);

    	Locale nationalityCountryName = new Locale("", user.getData().getNationality().toUpperCase());
    	Locale citizenshipCountryName = new Locale("", user.getData().getCitizenship().toUpperCase());
    	
    	model.addAttribute("user", user);
    	model.addAttribute("fullNameNationality", nationalityCountryName.getDisplayCountry());
    	model.addAttribute("fullNameCitizenship", citizenshipCountryName.getDisplayCountry());
    	model.addAttribute("userProjects", user.getProjects());
    	
        return "additional_info_rows/user_additional_info_row";
    }
    
    @RequestMapping("/showAddUserForm")
    String showAddUserForm(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {

    	User user = new User();
    	
    	model.addAttribute("user", user);
        return "add_forms/add_user_form";
    }
    
    @RequestMapping("/showEditUserForm")
    String showEditUserForm(
    		@RequestParam(value="userID", required=true) String userID, 
    		Model model) {
    	
    	User user = new User();
    	UsersMongoDAO users = new UsersMongoDAO();
    	
    	user = users.findUser(userID);
    	
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	List<Project> projects = projectsDao.findAllProjects();
    	
    	model.addAttribute("projects", projects);
    	
    	model.addAttribute("userProjects", user.getProjects());
    	
    	model.addAttribute("user", user);
    	
        return "edit_forms/edit_user_form";
    }
    
    @RequestMapping("/contacts")
    String contacts(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
    	
    	ContactsMongoDAO contactsDao = new ContactsMongoDAO();
    	
    	List<Contact> contacts = contactsDao.findAllContacts();
    	
    	model.addAttribute("contacts", contacts);
    	
        return "lists/contacts_list";
    }
    
    @RequestMapping("/getAdditionalContactInfo")
    String getAdditionalContactInfo(@RequestParam(value="unique_value", required=true) String uniqueValue, Model model) {
    	
    	
    	ContactsMongoDAO projectsDao = new ContactsMongoDAO();
    	
    	Contact contact = projectsDao.findContact(uniqueValue);
    	
    	model.addAttribute("contact", contact);
    	
        return "additional_info_rows/contact_additional_info_row";
    }
    
    @RequestMapping("/showAddContactForm")
    String showAddContactForm(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
        return "add_forms/add_contact_form";
    }
    
    @RequestMapping("/showEditContactForm")
    String showEditContactForm(
    		@RequestParam(value="contactID", required=true) String contactID, 
    		Model model) {
    	
    	Contact contact = new Contact();
    	ContactsMongoDAO contacts = new ContactsMongoDAO();
    	
    	contact = contacts.findContact(contactID);
    	
    	model.addAttribute("contact", contact);
    	
        return "edit_forms/edit_contact_form";
    }
    
    @RequestMapping("/projects")
    String projects(@RequestParam(value="view", required=false, defaultValue="projects") String view, Model model) {
    	
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	
    	//List<Project> projects = projectsDao.findAllProjects();
    	List<Project> projects = projectsDao.findAllWithTypeOfProject();
    	
    	model.addAttribute("projects", projects);
    	model.addAttribute("projectSubtype", Project.ProjectSubtypes.PROJECT);
    	
        return "lists/projects_list";
    }
    
    @RequestMapping("/partnerships")
    String partnerships(@RequestParam(value="view", required=false, defaultValue="partnerships") String view, Model model) {
    	
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	
    	List<Project> projects = projectsDao.findAllWithTypeOfPartnerships();
    	
    	model.addAttribute("projects", projects);
    	model.addAttribute("projectSubtype", Project.ProjectSubtypes.PARTNERSHIP.toString());
    	
        return "lists/partnerships_list";
    }
    
    @RequestMapping("/getAdditionalProjectInfo")
    String getAdditionalProjectInfo(@RequestParam(value="unique_value", required=true) String uniqueValue, Model model) {
    	
    	
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	
    	Project project = projectsDao.findProject(uniqueValue);
    	
    	model.addAttribute("project", project);
    	
        return "additional_info_rows/project_additional_info_row";
    }
    
    @RequestMapping("/showAddPartnershipForm")
    String showAddPartnershipForm(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
   
    	Project project = new Project();
    	
    	model.addAttribute("project", project);
        return "add_forms/add_partnership_form";
    }
    
    @RequestMapping("/showEditPartnershipForm")
    String showEditPartnershipForm(
    		@RequestParam(value="projectID", required=true) String projectID, 
    		Model model) {
    	
    	Project project = new Project();
    	ProjectsMongoDAO projects = new ProjectsMongoDAO();
    	
    	project = projects.findProject(projectID);
    	
    	model.addAttribute("project", project);
    	
        return "edit_forms/edit_partnership_form";
    }
    
    @RequestMapping("/showAddProjectForm")
    String showAddProjectForm(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
    	
    	
    	Project project = new Project();
    	
    	model.addAttribute("project", project);
        return "add_forms/add_project_form";
    }
    
    @RequestMapping("/showEditProjectForm")
    String showEditProjectForm(
    		@RequestParam(value="projectID", required=true) String projectID, 
    		Model model) {
    	
    	Project project = new Project();
    	ProjectsMongoDAO projects = new ProjectsMongoDAO();
    	
    	project = projects.findProject(projectID);
    	
    	model.addAttribute("project", project);
    	
        return "edit_forms/edit_project_form";
    }
    
    @RequestMapping("/partners")
    String partners(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();
    	
    	List<Partner> partners = partnersDao.findAllPartners();
    	
    	model.addAttribute("partners", partners);
    	
        return "lists/partners_list";
    }

    @RequestMapping("/getAdditionalPartnerInfo")
    String getAdditionalPartnerInfo(@RequestParam(value="unique_value", required=true) String uniqueValue, Model model) {
    	
    	
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();
    	
    	Partner partner = partnersDao.findPartner(uniqueValue);

    	Locale countryName = new Locale("", partner.getData().getCountry().toUpperCase());
    	
    	model.addAttribute("partner", partner);
    	model.addAttribute("partnerProjects", partner.getProjects());
    	

    	model.addAttribute("countryName", countryName.getDisplayCountry());
    	
        return "additional_info_rows/partner_additional_info_row";
    }
    
    @RequestMapping("/showAddPartnerForm")
    String showAddPartnerForm(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
    	
    	Partner partner = new Partner();
    	
    	model.addAttribute("partner", partner);
    	
        return "add_forms/add_partner_form";
    }
    
    @RequestMapping("/showEditPartnerForm")
    String showEditPartnerForm(
    		@RequestParam(value="partnerID", required=true) String partnerID, 
    		Model model) {
    	
    	Partner partner = new Partner();
    	PartnersMongoDAO partners = new PartnersMongoDAO();
    	
    	partner = partners.findPartner(partnerID);
    	
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	List<Project> projects = projectsDao.findAllProjects();
    	
    	model.addAttribute("projects", projects);
    	
    	model.addAttribute("partnerProjects", partner.getProjects());
    	
    	model.addAttribute("partner", partner);
    	
        return "edit_forms/edit_partner_form";
    }

}