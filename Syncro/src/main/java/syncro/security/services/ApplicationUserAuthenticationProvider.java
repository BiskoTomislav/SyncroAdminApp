package syncro.security.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import syncro.dao.mongo.ApplicationUsersMongoDAO;
import syncro.entities.ApplicationUser;

@Component
public class ApplicationUserAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        if(name == null || password == null || "".equals(name) || "".equals(password) ) {
        	return null;
        }
        
        ApplicationUsersMongoDAO applicationUsersMongoDAO = new ApplicationUsersMongoDAO();
        
        List<ApplicationUser> usersInDB = null;
		try {
			usersInDB = applicationUsersMongoDAO.findApplicationUserByUsername(name);
		} catch (Exception e) {
			throw new AuthenticationException("Login error.") {
				private static final long serialVersionUID = 1262971110708560838L;
			};
		}
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        
        for (ApplicationUser applicationUser : usersInDB) {
			if( password.equals(applicationUser.getPassword() )) {
				// Passed
				grantedAuths.add(new SimpleGrantedAuthority("USER_ROLE"));
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name, password, grantedAuths);
				return token;
			}
		}
        
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}

}
