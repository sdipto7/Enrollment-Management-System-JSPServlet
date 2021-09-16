package net.therap.enrollmentmanagement.domain;

/**
 * @author rumi.dipto
 * @since 9/16/21
 */
public enum Action {

    ADD("add"),
    UPDATE("update"),
    DELETE("delete"),
    VIEW("view"),
    EDIT("edit");

    private String text;

    Action(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static Action getAction(String text) {
        for (Action action : Action.values()) {
            if (action.getText().equalsIgnoreCase(text)) {
                return action;
            }
        }
        return null;
    }
}
