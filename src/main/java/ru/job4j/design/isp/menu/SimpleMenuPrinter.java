package ru.job4j.design.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private static final String SEPARATOR = "--";

    @Override
    public void print(Menu menu) {
        menu.forEach(el -> {
            StringBuilder rst = new StringBuilder().append(el.getNumber()).append(el.getName());
            int count = rst.toString().split("\\.").length;
            for (int i = 2; i < count; i++) {
                rst.insert(0, SEPARATOR);
            }
            System.out.println(rst);
        });
    }
}
