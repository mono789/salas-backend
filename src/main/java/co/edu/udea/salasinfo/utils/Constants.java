package co.edu.udea.salasinfo.utils;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }


    public static final String INVALID_TOKEN_MESSAGE = "The given token is invalid";
    public static final String ROOM_OCCUPIED_AT_MESSAGE = "Room '%s' is occupied at %s";

    // Null messages
    public static final String ACTIVITY_NAME_FIELD_NOT_NULL_MESSAGE = "'activityName' cannot be null";
    public static final String ACTIVITY_DESCRIPTION_FIELD_NOT_NULL_MESSAGE = "'activityDescription' cannot be null";
    public static final String STARTS_AT_FIELD_NOT_NULL_MESSAGE = "'startsAt' cannot be null";
    public static final String ENDS_AT_FIELD_NOT_NULL_MESSAGE = "'endsAt' cannot be null";
    public static final String TYPE_FIELD_NOT_NULL_MESSAGE = "'type' cannot be null";
    public static final String USER_ID_FIELD_NOT_NULL_MESSAGE = "'userId' cannot be null";
    public static final String ROOM_ID_FIELD_NOT_NULL_MESSAGE = "'roomId' cannot be null";
    public static final String SESSIONS_FIELD_NOT_NULL_MESSAGE = "'sessions' cannot be null";
    public static final String SEMESTER_STARTS_AT_FIELD_NOT_NULL_MESSAGE = "'semesterStartsAt' cannot be null";
    public static final String SEMESTER_ENDS_AT_FIELD_NOT_NULL_MESSAGE = "'semesterEndsAt' cannot be null";
    public static final String DAY_FIELD_NOT_NULL_MESSAGE = "'day' cannot be null";

    // Past dates messages
    public static final String STARTS_AT_FIELD_NOT_PAST_MESSAGE = "'startsAt' cannot be a past date";
    public static final String ENDS_AT_FIELD_NOT_PAST_MESSAGE = "'endsAt' cannot be a past date";

    // Out of bounds
    public static final String SESSION_FIELD_OUT_OF_BOUNDS_MESSAGE = "'sessions' must be between 1 and 3";

    // Day of Week
    public static final String INVALID_DAY_OF_WEEK_MESSAGE = "The given day of week is invalid";
}
