package ru.job4j.design.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    public static final ActionDelegate STUB_ACTION = System.out::println;
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (findItem(childName).isPresent()) {
            return false;
        }
        MenuItem item = new SimpleMenuItem(childName, actionDelegate);
        if (parentName == Menu.ROOT) {
            return rootElements.add(item);
        }
        Optional<ItemInfo> parentItem = findItem(parentName);
        if (parentItem.isPresent()) {
            return parentItem.get().menuItem.getChildren().add(item);
        }
        return false;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(i -> new MenuItemInfo(i.menuItem, i.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator dfs = new DFSIterator();

        return new Iterator<MenuItemInfo>() {
            @Override
            public boolean hasNext() {
                return dfs.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = dfs.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    public Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rst = Optional.empty();
        DFSIterator dfs = new DFSIterator();
        while (dfs.hasNext()) {
            ItemInfo item = dfs.next();
            if (item.menuItem.getName().equals(name)) {
                rst = Optional.of(item);
                break;
            }
        }
        return rst;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        Deque<MenuItem> stack = new LinkedList<>();
        Deque<String> numbers = new LinkedList<>();

        public DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
