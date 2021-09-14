package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author rumi.dipto
 * @since 8/9/21
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.REMOVE)
    private Credential credential;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollmentList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (Objects.isNull(object) || !(object instanceof User)) {
            return false;
        }
        User that = (User) object;

        return this.getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }

    public boolean isNew() {
        return getId() == 0;
    }
}