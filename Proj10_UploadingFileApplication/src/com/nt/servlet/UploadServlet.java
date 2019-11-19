package com.nt.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javazoom.upload.MultipartFormDataRequest;
import javazoom.upload.UploadBean;
import javazoom.upload.UploadException;
import javazoom.upload.UploadFile;

@WebServlet("/uplurl")
public class UploadServlet extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletOutputStream sos=null;
        MultipartFormDataRequest nreq=null;
		UploadBean bean=null;
		Hashtable<String, UploadFile> map=null;
		Enumeration<UploadFile> e=null;
		//general Setting
		sos=response.getOutputStream();
		response.setContentType("text/html");
		//prepare Special Object Request
		try {
			nreq=new MultipartFormDataRequest(request);
			//specify the file Uploading system
			bean=new UploadBean();
			bean.setFolderstore("D:/store");
			bean.setOverwrite(false);
			//complete file uploading
			//bean.setFilesizelimit(2*1024);
			bean.store(nreq);
			
			//Display the names of Uploading File
			sos.println("<h1 style='color:blue'> The Upload Files Are:</h1>");
			map=nreq.getFiles();
			e=map.elements();
			while (e.hasMoreElements()) {
				UploadFile file = (UploadFile) e.nextElement();
				sos.println("<br>"+file.getFileName()+" "+file.getFileSize()+" "+file.getContentType());
			}//while
		} //try
		catch (UploadException e1) {
			e1.printStackTrace();
			sos.println("<h1 style='color:red'>Size limit is Maximum</h1>");
		}
		 sos.println("<a href='Upload.html'><img src='home.png'</a>");
	}
    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
