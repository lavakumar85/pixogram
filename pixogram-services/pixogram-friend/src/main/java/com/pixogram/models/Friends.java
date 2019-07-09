package com.pixogram.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "friends")
public class Friends
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private int userId;
	
	@Column
	private int friendId;
	
	public int getUserId() 
	{
		return this.userId;
	}
	
	public void setUserId(int inputUserId) 
	{
		this.userId = inputUserId;
	}
	
	public int getFriendId() 
	{
		return this.friendId;
	}
	
	public void setFriendId(int inputFriendId)
	{
		this.friendId = inputFriendId;
	}
	
}
