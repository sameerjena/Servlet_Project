package com.nt.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nt.dto.BankCustomerDTO;
import com.nt.service.BankCustomerService;
import com.nt.service.BankCustomerServiceImpl;
import com.nt.vo.BankCustomerVO;
@WebServlet("/MainControllerServlet")
public class MainControllerServlet extends HttpServlet {
	private BankCustomerService service;

	public void init() {
		service=new BankCustomerServiceImpl();	
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String holdername=null,address=null,mobileno=null,initialamount=null,accounttype=null;
		BankCustomerVO vo=null;
		BankCustomerDTO dto=null;
		ServletOutputStream sos=null;
		String resultMsg=null;
		//get outputStream
		sos=res.getOutputStream();
		res.setContentType("text/html");
		//read from data
		holdername=req.getParameter("holdername");
		address=req.getParameter("address");
		mobileno=req.getParameter("mobileno");
		initialamount=req.getParameter("initialamount");
		accounttype=req.getParameter("accounttype");
		//store into data into vo class object
		vo=new BankCustomerVO();
		vo.setAccHolder(holdername);
		vo.setAdd(address);
		vo.setMobNo(mobileno);
		vo.setInitAmt(initialamount);
		vo.setAccType(accounttype);
		//convert VO class object to DTO class object
		dto= new BankCustomerDTO();
		dto.setAccHolder(vo.getAccHolder());
		dto.setAdd(vo.getAdd());
		dto.setMobNo(Long.parseLong(vo.getMobNo()));
		dto.setInitAmt(Float.parseFloat(vo.getInitAmt()));
		dto.setAccType(vo.getAccType());
		//use service class
		try {
			resultMsg=service.register(dto);
			sos.println("<h1 style='color:green;text-align:center'>"+resultMsg+"</h1>");
		}
		  catch(Exception e) {
	        e.printStackTrace();
	        sos.println("<h1 style='color:red;text-align:center'>Internal Problem--Try Again</h1>"); 	
	        }
		    sos.println("<br> <a href='employee_register.html'>home</a>");
	        //close stream
	        sos.close();
		
		
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public void destroy() {
		service=null;
	}

	
}
