package com.pixogram.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.exceptions.ResourceNotFoundException;
import com.pixogram.models.User;
import com.pixogram.repositories.UserRepository;

/**
 * Controller to register new users on the system
 * The password is encrypted and saves it into the database
 * 
 * @author Kiran
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController
{
	@Autowired
    UserRepository userRepository;
	
	/**
	 * Returns all users
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/user")
    public List<User> getAllUsers() 
	{
        return userRepository.findAll();
    }
	
	/**
	 * Returns a specific user by searching for their Id
	 * @param userId
	 * @return
	 */
    @GetMapping("/user/")
    public User getUserById(@RequestParam(value = "id") Integer userId) 
    {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
    }
	
	/**
	 * Blocks a user or mentor
	 * @param userId
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/user/block/")
	public User blockUser(@RequestParam(value = "id") int userId)
	{
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setUserBlocked("Y");
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	/**
	 * Blocks a user or mentor
	 * @param userId
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/user/unblock/")
	public User unblockUser(@RequestParam(value = "id") int userId)
	{
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setUserBlocked("N");
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	
	/**
	 * Returns a specific user by searching for their Id
	 * @param userId
	 * @return
	 */
    @GetMapping("/user/role/{username}")
    public User getUserRole(@PathVariable(value = "username") String username) 
    {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }
    
    /**
	 * Returns all blocked users
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/user/blocked")
    public List<User> getBlockedUsers() 
	{
        return userRepository.getBlockedUsers();
    }
	
	/**
	 * Returns all unblocked users
	 * @return
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/user/unblocked")
    public List<User> getUnblockedUsers() 
	{
        return userRepository.getUnblockedUsers();
    }
	
	/**
	 * Returns a boolean based on whether a username exists or not
	 * True = exists, False = doesn't exist
	 * 
	 * @param username
	 * @return
	 */
	@GetMapping("/user/checkusername/")
	public boolean checkUserNameInUse(@RequestParam(value = "username") String username)
	{
		return userRepository.existsByUsername(username);
	}
	
	@GetMapping("/user/namesearch/")
	public List<User> getUsernameFromName(@RequestParam(value = "name") String name)
	{
		return userRepository.getUsersByName(name);
	}
	
	/**
	 * Blocks a user or mentor
	 * @param userId
	 */
	@PutMapping("/user/updateprofilepic/")
	public User updateProfilePic(@RequestParam(value = "profilePicUri") String profilePicUri, @RequestParam(value = "id") int id)
	{
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		user.setProfilePicUri(profilePicUri);
		
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
}