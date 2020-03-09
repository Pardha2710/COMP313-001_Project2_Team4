package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;
import java.util.Random;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener  {

    private AppCompatEditText fname, mname,lname, email,phone, pass,cpass,org,country, city, postal;
    private RadioGroup gender,role;
    private RadioButton radbut,radrole;
    private AppCompatButton register;
    private AppCompatTextView loglink;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;
    private String emailval;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        fname = findViewById(R.id.textFname);
        email = findViewById(R.id.textEmail);
        phone = findViewById(R.id.textPhone);
        gender = findViewById(R.id.radioSex);
        role = findViewById(R.id.radioRole);
        pass = findViewById(R.id.textPassword);
        cpass = findViewById(R.id.textConfirmPassword);

        register = findViewById(R.id.appCompatRegister);
        loglink = findViewById(R.id.textlogin);

        mFirebaseInstance = FirebaseDatabase.getInstance();



        initListeners();


    }


    /**
     * This method is to initialize listeners
     */
    private void initListeners() {

        register.setOnClickListener(this);
        loglink.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.appCompatRegister:
                registercheck();
                break;

            case R.id.textlogin:
                Intent ii = new Intent(SignupActivity.this,LoginActivity.class);
                startActivity(ii);
                finish();
                break;
        }

    }


    private void registercheck(){
        String namef = fname.getText().toString().trim();
        emailval = email.getText().toString().trim();
        String password = pass.getText().toString().trim();
        String cpassword = cpass.getText().toString().trim();
        String number = phone.getText().toString().trim();

        int selectedId = gender.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radbut = findViewById(selectedId);

        int selectedId1 = role.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radrole = findViewById(selectedId1);

        String gen = radbut.getText().toString().trim();
        String rolev = radrole.getText().toString().trim();
        String active = "1";

        if (!namef.isEmpty() && !emailval.isEmpty() && !password.isEmpty()
                && !cpassword.isEmpty() && !number.isEmpty()) {
            if(password.equals(cpassword)) {

                createUser(namef, emailval, number, password, rolev, active, gen);



            }else{
                Toast.makeText(getApplicationContext(),
                        "Password and confirm Password Should be same", Toast.LENGTH_LONG)
                        .show();
            }
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please enter your details!", Toast.LENGTH_LONG)
                    .show();
        }
    }


    private void createUser(String name, String email, String phone, String password,
                            String role, String active, String gender) {
        // TODO
        // In real apps this userId should be fetched
        // by implementing firebase auth


        if(role.equals("User")) {

            // get reference to 'users' node
            mFirebaseDatabase = mFirebaseInstance.getReference("users");
        }else{
            // get reference to 'users' node
            mFirebaseDatabase = mFirebaseInstance.getReference("sellers");
        }

        if (TextUtils.isEmpty(userId)) {
            userId = mFirebaseDatabase.push().getKey();
        }

        User user = new User(name, email, phone, password, role, active, gender);

        mFirebaseDatabase.child(userId).setValue(user);

        addUserChangeListener();
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e("Testing11", "User data is null!");
                    Toast.makeText(SignupActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                    return;
                }

                final int min = 1000;
                final int max = 9999;
                final int random = new Random().nextInt((max - min) + 1) + min;

                String msg = "Please Enter the following OTP to Succesfully Register with nearly New :"+ Integer.toString(random);

                BackgroundMail.newBuilder(SignupActivity.this)
                        .withUsername("pardhasardhi4@gmail.com")
                        .withPassword("pardhasardhi572")
                        .withMailto(emailval)
                        .withType(BackgroundMail.TYPE_PLAIN)
                        .withSubject("OTP verification")
                        .withBody(msg)
                        .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                            @Override
                            public void onSuccess() {
                                //do some magic
                                Toast.makeText(SignupActivity.this,"OTP Mail Sent",Toast.LENGTH_LONG).show();
                            }
                        })
                        .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                            @Override
                            public void onFail() {
                               //do some magic
                                Toast.makeText(SignupActivity.this,"OTP Mail Failed",Toast.LENGTH_LONG).show();
                            }
                        })
                        .send();

                try {
                    /*
                    GMailSender sender = new GMailSender("pardhasardhi4@gmail.com", "pardhasardhi572");
                    sender.sendMail("OTP Verification from Nearly New",
                            msg,
                            "pardhasardhi4@gmail.com",
                            emailval);

                     */

                   // Toast.makeText(SignupActivity.this,emailval,Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }


              //  Toast.makeText(SignupActivity.this,"Registration Success. Please Enter Otp",Toast.LENGTH_LONG).show();

                Intent in = new Intent(SignupActivity.this,OtpActivity.class);
                in.putExtra("otpval",Integer.toString(random));
                startActivity(in);

                Log.e("Testing22", "User data is changed!" + user.name + ", " + user.email);




                // Display newly updated name and email
                //txtDetails.setText(user.name + ", " + user.email);

                // clear edit text
               // inputEmail.setText("");
                //inputName.setText("");

                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Retived Data", "Failed to read user", error.toException());
            }
        });
    }
}