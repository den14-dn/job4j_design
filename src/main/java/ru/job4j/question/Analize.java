package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, User> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user);
        }
        for (User user : current) {
            User prevUser = map.get(user.getId());
            if (prevUser == null) {
                info.setAdded(info.getAdded() + 1);
            } else {
                if (!prevUser.equals(user)) {
                    info.setChanged(info.getChanged() + 1);
                }
                map.remove(user.getId());
            }
            info.setDeleted(map.size());
        }
        return info;
    }
}
