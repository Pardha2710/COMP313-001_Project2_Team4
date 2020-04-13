package nearlynew.com;

import com.google.firebase.database.PropertyName;

public class Sellerslist {

    private String email;

    public Sellerslist(String email) {
        this.email = email;
    }

    public Sellerslist(){

    }

    @PropertyName("email")
    public String getTitle() {
        return email;
    }

    public void setTitle(String email) {
        this.email = email;
    }


}
