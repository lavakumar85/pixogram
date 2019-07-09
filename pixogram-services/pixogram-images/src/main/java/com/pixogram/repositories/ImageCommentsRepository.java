package com.pixogram.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pixogram.models.ImageComments;

/**
 * Persistence layer for ImageComment entity
 * Adds findByUsername method for use with authentication
 * @author Kiran
 *
 */
@Repository
public interface ImageCommentsRepository extends JpaRepository<ImageComments, Integer>
{
	public List<ImageComments> findByFilename(String filename);
	
	@Query(value = "SELECT * from imagecomments WHERE created_at > CURDATE()-2 AND user_id = :userid",
    		nativeQuery = true
    )
    public List<ImageComments> getRecentImageCommments(@Param("userid") int userid);
}
