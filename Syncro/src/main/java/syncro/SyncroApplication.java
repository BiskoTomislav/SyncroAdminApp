package syncro;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import syncro.dao.mongo.ContactsMongoDAO;
import syncro.dao.mongo.PartnersMongoDAO;
import syncro.dao.mongo.ProjectsMongoDAO;
import syncro.dao.mongo.UsersMongoDAO;
import syncro.entities.Contact;
import syncro.entities.Partner;
import syncro.entities.Project;
import syncro.entities.User;

@SpringBootApplication
public class SyncroApplication  implements CommandLineRunner {
	
    public static void main(String[] args) {
        SpringApplication.run(SyncroApplication.class, args);
    }
    
	@Override
	public void run(String... args) throws Exception {

//		BufferedInputStream bis = new BufferedInputStream(new FileInputStream("target/classes/static/images/noprofile.gif"));
//		
//		String basePath = new File("").getAbsolutePath();
//	    System.out.println(basePath);
//		
//		ContactsMongoDAO contacts = new ContactsMongoDAO();
//		ProjectsMongoDAO projects = new ProjectsMongoDAO();
//		PartnersMongoDAO partners = new PartnersMongoDAO();
//		UsersMongoDAO users = new UsersMongoDAO();
		
		//fillDB ();
		
//		for(Contact c: contacts.findAllContacts()) {
//			System.err.println(c.toString());
//		}
//		System.err.println();
//		Project projectToBeAddedToUser = new Project();
//		for(Project p: projects.findAllProjects()) {
//			System.err.println(p.toString());
//			projectToBeAddedToUser = p;
//		}
//		
//		User userToFind = users.findUser("55c1ed89cce6d27a744e6d51");
//
//		User.ProjectInfo pi = new User.ProjectInfo();
		
//		pi.setProject(projectToBeAddedToUser);
//		pi.setInfo("Napomena");
//		pi.setStatus("Test");
//		
//		userToFind.getProjects().add(pi);
//		
//		users.saveUser(userToFind);
		
//		List<User.ProjectInfo> list = userToFind.getProjects();
//		for (User.ProjectInfo projectInfo : list) {
//			System.err.println("Project Name: " + projectInfo.getProject().getData().getName());
//		}
		
		
//
//		System.err.println();
//		for(Partner p: partners.findAllPartners()) {
//			System.err.println(p.toString());
//		}
//		System.err.println("ADDING USER: ");
//		
//		User user = new User();
//		user.setUserId("0003");
//		user.getData().setName("Tomislav");
//		user.getData().setDob("6.12");
//		user.getData().setEmail("tomi@gmail.com");
//		user.getData().setMobile("098858585858");
//		user.getData().setOib("564654864564");
//		user.getData().setSex("M");
//		user.getData().setSurname("Bisko");
//		
//		//users.saveUser(user);
//		User userToDelete = new User();
//		User userToModify = new User();
//		User userToFind = new User();
//		userToModify.getData().setName("MODIRANI");
//		
//		
//		
//		User found = users.findUser(userToFind.get_id());
		//System.err.println(found.toString());
		
	}
	
	void fillDB () {
		fillUsers();
		fillContacts();
		fillPartners();
		fillProjects();
	}
	
