package com.nt.service;

import com.nt.bo.BankCustomerBO;
import com.nt.dao.BankCustomerDAO;
import com.nt.dao.BankCustomerDAOImpl;
import com.nt.dto.BankCustomerDTO;

public class BankCustomerServiceImpl implements BankCustomerService {
	private BankCustomerDAO dao;
	
	public BankCustomerServiceImpl() {
		dao=new BankCustomerDAOImpl();
	}
	
	@Override
	public String register(BankCustomerDTO dto) throws Exception {
		int count=0;
		float initAmt=0.0f,finalAmt=0.0f;
		float cuponAmt=0.0f;
		BankCustomerBO bo=null;
		//calculate finalAmount with cuponAmount
		initAmt=dto.getInitAmt();
		if (initAmt<=100000) {
			cuponAmt=1000;
			finalAmt=initAmt+cuponAmt;
		   System.out.println("customer get cashback prices "+cuponAmt+ "finalAmount price will be"+finalAmt);
		}
		else if (initAmt>=100000) {
			cuponAmt=2000;
			finalAmt=initAmt+cuponAmt;
			System.out.println("customer get cashback prices "+cuponAmt+ "finalAmount price will be"+finalAmt);	
		} else {
			return "no cashBack will Generated";
		}  
		//prepare BO class object having persistable data
		bo=new BankCustomerBO();
		bo.setAccHolder(dto.getAccHolder());
		bo.setAdd(dto.getAdd());
		bo.setMobNo(dto.getMobNo());
		bo.setInitAmt(dto.getInitAmt());
		bo.setAccType(dto.getAccType());
		bo.setFinalAmt(finalAmt);
		
		count=dao.insert(bo);
		//process the result
		if(count==0)
			return "BankCustomer Registration failed";
		else
		    return "BankCustomer Registration sucessfull";
	}

}
