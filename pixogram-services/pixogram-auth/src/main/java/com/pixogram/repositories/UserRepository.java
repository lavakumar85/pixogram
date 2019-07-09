package com.pixogram.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pixogram.models.User;

/**
 * Persistence layer for User entity
 * Adds findByUsername method for use with authentication
 * @author Kiran
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    
    @Query(value = "SELECT * from users WHERE user_blocked = 'N'",
    		nativeQuery = true
    )
    public List<User> getUnblockedUsers();
    
    @Query(value = "SELECT * from users WHERE user_blocked = 'Y'",
    		nativeQuery = true
    )
    public List<User> getBlockedUsers();
    
    @Query(value = "SELECT * from users WHERE name LIKE CONCAT('%',:name,'%')",
    		nativeQuery = true)
    public List<User> getUsersByName(@Param("name") String name);
    
    @Query(value = "UPDATE users SET profile_pic_uri = :profilePicUri WHERE id = :id",
	nativeQuery = true)
	public List<User> updateProfilePic(@Param("profilePicUri") String profilePicUri, @Param("id") String id);
}