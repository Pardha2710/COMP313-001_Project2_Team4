package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private AppCompatEditText inputEmail,inputPassword;
    private AppCompatButton btnLogin;
    private AppCompatTextView btnLinkToRegister;

    private RadioButton radbut,radrole;

    private RadioGroup roleval;
    private RadioButton radioButton;
    private String passw,role,name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail =  findViewById(R.id.textEmail);
        inputPassword = findViewById(R.id.textPassword);
        roleval = findViewById(R.id.radioRole);
        btnLogin = findViewById(R.id.appCompatButtonLogin);
        btnLinkToRegister = findViewById(R.id.textViewLinkRegister);



        // Progress dialog


        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                int selectedId = roleval.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radrole = findViewById(selectedId);

                String rolevv = radrole.getText().toString().trim();


                if(rolevv.equals("User")){


                                  DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

                    reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                passw = datas.child("password").getValue().toString();
                                role = datas.child("role").getValue().toString();
                            }

                            if (passw != null) {

                                // Toast.makeText(LoginActivity.this, passw, Toast.LENGTH_SHORT).show();

                                if (passw.equals(password)) {
                                    // Toast.makeText(LoginActivity.this, "Password Write", Toast.LENGTH_LONG).show();

                                        Intent in = new Intent(LoginActivity.this, Usermain.class);
                                        in.putExtra("emailval",email);
                                        startActivity(in);
                                        finish();


                                } else {
                                    Toast.makeText(LoginActivity.this, "Password Wrong", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "User Not Registered.", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Toast.makeText(LoginActivity.this, "Not Able To Login", Toast.LENGTH_LONG).show();
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

                                // Toast.makeText(LoginActivity.this, passw, Toast.LENGTH_SHORT).show();

                                if (passw.equals(password)) {
                                    // Toast.makeText(LoginActivity.this, "Password Write", Toast.LENGTH_LONG).show();



                                        Intent in = new Intent(LoginActivity.this, Sellermain.class);
                                        in.putExtra("emailval",email);
                                        startActivity(in);
                                        finish();



                                } else {
                                    Toast.makeText(LoginActivity.this, "Password Wrong", Toast.LENGTH_LONG).show();

                                }
                            } else {
                                Toast.makeText(LoginActivity.this, "User Not Registered.", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                            Toast.makeText(LoginActivity.this, "Not Able To Login", Toast.LENGTH_LONG).show();
                        }
                    });


                }else if(rolevv.equals("Admin")){

                    if(email.equals("admin")){

                        if(password.equals("password123")){

                            Intent in = new Intent(LoginActivity.this,Adminmain.class);
                            startActivity(in);
                        }else{
                            Toast.makeText(LoginActivity.this, "Admin Password Wrong", Toast.LENGTH_LONG).show();
                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "Admin Username Wrong", Toast.LENGTH_LONG).show();
                    }

                }


            }

        });


        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        SignupActivity.class);
                startActivity(i);
                finish();
            }
        });


    }
}
