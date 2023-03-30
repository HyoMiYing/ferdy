package rokklancar.ferdydurkeaudiobookplayer.security;

import org.springframework.security.core.GrantedAuthority;
import rokklancar.ferdydurkeaudiobookplayer.persistence.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rokklancar.ferdydurkeaudiobookplayer.persistence.dao.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + email);
        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, getAuthorities());
    }

    private static List<GrantedAuthority> getAuthorities () {
        List<GrantedAuthority> authorities = new ArrayList<>();
        return authorities;
    }
}
