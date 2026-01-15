package FoodKartLLD.model;

import java.util.HashMap;
import java.util.Map;

public class Restaurant {
    String name;
    int maxCapacity;
    int currentLoad;
    Map<String,Integer> menu;

    public Restaurant(String name, int maxCapacity) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0;
        this.menu = new HashMap<>();
    }

    public void updateMenu(Map<String,Integer> newItems) {
        menu.putAll(newItems);
    }

    public boolean hasItem(String item){
        return menu.containsKey(item);
    }

    public int getPrice(String item){
        return menu.getOrDefault(item,Integer.MAX_VALUE);
    }

    public String getName() {
        return name;
    }

    // Restaurant managing its capacity rule
    public synchronized boolean canAccept(int count){
        return (count+currentLoad <= maxCapacity);
    }

    public synchronized void reserve(int count){
        currentLoad+=count;
    }

    public synchronized void release(int count){
        currentLoad-=count;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", currentLoad=" + currentLoad +
                ", menu=" + menu +
                '}' + "\n";
    }
}
