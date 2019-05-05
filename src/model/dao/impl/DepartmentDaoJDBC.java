package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		PreparedStatement st = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Department (Name) VALUES (?)";
		
		try {
			st = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0)
			{
				rs = st.getGeneratedKeys();
				if(rs.next())
				{
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else
			{
				throw new  DbException("Unexpected error! No rows affected!");
			}
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}		
		
	}

	@Override
	public void update(Department obj) {
		
		PreparedStatement st = null;
		String sql = "UPDATE Department SET Name=? WHERE Id=?";
		try {
			st = conn.prepareStatement(sql);
			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());
		
			st.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		finally
		{
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteById(Integer id) {
		
		PreparedStatement st = null;
		String sql = "DELETE from Department WHERE Id=?";
		
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			
			throw new DbException(e.getMessage());			
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Department findById(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		String sql = "SELECT Name FROM Department WHERE id=?";
		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, id);
			st.executeQuery();
			rs = st.executeQuery();
				
			return instantiateDepartment(rs);
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
			
		}
		finally {
			DB.closeStatement(st);
		}
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
