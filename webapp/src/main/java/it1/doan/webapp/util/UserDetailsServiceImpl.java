package it1.doan.webapp.util;

import it1.doan.webapp.admin.service.impl.UserService;
import it1.doan.webapp.model.NguoiDung;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserService userService;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        NguoiDung user =this.userService.getUserByEmail(s);

        if (user == null) {
            System.out.println("User not found! " + user);
            throw new UsernameNotFoundException("User " + user + " was not found in the database");
        }


        List<String> roleNames= new ArrayList<>();
        roleNames.add(user.getQuyen());

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getMk(), grantList);

        return userDetails;
    }
}
