package nearlynew.com;

public class Product {

    public String pname,cat,comp,price,type,imglnk;

    public Product(String pname, String cat, String comp,
                   String price, String type, String imglnk) {
        this.pname = pname;
        this.cat = cat;
        this.comp = comp;
        this.price = price;
        this.type = type;
        this.imglnk = imglnk;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getcat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getComp() {
        return pname;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImglnk() {
        return imglnk;
    }

    public void setImglnk(String imglnk) {
        this.imglnk = imglnk;
    }

}
