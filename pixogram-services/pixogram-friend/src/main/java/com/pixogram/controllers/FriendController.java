package com.pixogram.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pixogram.models.Friends;
import com.pixogram.repositories.FriendRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api")
public class FriendController {
	
	@Autowired
    FriendRepository friendRepository;
	
	/**
	 * Returns a list of friend by searching for a user from their userId
	 * @param userId
	 * @return
	 */
    @GetMapping("/friends/user/")
    public List<Friends> getFriendsByUserId(@RequestParam(value = "userid") Integer userid) 
    {
    	return friendRepository.findFriendsByUserId(userid);
    }
	
	/**
	 * Creates a new row in the Friends table
	 * @param imageMetadata
	 * @return
	 */
	@PostMapping("/friends")
    public Friends createFriend(@Valid @RequestBody Friends friends) 
	{	
        return friendRepository.save(friends);
    }
	
	/**
	 * Deletes a new row in the Friends table
	 * @param imageMetadata
	 * @return
	 */
	@PostMapping("/friends/delete/")
    public void deleteFriend(@Valid @RequestParam int userid, @RequestParam int friendid) 
	{	
        friendRepository.deleteFriendByUserIdFriendIf(userid, friendid);
    }
}