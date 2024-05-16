package VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class Inventory {



    List<Item> itemList = new ArrayList<Item>();
    int itemSize = 0;
    public int getItemSize() {
        return itemSize;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }



    public Inventory(int itemSize){
        this.itemSize = itemSize;
    }

    public void addItem(Item item){
        itemList.add(item);
    }

    public Item getItem(int codeNumber) throws Exception {
        for (Item item : itemList) {
            if(item.getCode()==codeNumber){
                if (item.isSoldOut()) {
                    throw new Exception("item already sold out");
                } else {

                    return item;
                }
            }
        }
        return null;
    }

    public void updateSoldOutItem(int codeNumber){
        for (Item item : itemList) {
            if(item.getCode()==codeNumber){
                item.setSoldOut(true);
            }
        }
    }



}
