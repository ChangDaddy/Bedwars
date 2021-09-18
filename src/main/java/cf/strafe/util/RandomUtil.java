package cf.strafe.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtil {
    public int randomNumber(int max, int min) {
        return Math.round(min + (float) Math.random() * ((max - min)));
    }
}
