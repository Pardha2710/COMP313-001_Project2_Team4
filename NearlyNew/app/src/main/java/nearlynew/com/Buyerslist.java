package nearlynew.com;

import com.google.firebase.database.PropertyName;

public class Buyerslist {

    private String email;

    public Buyerslist(String email) {
        this.email = email;
    }

    public Buyerslist(){

    }
    @PropertyName("email")
    public String getTitle() {
        return email;
    }

    public void setTitle(String email) {
        this.email = email;
    }


}
