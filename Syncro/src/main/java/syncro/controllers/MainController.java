package syncro.controllers;
 
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import syncro.dao.mongo.UsersMongoDAO;
import syncro.entities.User;
 
@Controller
class MainController {
 
    @RequestMapping("/")
    String addUSer(@RequestParam(value="view", required=false, defaultValue="users") String view, Model model) {
    	
    	model.addAttribute("name", view);
    	
    	UsersMongoDAO usersDao = new UsersMongoDAO();
    	
    	List<User> users = usersDao.findAllUsers();
    	
    	model.addAttribute("users", users);
    	
        return "main";
    }
    
    


}