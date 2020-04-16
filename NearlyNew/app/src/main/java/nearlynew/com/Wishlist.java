package nearlynew.com;

public class Wishlist {

    public String pname;
    public String pemail;
    public String pprice;
    public String pcat;
    public String ptype;
    public String pcomp;
    public String pimg;
    public String plocation;
    public String email;
    public  String pid;

    public Wishlist() {
    }

    public Wishlist(String email, String pid,
                    String pname, String pemail, String pprice,
                    String pcat, String ptype, String pcomp,
                    String pimg, String plocation
                    ) {
        this.pid = pid;
        this.email = email;
        this.pname = pname;
        this.pemail = pemail;
        this.pprice = pprice;
        this.pcat = pcat;
        this.ptype = ptype;
        this.pcomp = pcomp;
        this.pimg = pimg;
        this.plocation = plocation;

    }
}
