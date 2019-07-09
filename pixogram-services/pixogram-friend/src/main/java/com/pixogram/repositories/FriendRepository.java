package com.pixogram.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pixogram.models.Friends;

/**
 * Persistence layer for Friend entity
 * @author Kiran
 *
 */
@Repository
public interface FriendRepository extends JpaRepository<Friends, Integer>
{
	@Query(value = "SELECT * from friends WHERE user_id = :userid",
    		nativeQuery = true
    )
	public List<Friends> findFriendsByUserId(@Param("userid") int userid);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM friends WHERE user_id = :userid AND friend_id = :friendid",
			nativeQuery = true)
	public void deleteFriendByUserIdFriendIf(@Param("userid") int userid, @Param("friendid") int friendid);
}