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

import com.pixogram.exceptions.ResourceNotFoundException;
import com.pixogram.models.ImageMetadata;
import com.pixogram.repositories.ImageMetadataRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ImageMetadataController
{
	@Autowired
    ImageMetadataRepository imageMetadataRepository;
	
	/**
	 * Returns all users
	 * @return
	 */
	@GetMapping("/imagemetadata/getAll")
    public List<ImageMetadata> getAllUsers() 
	{
        return imageMetadataRepository.findAll();
    }
	
	/**
	 * Returns a specific image by searching for their Id
	 * @param userId
	 * @return
	 */
    @GetMapping("/imagemetadata/{id}")
    public ImageMetadata getImageById(@RequestParam(value = "id") Integer userId) 
    {
        return imageMetadataRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("ImageMetadata", "id", userId));
    }
    
	/**
	 * Find all images relating to a user's id
	 * @param userId
	 * @return
	 */
	@GetMapping("/imagemetadata/{userId}")
	public List<ImageMetadata> getImageByUserId(@RequestParam(value = "userId") Integer userId)
	{
		return imageMetadataRepository.findByUserId(userId);
	}
    
	/**
	 * Creates a new row in the Imagemetadata table
	 * @param imageMetadata
	 * @return
	 */
	@PostMapping("/imagemetadata")
    public ImageMetadata createImageMetadata(@Valid @RequestBody ImageMetadata imageMetadata) 
	{	
        return imageMetadataRepository.save(imageMetadata);
    }
	
	/**
	 * Updates the image metadata's caption and description
	 * 
	 * @param id
	 * @param caption
	 * @param description
	 * @return
	 */
	@PostMapping("/imagemetadata/update")
    public ImageMetadata updateImageMetadata(@Valid @RequestParam(value = "filename") String filename, @RequestParam(value = "caption") String caption) 
	{	
		ImageMetadata imageMetadata = imageMetadataRepository.findByFilename(filename);
		imageMetadata.setCaption(caption);
        return imageMetadataRepository.save(imageMetadata);
    }
	
	/**
	 * Add like to picture
	 * @return
	 */
	@PostMapping("/imagemetadata/like")
    public ImageMetadata addLikeToImage(@Valid @RequestParam(value = "filename") String filename) 
	{	
		ImageMetadata imageMetadata = imageMetadataRepository.findByFilename(filename);
		imageMetadata.setLikes(imageMetadata.getLikes() + 1);
        return imageMetadataRepository.save(imageMetadata);
    }
	
	/**
	 * Get count of image uploads for news feed
	 * @param userId
	 * @return
	 */
	@GetMapping("/imagemetadata/recentuploadcount/")
	public List<ImageMetadata> getCountOfImageUploads(@RequestParam(value = "userid") int userid)
	{
		return imageMetadataRepository.getRecentImageUploads(userid);
	}
}