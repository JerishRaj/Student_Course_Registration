package com.course_registration.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course_registration.dao.StudentDAO;
import com.course_registration.model.Student;
import com.course_registration.util.DBConnection;

public class StudentDAOImpl implements StudentDAO {

    private final String INSERT_STUDENT = "INSERT INTO `students` (`studentName`, `studentEmail`, `departmentId`) VALUES (?, ?, ?)";
    private final String UPDATE_STUDENT = "UPDATE `students` SET `studentName` = ?, `studentEmail` = ?, `departmentId` = ? WHERE `studentId` = ?";
    private final String SELECT_STUDENT = "SELECT * FROM `students` WHERE `studentId` = ?";
    private final String DELETE_STUDENT = "DELETE FROM `students` WHERE `studentId` = ?";
    private final String SELECTALL_STUDENT = "SELECT * FROM `students`";

    @Override
    public List<Student> getAllStudents() {
    	
        List<Student> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTALL_STUDENT)) {

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                int studentId = res.getInt("studentId");
                String studentName = res.getString("studentName");
                String studentEmail = res.getString("studentEmail");
                int departmentId = res.getInt("departmentId");

                Student student = new Student(studentId, studentName, studentEmail, departmentId);
                list.add(student);
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Student getStudentById(int studentId) {
    	
        Student student = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT)) {

            preparedStatement.setInt(1, studentId);

            ResultSet res = preparedStatement.executeQuery();

            if (res.next()) {
                String studentName = res.getString("studentName");
                String studentEmail = res.getString("studentEmail");
                int departmentId = res.getInt("departmentId");

                student = new Student(studentId, studentName, studentEmail, departmentId);
            }

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    @Override
    public void addStudent(Student student) {
        
    	try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {

            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getStudentEmail());
            preparedStatement.setInt(3, student.getDepartmentId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Inserted rows: " + res);
        } 
    	catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStudent(Student student) {
        
    	try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT)) {

            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getStudentEmail());
            preparedStatement.setInt(3, student.getDepartmentId());
            preparedStatement.setInt(4, student.getStudentId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Updated rows: " + res);
        } 
    	catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(int studentId) {
        
    	try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT)) {

            preparedStatement.setInt(1, studentId);

            int res = preparedStatement.executeUpdate();
            System.out.println("Deleted rows: " + res);

        } 
    	catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
