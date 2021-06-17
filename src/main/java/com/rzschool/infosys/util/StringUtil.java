package com.rzschool.infosys.util;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class StringUtil {

    public static String randomUUID() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextLong(), random.nextLong()).toString().replace("-", "");
    }
}
