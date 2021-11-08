package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        LOG.debug("Boolean : {}, int : {}, long : {}, char : {}, float : {}, double : {}, byte : {}, short : {}", true, 32, 65999L, 'f', 30.4f, 31.2, (byte) 127, (short) 32767);
    }
}
