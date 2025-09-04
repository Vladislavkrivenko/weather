package util.hashPass;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
@Slf4j
@UtilityClass
public class HashPassword {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
