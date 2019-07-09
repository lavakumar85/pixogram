package com.pixogram.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pixogram.models.ImageMetadata;

/**
 * Persistence layer for User entity
 * Adds findByUsername method for use with authentication
 * @author Kiran
 *
 */
@Repository
public interface ImageMetadataRepository extends JpaRepository<ImageMetadata, Integer>
{	
	public List<ImageMetadata> findByUserId(Integer userId);
	public ImageMetadata findByFilename(String filename);
	
	@Query(value = "SELECT * from imagemetadata WHERE created_at > CURDATE()-2 AND user_id = :userid",
    		nativeQuery = true
    )
    public List<ImageMetadata> getRecentImageUploads(@Param("userid") int userid);
}