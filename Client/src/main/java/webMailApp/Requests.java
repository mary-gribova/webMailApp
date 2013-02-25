package webMailApp;

/**
 * Created with IntelliJ IDEA.
 * User: maru
 * Date: 25.02.13
 * Time: 13:58
 * To change this template use File | Settings | File Templates.
 */
public interface Requests {
    public static String REGISTER_USER_REQUEST = "registerNewUser";
    public static String LOGIN_REQUEST = "loginUser";
    public static String SEND_LETTER_REQUEST = "sendNewLetter";
    public static String GET_USER_BY_EMAIL = "getUserByEmail";
    public static String GET_RECIEVED_LETTERS = "getRecievedLetters";
    public static String DEL_LETTERS_REQUEST = "delLetters";
}
