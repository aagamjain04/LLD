package OrderManagementSystemLLD.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    public int productCategoryId;
    public String productCategoryName;
    public List<Product> products = new ArrayList<>();
    public double price;


    public void addProduct(Product product){
        products.add(product);
    }

    public void removeProduct(int count){
        for(int i = 0; i<count && !products.isEmpty(); i++){
            products.removeLast();
        }
    }
}
