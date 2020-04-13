package nearlynew.com;

import com.google.firebase.database.PropertyName;

public class Wishproducts {

    private String pname;
    private String pcomp;
    private String pimg;
    private String pprice;
    private String pcat;
    private String ptype;

    public Wishproducts(String pname, String pcomp, String pimg,
                        String pprice,String pcat, String ptype) {
        this.pname = pname;
        this.pcomp = pcomp;
        this.pimg = pimg;
        this.pprice = pprice;
        this.pcat = pcat;
        this.ptype = ptype;
    }

    public Wishproducts(){

    }

    @PropertyName("pname")
    public String getTitle() {
        return pname;
    }

    public void setTitle(String product_name) {
        this.pname = pname;
    }

    @PropertyName("pcomp")
    public String getcomp() {
        return pcomp;
    }

    public void setcomp(String pcomp) {
        this.pcomp = pcomp;
    }

    @PropertyName("pimg")
    public String getImage() {
        return pimg;
    }

    public void setImage(String pimg) {
        this.pimg = pimg;
    }

    @PropertyName("pprice")
    public String getprice() {
        return pprice;
    }

    public void setprice(String pprice) {
        this.pprice = pprice;
    }

    @PropertyName("pcat")
    public String getCategory() {
        return pcat;
    }

    public void setCategory(String pcat) {
        this.pcat = pcat;
    }

    @PropertyName("ptype")
    public String getType() {
        return ptype;
    }

    public void setType(String product_type) {
        this.ptype = ptype;
    }
}
