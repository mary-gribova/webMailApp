package webMailApp;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 15.02.13
 * Time: 17:23
 * To change this template use File | Settings | File Templates.
 */
public interface ProjectConstants {
    public static final int SOCKET = 5555;
    public static String REGISTER_USER_REQUEST = "registerNewUser";
    public static String LOGIN_REQUEST = "loginUser";
    public static String SEND_LETTER_REQUEST = "sendNewLetter";
    public static String GET_USER_BY_SESSION_ID_REQUEST = "getUserBySessionID";
    public static String GET_RECIEVED_LETTERS = "getRecievedLetters";
    public static String DEL_LETTERS_REQUEST = "delLetters";
}
