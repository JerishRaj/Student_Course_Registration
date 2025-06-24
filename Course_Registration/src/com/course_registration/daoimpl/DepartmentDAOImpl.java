package com.course_registration.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course_registration.model.Department;
import com.course_registration.dao.DepartmentDAO;
import com.course_registration.util.DBConnection;

public class DepartmentDAOImpl implements DepartmentDAO{
	
    private final String INSERT_DEPARTMENT = "INSERT INTO `departments` (`departmentName`) VALUES (?)";
    private final String UPDATE_DEPARTMENT = "UPDATE `departments` SET `departmentName` = ? WHERE `departmentId` = ?";
    private final String SELECT_DEPARTMENT = "SELECT * FROM `departments` WHERE `departmentId` = ?";
    private final String DELETE_DEPARTMENT = "DELETE FROM `departments` WHERE `departmentId` = ?";
    private final String SELECTALL_DEPARTMENT = "SELECT * FROM `departments`";

	@Override
	public List<Department> getAllDepartments() {
		
		List<Department> list = new ArrayList<>();
		
		try(Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECTALL_DEPARTMENT)){
			
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next()) {
				int departmentId = res.getInt("departmentId");
				String departmentName = res.getString("departmentName");
				
				Department department = new Department(departmentId, departmentName);
				list.add(department);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Department getDepartmentById(int departmentId) {
		
		Department department = null;
		
		try(Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DEPARTMENT)){
			
			preparedStatement.setInt(1, departmentId);
			
			ResultSet res = preparedStatement.executeQuery();
			
			while(res.next()) {
				int departmentId1 = res.getInt("departmentId");
				String departmentName = res.getString("departmentName");
				
				department = new Department(departmentId1, departmentName);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return department;
	}

	@Override
	public void addDepartment(Department department) {
		
		try(Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT)){
			
			preparedStatement.setString(1, department.getDepartmentName());
			
			int res = preparedStatement.executeUpdate();
			System.out.println("Inserted rows: " + res);
		}
		catch (SQLException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void updateDepartment(Department department) {
		
		try(Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DEPARTMENT)){
			
			preparedStatement.setString(1, department.getDepartmentName());
			preparedStatement.setInt(2, department.getDepartmentId());
			
			int res = preparedStatement.executeUpdate();
			System.out.println("Updated rows: " + res);
		}
		catch (SQLException e) {
           e.printStackTrace();
       }
		
	}

	@Override
	public void deleteDepartment(int departmentId) {
		
		try (Connection connection = DBConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DEPARTMENT)) {

	            preparedStatement.setInt(1, departmentId);

	            int res = preparedStatement.executeUpdate();
	            System.out.println("Deleted rows: " + res);

	        } 
	    	catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}

}
