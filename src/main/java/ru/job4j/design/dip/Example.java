package ru.job4j.design.dip;

import java.util.HashMap;
import java.util.Map;

public class Example {
    class Task {
        int id;
        String description;
        /**
         * 1. Жесткая связь на хранение данных в памяти, для гибкости лучше использовать абстракцию
         */
        Map<Integer, String> storageFiles = new HashMap<>();

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    class TaskTracker {
        /**
         * 2. Жесткая связь на хранение данных в памяти, для гибкости лучше использовать абстракцию
         */
        Map<Integer, Task> storageTasks = new HashMap<>();

        public boolean add(Task task) {
            if (storageTasks.containsKey(task.getId())) {
                /**
                 * 3. Жесткая связь на вывод в консоль, для гибкости лучше использовать абстракцию для логирования
                 */
                System.out.println("Task " + task.getId() + " is already in the tracker");
                return false;
            }
            return storageTasks.put(task.getId(), task) != null;
        }

        public boolean remove(Task task) {
            return storageTasks.remove(task.getId()) != null;
        }
    }
}
