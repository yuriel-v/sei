package io.sei.exception;

public class AlreadyEnrolledException extends Exception {
    public final String userId;
    public final String subjectId;
    public final String subjectName;

    public AlreadyEnrolledException(String message, String userId, String subjectId, String subjectName)
    {
        super(message == null ? String.format("User is already in subject '%s' - lock and re-enroll to reset it", subjectName) : message);
        this.userId = userId;
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }
}
