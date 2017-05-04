package com.xa.action;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;

public class UserAction extends ActionSupport{

	private String username;
	private String password;
	
	public File mPhoto;
	public String mPhotoFileName;
	
	public String login() throws IOException {
		//userService.login(username,password)；
		
		System.out.println(username+","+password);
		
		HttpServletResponse response=ServletActionContext.getResponse();
		PrintWriter writer=response.getWriter();
		writer.write("login success !");
		writer.flush();
		return null;	
		
	}
	
	 public String uploadInfo() throws IOException {
		 
		 System.out.println(username+","+password);
		if (mPhoto==null) {
			System.out.println(mPhotoFileName+"is null.");
		} 
		
		String dir=ServletActionContext.getServletContext().getRealPath("files");
		File file=new File(dir,mPhotoFileName);
		
		FileUtils.copyFile(mPhoto, file);
		
		return null;
		
	}
	
public String postFile() throws IOException {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		System.out.println("sessionID:"+request.getSession().getId());
		
		ServletInputStream is=request.getInputStream();
		
		String dir=ServletActionContext.getServletContext().getRealPath("files");
		File file=new File(dir,"picture.jpg");
		FileOutputStream fos=new FileOutputStream(file);
		int len=0;
		byte[] buf=new byte[1024];
		
		while ((len=is.read(buf))!=-1) {
			
			fos.write(buf,0,len);
		}
		fos.flush();
		fos.close();
		System.out.println("sss");
		return null;
		
	}
	
	public String postString() throws IOException {
		
		
		HttpServletRequest request=ServletActionContext.getRequest();
		
		System.out.println("sessionID:"+request.getSession().getId());
		
		ServletInputStream is=request.getInputStream();
		StringBuffer sb=new StringBuffer();
		int len=0;
		byte[] buf=new byte[1024];
		
		while ((len=is.read(buf))!=-1) {
			sb.append(new String (buf,0,len));
			
		}
		System.out.println(sb.toString());
		
		return null;
		
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String usernaame) {
		this.username = usernaame;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
