package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
@Entity
@Table(name = "enrollment")
public class Enrollment extends Persistent implements Comparable<Enrollment>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isNew() {
        return getId() == 0;
    }

    @Override
    public int compareTo(Enrollment that) {
        return this.getUser().getName().compareTo(that.getUser().getName());
    }
}
