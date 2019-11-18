package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.nt.bo.BankCustomerBO;

public class BankCustomerDAOImpl implements BankCustomerDAO {
	private  static final String INSERT_CUSTOMER="INSERT INTO BANK_CUST_REGISTRATION VALUES(ACCNO_SEQ.NEXTVAL,?,?,?,?,?,?)";
    
	private Connection getpoolConnection()throws Exception{
		  InitialContext ic=null;
		  DataSource ds=null;
		  Connection con=null;
		  //create Initial Context obj
		  ic=new InitialContext();
		  //get datasource object throws lookup operation
		  ds=(DataSource) ic.lookup("java:/comp/env/DsJndi");
		  //get pooled jdbc con object
		  con=ds.getConnection();
		  return con;	
	}
	
	@Override
	public int insert(BankCustomerBO bo) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		//get pooled jdbc Connection Object
		con=getpoolConnection();
		ps=con.prepareStatement(INSERT_CUSTOMER);
		ps.setString(1, bo.getAccHolder());
		ps.setString(2, bo.getAddr());
		ps.setLong(3, bo.getMobNo());
		ps.setFloat(4, bo.getInitAmt());
		ps.setString(5, bo.getAccType());
		ps.setFloat(6, bo.getFinalAmt());
		//execute the query
		count=ps.executeUpdate();
		//close the jdbc object
		ps.close();
		con.close();
		return count;
	}

}
