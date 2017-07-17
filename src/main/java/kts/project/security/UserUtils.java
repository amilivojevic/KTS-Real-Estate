package kts.project.security;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import kts.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;



@Component
public class UserUtils {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;


	public Object getLoggedUser(ServletRequest request) {
		System.out.println("pocetak funkcije");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		System.out.println("http req");
		String token = httpRequest.getHeader("X-Auth-Token");
		System.out.println("ucitan token");
		String un = tokenUtils.getUsernameFromToken(token);
		System.out.println("username = " + un);
		UserDetails details = userDetailsService.loadUserByUsername(un);
		if(details == null){
			System.out.println("Details = null");
		}
		else{
			System.out.println("detail nije null" + details.toString());
		}
		System.out.println("username : " + details.getUsername());
		return userService.findByUsername(details.getUsername());

		//NE ZNAM DAL TREBAJU OVE AUTHORITIES DA SE KORISTE?!?!?!?
		/*
		if (details.getAuthorities().contains(new SimpleGrantedAuthority(ADMIN))) {
			return adminService.findByUsername(details.getUsername());
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority(USER))) {
			return userService.findByUsername(details.getUsername());
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority(VERIFIER))) {
			return verifierService.findByUsername(details.getUsername());
		} else {
			return null;
		}*/
	}

	/*public LoggedUserDTO getLoggedUserData(ServletRequest request) {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("X-Auth-Token");

		UserDetails details = userDetailsService.loadUserByUsername(tokenUtils.getUsernameFromToken(token));
		if (details.getAuthorities().contains(new SimpleGrantedAuthority(ADMIN))) {
			Admin admin = adminService.findByUsername(details.getUsername());
			return new LoggedUserDTO(admin.getName(), admin.getSurname(), ADMIN);
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority(USER))) {
			User user = userService.findByUsername(details.getUsername());
			if (user.isClerk()) {
				return new LoggedUserDTO(user.getName(), user.getSurname(), CLERK);
			}
			return new LoggedUserDTO(user.getName(), user.getSurname(), USER);
		} else if (details.getAuthorities().contains(new SimpleGrantedAuthority(VERIFIER))) {
			Verifier verifier = verifierService.findByUsername(details.getUsername());
			return new LoggedUserDTO(verifier.getUsername(), "", VERIFIER);
		} else {
			return null;
		}
	}*/

/*	*//**
	 * This method checks if passed user name already exists
	 *
	 * @param username
	 * @return true or false
	 */
	public boolean checkUniqueUsername(String username) {

		if (userService.findByUsername(username) != null) {
			return false;
		}
		return true;
	}

}
