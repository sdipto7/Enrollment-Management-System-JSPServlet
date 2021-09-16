package net.therap.enrollmentmanagement.domain;

/**
 * @author rumi.dipto
 * @since 9/7/21
 */
public enum Role {

    ADMIN("admin"),
    USER("user");

    private String text;

    Role(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Role getRole(String text) {
        for (Role role : Role.values()) {
            if (role.getText().equalsIgnoreCase(text)) {
                return role;
            }
        }
        return null;
    }
}
