package ru.job4j.design.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(el ->
                System.out.println(
                        el.getNumber().concat(el.getName())
                )
        );
    }
}
