package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
@Entity
@Table(name = "course")
public class Course extends Persistent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "course_code")
    private String courseCode;

    @Column(name = "course_title")
    private String courseTitle;

    @OneToMany(mappedBy = "course",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollmentList;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
