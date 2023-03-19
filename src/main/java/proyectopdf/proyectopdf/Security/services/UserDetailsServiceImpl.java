package proyectopdf.proyectopdf.Security.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import proyectopdf.proyectopdf.Model.Role;
import proyectopdf.proyectopdf.Model.User;
import proyectopdf.proyectopdf.Service.User.IUser;

@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IUser iUsuario;

    @Override
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException {

        Optional<User> user = iUsuario.findByEmail(userName);

        return new org.springframework.security.core.userdetails.User(
                user.get().getEmail(), user.get().getPassword(), user.get().getEstado(), true, true,
                true, getAuthorities(user.get().getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
        List<String> role = new ArrayList<>();
        Iterator<Role> i = roles.iterator();
        while (i.hasNext()) {
            role.add(i.next().getRol().toString());  
        }
        return getGrantedAuthorities(role);
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