	void fillUsers () {
		UsersMongoDAO usersDao = new UsersMongoDAO();
		
    	for (int i = 0; i < 15; i++) {
			User user = new User();
			user.setUserId("000" + i);
			user.getData().setName("Tomislav " + i);
			user.getData().setSurname("Bisko " + i);
			user.getData().setDob("12." + i);
			user.getData().setYob("200" + (i % 6 ));
			user.getData().setEmail("tomo@gmail.com" + i);
			user.getData().setMobile("+385989655176" + i);
			user.getData().setOib("00000555" + i);
			String sex = (i%2 == 0) ? "M" : "Ž";
			user.getData().setSex(sex);
			user.getData().setCitizenship("Cro");
			user.getData().setNationality("Cro");
			user.getData().setStatus("Zaposlen" + i);
			user.getHomeAddress().setAddress("Žajina");
			user.getHomeAddress().setCity("Zagreb");
			user.getHomeAddress().setPostNum("10 000");
			user.getLivingAddress().setAddress("Žajina");
			user.getLivingAddress().setCity("Zagreb");
			user.getLivingAddress().setPostNum("10 000");
			
			user.getEducation().setAdditionalInfo("Dodatni info " + i);
			user.getEducation().setProject("Syncro" + i);
			user.getEducation().setProjectActivityDuration("12 mj");
			user.getEducation().setReferentNumber("1254" + i);
			user.getEducation().setStatus("SSS" + i);
			user.getEducation().setSubject("Tomislav" + i);
			user.getEducation().setType("T" + i);
			user.getEducation().setStartDate("12.5");
			user.getEducation().setEndDate("12.7");
			user.getWorkInSyncro().setAdditionalInfo("Net" + i);
			user.getWorkInSyncro().setProject("Project " + i);
			user.getWorkInSyncro().setStatus("SES" + i);
			user.getWorkInSyncro().setStartDate("1.7");
			user.getWorkInSyncro().setEndDate("12.7");
			usersDao.saveUser(user);
		}
	}
	
	void fillProjects () {
    	ProjectsMongoDAO projectsDao = new ProjectsMongoDAO();
    	
    	for (int i = 0; i < 15; i++) {
			Project project = new Project();
			project.getData().setName("Space shuttle " + i);
			project.getData().setType("Space");
			project.getData().setSubject("Space");
			project.getData().setStatus("Space");
			project.getData().setDuration("Space");
			project.getData().setActivity("Space");
			project.getData().setPlace("Space");
			project.getData().setOrganizer("Space");
			project.getFinancingProgram().setProgramName("Space");
			project.getFinancingProgram().setSponsor("Space");
			project.getFinancingProgram().setRefId("Space");
			project.getFinancingProgram().setDue("Space");
			project.getFinancingProgram().getContactPerson().setPersonName("Marija");
			project.getFinancingProgram().getContactPerson().setPersonRole("Voditelj");
			project.getFinancingProgram().getContactPerson().setPersonEmail("marija@gmail.com");
			project.getFinancingProgram().getContactPerson().setPersonPhone("+385965441584");
			
			projectsDao.saveProject(project);
		}
	}
	
	void fillContacts () {
    	ContactsMongoDAO contactsDao = new ContactsMongoDAO();
    	
    	for (int i = 0; i < 15; i++) {
			Contact contact = new Contact();
			contact.getData().setCompanyName("Pliva d.o.o " + i);
			contact.getData().setCountry("Hrvatska");
			contact.getData().setAddress("Prva 19" + i);
			contact.getData().setEmail("pliva@gmail.com");
			contact.getData().setPhone("9564866455");
			contact.getData().setWebSite("www.pliva.hr");
			contact.getData().setFacebook("pliva");
			contact.getContactPerson().setPersonName("Marko");
			contact.getContactPerson().setPersonRole("Šef");
			contact.getContactPerson().setPersonEmail("marko@gmail.com");
			contact.getContactPerson().setPersonPhone("+3859654125");
			contactsDao.saveContact(contact);
		}
	}
	
	void fillPartners () {
    	PartnersMongoDAO partnersDao = new PartnersMongoDAO();
    	
    	for (int i = 0; i < 15; i++) {
			Partner partner = new Partner();
			partner.getData().setCompanyName("Ledo d.o.o.");
			partner.getData().setCountry("Hrvatska");
			partner.getData().setAddress("Prvih boraca 19");
			partner.getData().setEmail("ledo@gmail.com");
			partner.getData().setPhone("9564866455");
			partner.getData().setWebSite("www.ledo.hr");
			partner.getData().setFacebook("facebook/ledo");
			partner.getContactPerson().setPersonName("Medo Ledo");
			partner.getContactPerson().setPersonRole("Maskota");
			partner.getContactPerson().setPersonEmail("medo@gmail.com");
			partner.getContactPerson().setPersonPhone("2145");
			partnersDao.savePartner(partner);
		}
	}
}
