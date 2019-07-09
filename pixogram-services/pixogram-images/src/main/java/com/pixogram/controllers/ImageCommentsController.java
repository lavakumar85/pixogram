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

import com.pixogram.models.ImageComments;
import com.pixogram.repositories.ImageCommentsRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ImageCommentsController
{
	@Autowired
	ImageCommentsRepository imageCommentsRepository;
	
	/**
	 * Returns all comments
	 * @return
	 */
	@GetMapping("/imagecomments/getAll")
    public List<ImageComments> getAllComments() 
	{
        return imageCommentsRepository.findAll();
    }
	
	/**
	 * Find all comments relating to a filename
	 * @param userId
	 * @return
	 */
	@GetMapping("/imagecomments/getComments/")
	public List<ImageComments> getCommentByFilename(@RequestParam(value = "filename") String filename)
	{
		return imageCommentsRepository.findByFilename(filename);
	}
	
	/**
	 * Creates a new row in the ImageComment table
	 * @param imageMetadata
	 * @return
	 */
	@PostMapping("/imagecomments")
    public ImageComments createImageComments(@Valid @RequestBody ImageComments imageComments) 
	{	
        return imageCommentsRepository.save(imageComments);
    }
	
	/**
	 * Get count of comments for news feed
	 * @param userId
	 * @return
	 */
	@GetMapping("/imagecomments/recentcommentcount/")
	public List<ImageComments> getCountOfComment(@RequestParam(value = "userid") int userid)
	{
		return imageCommentsRepository.getRecentImageCommments(userid);
	}
}