package nearlynew.com;

import com.google.firebase.database.PropertyName;

public class Chatslistseller {

    private String buyeremail;

    public Chatslistseller(String buyeremail) {
        this.buyeremail = buyeremail;
    }

    public Chatslistseller(){

    }

    @PropertyName("buyeremail")
    public String getTitle() {
        return buyeremail;
    }

    public void setTitle(String buyeremail) {
        this.buyeremail = buyeremail;
    }


}
