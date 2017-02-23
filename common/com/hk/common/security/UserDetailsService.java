package com.hk.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.base.dao.UserDAO;
import com.hk.base.entity.User;

/**
 * A custom {@link UserDetailsService} where user information is retrieved from
 * a JPA repository
 */
@Service
@Transactional(readOnly = true)
public class UserDetailsService implements 
		org.springframework.security.core.userdetails.UserDetailsService {
	
	@Autowired
	private UserDAO userDao;

	/**
	 * Returns a populated {@link UserDetails} object. The username is first
	 * retrieved from the database and then mapped to a {@link UserDetails}
	 * object.
	 */
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User userFromDB = null;
		try {
			userFromDB = (User) userDao.execUnique("from User u where u.id = ? and u.isActive=true ", new Object[]{username});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userFromDB;
	}

	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical
	 * role
	 * 
	 * @param role
	 *            the numerical role
	 * @return a collection of {@link GrantedAuthority

	 */
	/*public Collection<? extends GrantedAuthority> getAuthorities(String role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}*/

	/**
	 * Converts a numerical role to an equivalent list of roles
	 * 
	 * @param role
	 *            the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	/*public List<String> getRoles(String role) {
		List<String> roles = new ArrayList<String>();
		//System.out.println("role nya "+role);
		if (role.equalsIgnoreCase("1")) {
			//System.out.println("masuk");
			roles.add("ROLE_ADMIN");

		} else if (role.equalsIgnoreCase("2")) {
			roles.add("ROLE_USER");
		}

		return roles;
	}*/

	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * 
	 * @param roles
	 *            {@link String} of roles
	 * @return list of granted authorities
	 */
	/*public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			////System.out.println("asupp " +  roles);
			authorities.add(new GrantedAuthorityImpl(role));
		}
		return authorities;
	}*/
}