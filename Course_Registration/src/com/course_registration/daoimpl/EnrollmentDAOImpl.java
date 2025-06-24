package com.course_registration.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.course_registration.model.Enrollment;
import com.course_registration.dao.EnrollmentDAO;
import com.course_registration.util.DBConnection;

public class EnrollmentDAOImpl implements EnrollmentDAO {

    private final String INSERT_ENROLLMENT = "INSERT INTO `enrollments` (`studentId`, `courseId`, `semester`) VALUES (?, ?, ?)";
    private final String UPDATE_ENROLLMENT = "UPDATE `enrollments` SET `studentId` = ?, `courseId` = ?, `semester` = ? WHERE `enrollmentId` = ?";
    private final String SELECT_ENROLLMENT = "SELECT * FROM `enrollments` WHERE `enrollmentId` = ?";
    private final String DELETE_ENROLLMENT = "DELETE FROM `enrollments` WHERE `enrollmentId` = ?";
    private final String SELECTALL_ENROLLMENTS = "SELECT * FROM `enrollments`";

    @Override
    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> list = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECTALL_ENROLLMENTS)) {

            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                int enrollmentId = res.getInt("enrollmentId");
                int studentId = res.getInt("studentId");
                int courseId = res.getInt("courseId");
                String semester = res.getString("semester");

                Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId, semester);
                list.add(enrollment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public Enrollment getEnrollmentById(int enrollmentId) {
        Enrollment enrollment = null;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ENROLLMENT)) {

            preparedStatement.setInt(1, enrollmentId);
            ResultSet res = preparedStatement.executeQuery();

            while (res.next()) {
                int studentId = res.getInt("studentId");
                int courseId = res.getInt("courseId");
                String semester = res.getString("semester");

                enrollment = new Enrollment(enrollmentId, studentId, courseId, semester);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollment;
    }

    @Override
    public void addEnrollment(Enrollment enrollment) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ENROLLMENT)) {

            preparedStatement.setInt(1, enrollment.getStudentId());
            preparedStatement.setInt(2, enrollment.getCourseId());
            preparedStatement.setString(3, enrollment.getSemester());

            int res = preparedStatement.executeUpdate();
            System.out.println("Inserted rows: " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateEnrollment(Enrollment enrollment) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ENROLLMENT)) {

            preparedStatement.setInt(1, enrollment.getStudentId());
            preparedStatement.setInt(2, enrollment.getCourseId());
            preparedStatement.setString(3, enrollment.getSemester());
            preparedStatement.setInt(4, enrollment.getEnrollmentId());

            int res = preparedStatement.executeUpdate();
            System.out.println("Updated rows: " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEnrollment(int enrollmentId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ENROLLMENT)) {

            preparedStatement.setInt(1, enrollmentId);

            int res = preparedStatement.executeUpdate();
            System.out.println("Deleted rows: " + res);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ New method: For student enrollment action
    @Override
    public void enrollStudent(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (studentId, courseId, semester) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setString(3, enrollment.getSemester());

            int rows = ps.executeUpdate();
            System.out.println("✅ Student enrolled. Rows affected: " + rows);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ New method: View a student's enrolled courses
    @Override
    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM enrollments WHERE studentId = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int enrollmentId = rs.getInt("enrollmentId");
                int courseId = rs.getInt("courseId");
                String semester = rs.getString("semester");

                enrollments.add(new Enrollment(enrollmentId, studentId, courseId, semester));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return enrollments;
    }
}
