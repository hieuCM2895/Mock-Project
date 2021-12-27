package fa.training.impsystem.consts;

public class RegexConst {

    public static final String REGEX_NAME = "^[a-z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ A-Z]{2,}$";
    public static final String REGEX_USERNAME = "^[a-z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ A-Z 0-9]{2,}$";
    public static final String REGEX_PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    public static final String REGEX_EMAIL = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
    public static final String REGEX_PHONE = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";

    public static final String FORMAT_FIRST_NAME = "Not Correct format first name";
    public static final String FORMAT_LAST_NAME = "Not Correct format last name";
    public static final String FORMAT_EMAIL = "Not Correct format email";
    public static final String FORMAT_PASSWORD = "Not Correct format password";
}
