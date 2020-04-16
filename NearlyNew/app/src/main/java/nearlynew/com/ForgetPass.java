package nearlynew.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgetPass extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private Button btnLogin,btnBack;
    private TextView tv;
    private RadioButton radbut,radrole;
    private RadioGroup roleval;
    private RadioButton radioButton;
    private String passw, role, name;
    private TextView etreg;


    FirebaseAuth mAuth;

    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        inputEmail = findViewById(R.id.email);
        roleval = findViewById(R.id.radioRole);
        btnLogin = findViewById(R.id.btn_reset_password);
        btnBack = findViewById(R.id.btn_back);


        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();

                int selectedId = roleval.getCheckedRadioButtonId();

                radrole = findViewById(selectedId);

                String rolevv = radrole.getText().toString().trim();


                if(rolevv.equals("Buyer")){


                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

                    reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                passw = datas.child("password").getValue().toString();
                                role = datas.child("role").getValue().toString();
                            }

                            if (passw != null) {



                                String msg = "The Password for Your account is :"+ passw;


                                BackgroundMail.newBuilder(ForgetPass.this)
                                        .withUsername("pardhasardhi4@gmail.com")
                                        .withPassword("pardhasardhi572")
                                        .withMailto(email)
                                        .withType(BackgroundMail.TYPE_PLAIN)
                                        .withSubject("Password of Your Account")
                                        .withBody(msg)
                                        .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                                            @Override
                                            public void onSuccess() {
                                                //do some magic
                                                Toast.makeText(ForgetPass.this,"OTP Mail Sent",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                                            @Override
                                            public void onFail() {
                                                //do some magic
                                                Toast.makeText(ForgetPass.this,"OTP Mail Failed",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .send();

                                try {

                                } catch (Exception e) {
                                    Log.e("SendMail", e.getMessage(), e);
                                }


                                Toast.makeText(ForgetPass.this, "Password is Sent to your email.", Toast.LENGTH_SHORT).show();

                                    Intent in = new Intent(ForgetPass.this, LoginActivity.class);
                                    startActivity(in);
                                    finish();

                            } else {
                                Toast.makeText(ForgetPass.this, "User Not Registered.", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Toast.makeText(ForgetPass.this, "Not Able To Connect", Toast.LENGTH_LONG).show();
                        }
                    });

                }else if(rolevv.equals("Seller")){

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sellers");

                    reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                passw = datas.child("password").getValue().toString();
                                role = datas.child("role").getValue().toString();
                            }

                            if (passw != null) {

                                String msg = "The Password for Your account is :"+ passw;


                                BackgroundMail.newBuilder(ForgetPass.this)
                                        .withUsername("pardhasardhi4@gmail.com")
                                        .withPassword("pardhasardhi572")
                                        .withMailto(email)
                                        .withType(BackgroundMail.TYPE_PLAIN)
                                        .withSubject("Password of Your Account")
                                        .withBody(msg)
                                        .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                                            @Override
                                            public void onSuccess() {
                                                //do some magic
                                                Toast.makeText(ForgetPass.this,"OTP Mail Sent",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                                            @Override
                                            public void onFail() {
                                                //do some magic
                                                Toast.makeText(ForgetPass.this,"OTP Mail Failed",Toast.LENGTH_LONG).show();
                                            }
                                        })
                                        .send();

                                try {

                                } catch (Exception e) {
                                    Log.e("SendMail", e.getMessage(), e);
                                }


                                Toast.makeText(ForgetPass.this, "Password is Sent to your email.", Toast.LENGTH_SHORT).show();

                                Intent in = new Intent(ForgetPass.this, LoginActivity.class);
                                    startActivity(in);
                                    finish();

                            } else {
                                Toast.makeText(ForgetPass.this, "Seller Not Registered.", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Toast.makeText(ForgetPass.this, "Not Able To Connect", Toast.LENGTH_LONG).show();
                        }
                    });


                }


            }

        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ForgetPass.this, LoginActivity.class);
                startActivity(in);
            }
        });
    }
}
