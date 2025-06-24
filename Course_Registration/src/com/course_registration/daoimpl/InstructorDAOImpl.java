package com.course_registration.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course_registration.dao.InstructorDAO;
import com.course_registration.model.Instructor;
import com.course_registration.util.DBConnection;

public class InstructorDAOImpl implements InstructorDAO {

    private final String INSERT_INSTRUCTOR = "INSERT INTO `instructors` (`instructorName`, `courseId`) VALUES (?, ?)";
    private final String UPDATE_INSTRUCTOR = "UPDATE `instructors` SET `instructorName` = ?, `courseId` = ? WHERE `instructorId` = ?";
    private final String SELECT_INSTRUCTOR = "SELECT * FROM `instructors` WHERE `instructorId` = ?";
    private final String DELETE_INSTRUCTOR = "DELETE FROM `instructors` WHERE `instructorId` = ?";
    private final String SELECTALL_INSTRUCTORS = "SELECT * FROM `instructors`";

    @Override
    public List<Instructor> getAllInstructors() {
    	
        List<Instructor> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTALL_INSTRUCTORS)) {

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                int instructorId = res.getInt("instructorId");
                String instructorName = res.getString("instructorName");
                int courseId = res.getInt("courseId");

                Instructor instructor = new Instructor(instructorId, instructorName, courseId);
                list.add(instructor);
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Instructor getInstructorById(int instructorId) {
    	
        Instructor instructor = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_INSTRUCTOR)) {

            preparedStatement.setInt(1, instructorId);
            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                String instructorName = res.getString("instructorName");
                int courseId = res.getInt("courseId");

                instructor = new Instructor(instructorId, instructorName, courseId);
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return instructor;
    }

    @Override
    public void addInstructor(Instructor instructor) {
        
    	try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INSTRUCTOR)) {

            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setInt(2, instructor.getCourseId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Inserted rows: " + res);
        } 
    	catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateInstructor(Instructor instructor) {
        
    	try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_INSTRUCTOR)) {

            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setInt(2, instructor.getCourseId());
            preparedStatement.setInt(3, instructor.getInstructorId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Updated rows: " + res);
        } 
    	catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInstructor(int instructorId) {
        
    	try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_INSTRUCTOR)) {

            preparedStatement.setInt(1, instructorId);

            int res = preparedStatement.executeUpdate();
            System.out.println("Deleted rows: " + res);
        }
    	catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
