package model.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findAll() {
		// TODO Auto-generated method stub
		
		Statement st = null;
		ResultSet rs = null;
		
		List<Department> listDepartment = new ArrayList<Department>();
		
		String sql = "SELECT * FROM Department";
		
		try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);		
				
				while(rs.next())
				{
					listDepartment.add(instantiateDepartment(rs));
				}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
		return listDepartment;
	}
	
	
	private Department instantiateDepartment(ResultSet rs) throws SQLException
	{
	    Department dep = new Department();	
		dep.setId(rs.getInt("id"));
		dep.setName(rs.getString("name"));
		return dep;
	}

}
