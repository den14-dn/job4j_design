package ru.job4j.gc;

public class User {
    private int id;
    private String name;

    private static final long KB = 1000;
    private static final long MB = KB * KB;
    private static final Runtime ENVIRONMENT = Runtime.getRuntime();

    public static void info() {
        final long freeMemory = ENVIRONMENT.freeMemory();
        final long totalMemory = ENVIRONMENT.totalMemory();
        final long maxMemory = ENVIRONMENT.maxMemory();
        System.out.println("=== Environment state ===");
        System.out.printf("Free: %d%n", freeMemory / MB);
        System.out.printf("Total: %d%n", totalMemory / MB);
        System.out.printf("Max: %d%n", maxMemory / MB);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", id, name);
    }

    public static void main(String[] args) {
        info();
        for (int i = 0; i < 40000; i++) {
            new User();
        }
//        System.gc();
        info();
    }
}
