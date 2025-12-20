package OrderManagementSystemLLD.User;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    public Map<Integer,Integer> productCatIdVsCountMap;

    Cart(){
        productCatIdVsCountMap = new HashMap<>();
    }

    // add item to cart
    public void addItemToCart(int productCatId, int count){
        productCatIdVsCountMap.put(productCatId, productCatIdVsCountMap.getOrDefault(productCatId,0)+ count);
    }

    //remove item from cart
    public void removeItemFromCart(int productCatId, int count){
        if(productCatIdVsCountMap.containsKey(productCatId)){
            int existingCount = productCatIdVsCountMap.get(productCatId);
            if(existingCount-count==0){
                productCatIdVsCountMap.remove(productCatId);
            } else {
                productCatIdVsCountMap.put(productCatId, existingCount - count);
            }
        }
    }

    //empty cart
    public void emptyCart(){
        productCatIdVsCountMap.clear();
    }

    // view cart
    public Map<Integer,Integer> viewCart(){
        return productCatIdVsCountMap;
    }
}
