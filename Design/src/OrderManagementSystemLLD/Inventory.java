package OrderManagementSystemLLD;

import OrderManagementSystemLLD.Product.Product;
import OrderManagementSystemLLD.Product.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {
    public List<ProductCategory> productCategories;

    Inventory(){
        productCategories = new ArrayList<>();
    }

    // add new category
    public void addCategory(int categoryId, String categoryName, double price){
        ProductCategory productCategory = new ProductCategory();
        productCategory.productCategoryId = categoryId;
        productCategory.productCategoryName = categoryName;
        productCategory.price = price;
        productCategories.add(productCategory);
    }

    // add product to category
    public void addProduct(Product product, int productCategoryId){
        for(ProductCategory productCategory: productCategories){
            if(productCategory.productCategoryId == productCategoryId){
                productCategory.addProduct(product);
                return;
            }
        }
    }

    // remove product from category
    public void removeItems(Map<Integer,Integer> productCategoryToCount) {
        for (Map.Entry<Integer, Integer> entry : productCategoryToCount.entrySet()) {
            ProductCategory category = getProductCategoryFromId(entry.getKey());
            if (category != null) {
                category.removeProduct(entry.getValue());
            }
        }
    }

    private ProductCategory getProductCategoryFromId(int productCategoryId){

        for(ProductCategory productCategory: productCategories){
            if(productCategory.productCategoryId == productCategoryId){
                return productCategory;
            }
        }
        return null;
    }
}
