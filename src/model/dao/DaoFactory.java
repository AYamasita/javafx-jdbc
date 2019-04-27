package model.dao;

import db.DB;
import model.dao.impl.DepartmentDaoJDBC;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	public static DepartmentDao createDepartmenttDao()
	{
		return new DepartmentDaoJDBC(DB.getConnection());
	}
	
	public static SellerDao createSellerDao()
	{
		return  new SellerDaoJDBC(DB.getConnection());
	}

}
