package nearlynew.com;

import com.google.firebase.database.PropertyName;

public class Products {

    private String product_name;
    private String product_comp;
    private String product_img1;
    private String product_price;
    private String product_category;
    private String product_type;

    public Products(String product_name, String product_comp, String product_img1, String product_price,
                    String product_category, String product_type) {
        this.product_name = product_name;
        this.product_comp = product_comp;
        this.product_img1 = product_img1;
        this.product_price = product_price;
        this.product_category = product_category;
        this.product_type = product_type;
    }

    public Products(){

    }

    @PropertyName("product_name")
    public String getTitle() {
        return product_name;
    }

    public void setTitle(String product_name) {
        this.product_name = product_name;
    }

    @PropertyName("product_comp")
    public String getcomp() {
        return product_comp;
    }

    public void setcomp(String product_comp) {
        this.product_comp = product_comp;
    }
    @PropertyName("product_img1")
    public String getImage() {
        return product_img1;
    }

    public void setImage(String product_img1) {
        this.product_img1 = product_img1;
    }

    @PropertyName("product_price")
    public String getprice() {
        return product_price;
    }

    public void setprice(String product_price) {
        this.product_price = product_price;
    }

    @PropertyName("product_category")
    public String getCategory() {
        return product_category;
    }

    public void setCategory(String product_category) {
        this.product_category = product_category;
    }

    @PropertyName("product_type")
    public String getType() {
        return product_type;
    }

    public void setType(String product_type) {
        this.product_type = product_type;
    }
}
