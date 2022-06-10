package game.helper.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import game.helper.model.User;
import game.helper.model.dto.UserDTO;
import game.helper.repository.UserRepo;

@Service
public class UserService implements UserDetailsService {
	
	private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public UserService(UserRepo userRepository, PasswordEncoder passwordEncoder) {
    	this.userRepository = userRepository;
    	this.passwordEncoder = passwordEncoder;
    }
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }
        
        User user = optionalUser.get();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
        

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
	}

	public UserDTO create(UserDTO userDTO) {
		Optional<User> userOptionalUsername = userRepository.findByUsername(userDTO.getUsername());
        if (userOptionalUsername.isPresent()) {
            throw new IllegalArgumentException("A user with this username already exists!");
        }
        
        User user = new User(userDTO.getUsername(), passwordEncoder.encode(userDTO.getPassword()), userDTO.getName(), userDTO.getLastname());
        userRepository.save(user);
        return userDTO;
	}

}
