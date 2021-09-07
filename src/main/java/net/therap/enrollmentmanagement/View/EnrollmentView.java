package net.therap.enrollmentmanagement.View;

import net.therap.enrollmentmanagement.domain.Course;
import net.therap.enrollmentmanagement.domain.Enrollment;
import net.therap.enrollmentmanagement.domain.User;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/26/21
 */
public class EnrollmentView {

    public static void printCourse(Course course) {
        System.out.println("Course id: " + course.getId());
        System.out.println("Course code: " + course.getCourseCode());
        System.out.println("Course title: " + course.getCourseTitle());
    }

    public static void printUser(User user) {
        System.out.println("User's id: " + user.getId());
        System.out.println("User's name: " + user.getName());
    }

    public static void printEnrollment(Enrollment enrollment) {
        System.out.println("Enrollment id: " + enrollment.getId());
        printUser(enrollment.getUser());
        printCourse(enrollment.getCourse());
    }

    public static void printCourseList(List<Course> courseList) {
        System.out.println("ID \t Code \t\t Title");
        for (Course course : courseList) {
            System.out.println(course.getId() + " \t " + course.getCourseCode() + " \t "
                    + course.getCourseTitle());
        }
    }

    public static void printUserList(List<User> userList) {
        System.out.println("ID\t\tName");
        for (User user : userList) {
            System.out.println(user.getId() + " - " + user.getName());
        }
    }

    public static void printEnrollmentList(List<Enrollment> enrollmentList) {
        Collections.sort(enrollmentList);
        User userBuffer = null;

        for (Enrollment enrollment : enrollmentList) {
            Course course = enrollment.getCourse();
            if (Objects.isNull(userBuffer) || !(enrollment.getUser().equals(userBuffer))) {
                userBuffer = enrollment.getUser();
                System.out.println("User:\n" + userBuffer.getId() + " - " + userBuffer.getName());
                System.out.println("Courses:\n" + course.getId() + " - " + course.getCourseCode() + " - " +
                        course.getCourseTitle() + " (Enrollment ID: " + enrollment.getId() + ")");
            } else {
                System.out.println(course.getId() + " - " + course.getCourseCode() + " - " +
                        course.getCourseTitle() + " (Enrollment ID: " + enrollment.getId() + ")");
            }
        }
    }
}