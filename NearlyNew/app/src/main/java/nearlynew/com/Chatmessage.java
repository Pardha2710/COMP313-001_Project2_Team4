package nearlynew.com;

import com.google.firebase.database.PropertyName;

public class Chatmessage {

    private String chat_emailfull;
    private String chat_recev;
    private String chat_sender;
    private String chat_sender_msg;

    public Chatmessage(String chat_emailfull, String chat_recev,
                       String chat_sender, String chat_sender_msg) {
        this.chat_emailfull = chat_emailfull;
        this.chat_recev = chat_recev;
        this.chat_sender = chat_sender;
        this.chat_sender_msg = chat_sender_msg;
    }

    public Chatmessage(){

    }

    @PropertyName("chat_emailfull")
    public String getFull() {
        return chat_emailfull;
    }

    public void setFull(String chat_emailfull) {
        this.chat_emailfull = chat_emailfull;
    }

    @PropertyName("chat_recev")
    public String getRecev() {
        return chat_recev;
    }

    public void setRecev(String chat_recev) {
        this.chat_recev = chat_recev;
    }

    @PropertyName("chat_sender")
    public String getSender() {
        return chat_sender;
    }

    public void setSender(String chat_sender) {
        this.chat_sender = chat_sender;
    }

    @PropertyName("chat_sender_msg")
    public String getMsg() {
        return chat_sender_msg;
    }

    public void setMsg(String chat_sender_msg) {
        this.chat_sender_msg = chat_sender_msg;
    }

   }
