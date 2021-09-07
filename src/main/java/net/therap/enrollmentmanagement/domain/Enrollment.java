package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
@Entity
@Table(name = "enrollment")
public class Enrollment implements Comparable<Enrollment>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @NotNull
    private Course course;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isNew() {
        return this.getId() == 0;
    }

    @Override
    public int compareTo(Enrollment enrollment) {
        return (int) this.getUser().getId() - (int) enrollment.getUser().getId();
    }
}