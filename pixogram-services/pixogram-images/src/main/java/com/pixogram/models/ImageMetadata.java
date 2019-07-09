package com.pixogram.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * Table used to store the Users details once signup has been completed
 * @author Kiran
 *
 */
@Entity
@Table(name = "imagemetadata")
public class ImageMetadata
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    @Column(name = "userId")
    private Integer userId;
    
    @Column(name = "username")
    private String username;
 
    @Column(name = "filename")
    private String filename;
    
    @Column(name = "filetype")
    private String filetype;
 
    @Column(name = "size")
    private String size;
    
    @Column(name="caption")
    private String caption;
    
    @Column(name="likes")
    private Integer likes;
    
    @Column(name = "createdAt")
    private Date createdAt;
    
    public ImageMetadata() {}
 
    public ImageMetadata(Integer userId, String username, String filename, String filetype, String size, String caption) 
    {
        this.userId = userId;
        this.username= username;
        this.filename = filename;
        this.filetype = filetype;
        this.size = size;
        this.caption = caption;
    }
    
    public Integer getId()
    {
    	return this.id;
    }
    
    public void setUserId(Integer inputUserId)
    {
    	this.userId = inputUserId;
    }
    
    public Integer getUserId()
    {
    	return this.userId;
    }
    
    public void setUsername(String inputUsername)
    {
    	this.username = inputUsername;
    }
    
    public String getUsername()
    {
    	return this.username;
    }
    
    public void setFilename(String inputFilename)
    {
    	this.filename = inputFilename;
    }
    
    public String getFilename()
    {
    	return this.filename;
    }
    
    public void setFiletype(String inputFiletype)
    {
    	this.filetype = inputFiletype;
    }
    
    public String getFiletype()
    {
    	return this.filetype;
    }
    
    public void setSize(String inputSize)
    {
    	this.size = inputSize;
    }
    
    public String getSize()
    {
    	return this.size;
    }
    
    public void setCaption(String inputCaption)
    {
    	this.caption = inputCaption;
    }
    
    public String getCaption()
    {
    	return this.caption;
    }
    
    public void setLikes(Integer inputLikes)
    {
    	this.likes = inputLikes;
    }
    
    public Integer getLikes()
    {
    	return this.likes;
    }
    
    @PrePersist
    public void setCreatedAt() 
    {
      this.createdAt = new Date();
    } 
    
    public Date getCreatedAt()
    {
    	return this.createdAt;
    }
}