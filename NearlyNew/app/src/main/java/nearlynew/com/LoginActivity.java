package nearlynew.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

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

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail,inputPassword;
    private Button btnLogin;
    private TextView tv;

    private RadioButton radbut,radrole;

    private RadioGroup roleval;
    private RadioButton radioButton;
    private String passw,role,name;
    private TextView etreg;

    //a constant for detecting the login intent result
    private static final int RC_SIGN_IN = 234;

    //Tag for the logs optional
    private static final String TAG = "Nearly New";

    //creating a GoogleSignInClient object
    GoogleSignInClient mGoogleSignInClient;

    //And also a Firebase Auth object
    FirebaseAuth mAuth;

    ImageView iv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputEmail =  findViewById(R.id.textEmail);
        inputPassword = findViewById(R.id.textPassword);
        roleval = findViewById(R.id.radioRole);
        btnLogin = findViewById(R.id.appCompatButtonLogin);
        etreg = findViewById(R.id.textViewLinkRegister);
        iv1 = findViewById(R.id.imageView3);
        tv = findViewById(R.id.textViewLinkForget);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this,ForgetPass.class);
                startActivity(in);
            }
        });



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
        etreg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        SignupActivity.class);
                startActivity(i);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        //Then we need a GoogleSignInOptions object
        //And we need to build it as below
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        //Then we will get the GoogleSignInClient object from GoogleSignIn class
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);





        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });





    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //if the requestCode is the Google Sign In code that we defined at starting
        if (requestCode == RC_SIGN_IN) {

            //Getting the GoogleSignIn Task
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                //Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                //authenticating with firebase
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        //getting the auth credential
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        //Now using firebase we are signing in the user here
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            String emaill = user.getEmail(); // Valid email
                            String nameva = user.getDisplayName(); // null
                            Uri userimg = user.getPhotoUrl(); // null
                            

                            Toast.makeText(LoginActivity.this,emaill+' '+nameva,Toast.LENGTH_LONG).show();

                            /*

                            Toast.makeText(LoginActivity.this, "Signed In", Toast.LENGTH_SHORT).show();
                            Intent in = new Intent(LoginActivity.this,Usermain.class);
                            in.putExtra("emailval",acct);
                            startActivity(in);

                             */


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }


    //this method is called on click
    private void signIn() {
        //getting the google signin intent
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        //starting the activity for result
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}
