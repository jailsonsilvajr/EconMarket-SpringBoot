package br.com.springboot.econmarket.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.econmarket.model.User;
import br.com.springboot.econmarket.repository.UserRepository;

@RestController
@RequestMapping("user")
public class UserEndpoint {

	private final UserRepository userRepository;
	@Autowired
	public UserEndpoint(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
		
		Optional<User> user = this.userRepository.findByEmail(email);
		if(user.isPresent() && user.get().getPassword().equalsIgnoreCase(password))
			return new ResponseEntity<>(user.get(), HttpStatus.ACCEPTED);
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/getUser")
	public ResponseEntity<?> getUser(@RequestParam String email){
		
		User user = verifyIfUserExists(email);
		if(user != null) {
			
			//user.setPassword(null);
			return new ResponseEntity<>(user, HttpStatus.FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User newUser) {
		
		if(verifyIfUserExists(newUser.getEmail()) != null) return new ResponseEntity<>(HttpStatus.CONFLICT);
		newUser = this.userRepository.save(newUser);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User updatedUser){
		
		if(verifyIfUserExists(updatedUser.getId()) != null) {
			
			this.userRepository.save(updatedUser);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam Long id){
		
		User user = verifyIfUserExists(id);
		if(user != null) {
			
			this.userRepository.delete(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	private User verifyIfUserExists(String email) {
		
		Optional<User> optUser = this.userRepository.findByEmail(email);
		if(optUser.isPresent()) return optUser.get();
		return null;
	}
	
	private User verifyIfUserExists(Long id) {
		
		Optional<User> optUser = this.userRepository.findById(id);
		if(optUser.isPresent()) return optUser.get();
		return null;
	}
}
