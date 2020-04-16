package nearlynew.com;

public class ImageUpload {

    public String product_name;
    public String product_email;
    public String product_price;
    public String product_category;
    public String product_type;
    public String product_comp;
    public String product_img1;
    public String product_location;

    public ImageUpload() {
    }

    public ImageUpload(String product_name,String product_email,String product_price,
                       String product_category,String product_type,String product_comp,
                       String product_img1,String product_location) {
        this.product_name = product_name;
        this.product_email = product_email;
        this.product_price = product_price;
        this.product_category = product_category;
        this.product_type = product_type;
        this.product_comp = product_comp;
        this.product_img1 = product_img1;
        this.product_location = product_location;

    }
}
