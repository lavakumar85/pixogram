package com.pixogram.message.response;

public class UploadFileResponse 
{
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size) 
    {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

	public void setFileName(String inputFileName)
	{
		this.fileName = inputFileName;
	}
	
	public String getFileName()
	{
		return this.fileName;
	}
	
	public void setFileDownloadUri(String inputFileDownloadUri)
	{
		this.fileDownloadUri = inputFileDownloadUri;
	}
	
	public String getFileDownloadUri()
	{
		return this.fileDownloadUri;
	}
	
	public void setFileType(String inputFileType)
	{
		this.fileType = inputFileType;
	}
	
	public String getFileType()
	{
		return this.fileType;
	}
	
	public void setSize(long inputSize)
	{
		this.size = inputSize;
	}
	
	public long getSize()
	{
		return this.size;
	}
}