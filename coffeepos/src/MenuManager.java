import java.util.ArrayList;

public class MenuManager {
    private static ArrayList<MenuItem> menuList = new ArrayList<>();

    public static void addItem(MenuItem item) {
        menuList.add(item);
    }

    public static ArrayList<MenuItem> getItems() {
        return menuList;
    }

    public static void removeItem(int index) {
        if (index >= 0 && index < menuList.size()) {
            menuList.remove(index);
        }
    }

    public static void updateItem(int index, MenuItem item) {
        if (index >= 0 && index < menuList.size()) {
            menuList.set(index, item);
        }
    }
}
