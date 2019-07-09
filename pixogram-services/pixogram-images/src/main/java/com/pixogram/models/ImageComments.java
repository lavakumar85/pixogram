package com.pixogram.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "imagecomments")
public class ImageComments
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private String filename;
	
	@Column
	private String username;
	
	@Column
	private String comment;
	
	@Column
	private int userId;
	
	@Column(name = "createdAt")
    private Date createdAt;
	
	public void setFilename(String inputFilename)
	{
		this.filename = inputFilename;
	}
	
	public String getFilename()
	{
		return this.filename;
	}
	
	public void setUsername(String inputUsername)
	{
		this.username = inputUsername;
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public void setComment(String inputComment)
	{
		this.comment = inputComment;
	}
	
	public String getComment()
	{
		return this.comment;
	}
	
	public void setUserId(int inputUserId)
	{
		this.userId = inputUserId;
	}
	
	public int getUserId()
	{
		return this.userId;
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