package nearlynew.com;

import com.google.firebase.database.PropertyName;

public class Chatslist {

    private String selleremail;

    public Chatslist(String selleremail) {
        this.selleremail = selleremail;
    }

    public Chatslist(){

    }

    @PropertyName("selleremail")
    public String getTitle() {
        return selleremail;
    }

    public void setTitle(String selleremail) {
        this.selleremail = selleremail;
    }


}
