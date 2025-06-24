package com.course_registration.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course_registration.dao.CourseDAO;
import com.course_registration.model.Course;
import com.course_registration.util.DBConnection;

public class CourseDAOImpl implements CourseDAO {

    private final String INSERT_COURSE = "INSERT INTO `courses` (`courseName`, `credits`, `departmentId`) VALUES (?, ?, ?)";
    private final String UPDATE_COURSE = "UPDATE `courses` SET `courseName` = ?, `credits` = ?, `departmentId` = ? WHERE `courseId` = ?";
    private final String DELETE_COURSE = "DELETE FROM `courses` WHERE `courseId` = ?";
    private final String SELECT_COURSE = "SELECT * FROM `courses` WHERE `courseId` = ?";
    private final String SELECTALL_COURSES = "SELECT * FROM `courses`";

    @Override
    public List<Course> getAllCourses() {
        List<Course> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTALL_COURSES)) {

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                int courseId = res.getInt("courseId");
                String courseName = res.getString("courseName");
                int credits = res.getInt("credits");
                int departmentId = res.getInt("departmentId");

                Course course = new Course(courseId, courseName, credits, departmentId);
                list.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Course getCourseById(int courseId) {
        Course course = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COURSE)) {

            preparedStatement.setInt(1, courseId);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                String courseName = res.getString("courseName");
                int credits = res.getInt("credits");
                int departmentId = res.getInt("departmentId");

                course = new Course(courseId, courseName, credits, departmentId);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return course;
    }

    @Override
    public void addCourse(Course course) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COURSE)) {

            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getCredits());
            preparedStatement.setInt(3, course.getDepartmentId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Inserted rows: " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCourse(Course course) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COURSE)) {

            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getCredits());
            preparedStatement.setInt(3, course.getDepartmentId());
            preparedStatement.setInt(4, course.getCourseId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Updated rows: " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCourse(int courseId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_COURSE)) {

            preparedStatement.setInt(1, courseId);
            int res = preparedStatement.executeUpdate();
            System.out.println("Deleted rows: " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ Newly Added Method
    @Override
    public List<Course> getCoursesByDepartment(int departmentId) {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses WHERE departmentId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, departmentId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int courseId = rs.getInt("courseId");
                String courseName = rs.getString("courseName");
                int credits = rs.getInt("credits");
                int deptId = rs.getInt("departmentId");

                Course course = new Course(courseId, courseName, credits, deptId);
                courses.add(course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }
}
