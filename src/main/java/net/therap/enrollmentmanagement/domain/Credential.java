package net.therap.enrollmentmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author rumi.dipto
 * @since 9/7/21
 */
@Entity
@Table(name = "credential")
public class Credential  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @Override
    public String toString() {
        return "Credential{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Column(name = "password")
    @NotNull
    @Size(min = 2, max = 45)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @NotNull
    @Size(min = 2, max = 10)
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
