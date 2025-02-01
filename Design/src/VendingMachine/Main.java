package VendingMachine;


import java.util.List;

public class Main {
    public static void main(String[] args) {

        VendingMachine vendingMachine = new VendingMachine();

        try{
            System.out.println("|");
            System.out.println("Filling up the inventory");
            System.out.println("|");

            fillUpInventory(vendingMachine);
            displayInventory(vendingMachine);

            System.out.println("|");
            System.out.println("Clicking on insertCoinButton");
            System.out.println("|");

            State vendingState = vendingMachine.getVendingMachineState();
            vendingState.clickOnInsertCoinButton(vendingMachine);

            vendingState = vendingMachine.getVendingMachineState();
            vendingState.insertCoin(vendingMachine, Coin.NICKEL);
            vendingState.insertCoin(vendingMachine, Coin.QUARTER);

            System.out.println("|");
            System.out.println("clicking on ProductSelectionButton");
            System.out.println("|");

            vendingState.clickOnStartProductSelectionButton(vendingMachine);
            vendingState = vendingMachine.getVendingMachineState();
            vendingState.chooseProduct(vendingMachine, 102);

            displayInventory(vendingMachine);






        }
        catch (Exception e){
            displayInventory(vendingMachine);
        }

    }

    private static void fillUpInventory(VendingMachine vendingMachine){
        Inventory inventory = vendingMachine.getInventory();
        int n = inventory.getItemSize();
        List<Item> itemList = inventory.getItemList();
        int codeNumber = 100;
        for(int i =0;i < n; i++){
            Item item = new Item();
            if(i < 3){
                item.setItemType(ItemType.COKE);
                item.setCode(codeNumber++);
                item.setPrice(12);
                item.setSoldOut(false);
            }else if(i >= 3 && i<= 5){
                item.setItemType(ItemType.PEPSI);
                item.setCode(codeNumber++);
                item.setPrice(20);
                item.setSoldOut(false);
            }else if(i > 5 && i<= 7){
                item.setItemType(ItemType.SODA);
                item.setCode(codeNumber++);
                item.setPrice(5);
                item.setSoldOut(false);
            }else if(i > 7){
                item.setItemType(ItemType.JUICE);
                item.setCode(codeNumber++);
                item.setPrice(40);
                item.setSoldOut(false);
            }

            itemList.add(i,item);
        }





    }

    private static void displayInventory(VendingMachine vendingMachine){

        Inventory inventory = vendingMachine.getInventory();
        List<Item> itemList = inventory.itemList;

        for (Item item : itemList) {
            System.out.println("CodeNumber: " + item.getCode() +
                    " Item: " + item.getItemType() +
                    " Price: " + item.getPrice() +
                    " isAvailable: " + !item.isSoldOut());
        }


    }

}
