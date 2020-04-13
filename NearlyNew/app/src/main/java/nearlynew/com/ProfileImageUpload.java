package nearlynew.com;

public class ProfileImageUpload {


    public String email, profileimg;
    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public ProfileImageUpload() {
    }

    public ProfileImageUpload(String email, String profileimg) {

        this.profileimg = profileimg;
        this.email = email;

    }
}
