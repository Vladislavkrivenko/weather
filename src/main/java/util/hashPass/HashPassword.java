package util.hashPass;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

@Slf4j
@UtilityClass
public class HashPassword {

    public static String hashPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password is null");
        }
        log.debug("Hash password successful");
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        if (hashedPassword == null) {
            throw new IllegalArgumentException("hashedPassword is null");
        }
        return BCrypt.checkpw(password, hashedPassword);
    }
}
