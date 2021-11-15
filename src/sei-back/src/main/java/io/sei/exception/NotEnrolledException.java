package io.sei.exception;

public class NotEnrolledException extends Exception {
    public final String userId;
    public final String subjectId;
    public final String subjectName;

    public NotEnrolledException(String message, String userId, String subjectId, String subjectName)
    {
        super(message == null ? String.format("User is not enrolled in subject '%s'", subjectName) : message);
        this.userId = userId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }
}