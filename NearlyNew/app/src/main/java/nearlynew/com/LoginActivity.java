package nearlynew.com;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
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

import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;

import com.facebook.CallbackManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail,inputPassword;
    private Button btnLogin;
    private TextView tv;

    LoginButton loginButton;
    CallbackManager callbackManager;

    private RadioButton radbut,radrole;

    private RadioGroup roleval;
    private RadioButton radioButton;
    private String passw,role,name;
    private TextView etreg;

    private static final int RC_SIGN_IN = 234;

    private static final String TAG = "Nearly New";

    GoogleSignInClient mGoogleSignInClient;

    FirebaseAuth mAuth;

    ImageView iv1,iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        printHashKey(getBaseContext());

        inputEmail = findViewById(R.id.textEmail);
        inputPassword = findViewById(R.id.textPassword);
        roleval = findViewById(R.id.radioRole);
        btnLogin = findViewById(R.id.appCompatButtonLogin);
        etreg = findViewById(R.id.textViewLinkRegister);
        iv1 = findViewById(R.id.imageView3);
        tv = findViewById(R.id.textViewLinkForget);
        loginButton = findViewById(R.id.login_button);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoginActivity.this, ForgetPass.class);
                startActivity(in);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                int selectedId = roleval.getCheckedRadioButtonId();

                radrole = findViewById(selectedId);

                String rolevv = radrole.getText().toString().trim();

                if (rolevv.equals("Buyer")) {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

                    reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                passw = datas.child("password").getValue().toString();
                                role = datas.child("role").getValue().toString();
                            }

                            if (passw != null) {

                                if (passw.equals(password)) {

                                    Intent in = new Intent(LoginActivity.this, Usermain.class);
                                    in.putExtra("emailval", email);
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

                } else if (rolevv.equals("Seller")) {

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sellers");

                    reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                passw = datas.child("password").getValue().toString();
                                role = datas.child("role").getValue().toString();
                            }

                            if (passw != null) {

                                if (passw.equals(password)) {

                                    Intent in = new Intent(LoginActivity.this, Sellermain.class);
                                    in.putExtra("emailval", email);
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


                } else if (rolevv.equals("Admin")) {

                    if (email.equals("admin")) {

                        if (password.equals("password123")) {

                            Intent in = new Intent(LoginActivity.this, Adminmain.class);
                            startActivity(in);
                        } else {
                            Toast.makeText(LoginActivity.this, "Admin Password Wrong", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, "Admin Username Wrong", Toast.LENGTH_LONG).show();
                    }

                }


            }

        });

        etreg.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        SignupActivity.class);
                startActivity(i);
                finish();
            }
        });

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");

                AccessToken accessToken = AccessToken.getCurrentAccessToken();

                getUserProfile(accessToken);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {

            }
        });


    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {

                            String email = object.getString("email");

                            Intent in = new Intent(LoginActivity.this, Usermain.class);
                            in.putExtra("emailval",email);
                            startActivity(in);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
    private void firebaseAuthWithGoogle(final GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            String emaill = user.getEmail();
                            String nameva = user.getDisplayName();
                            Uri userimg = user.getPhotoUrl();


                            Intent in = new Intent(LoginActivity.this, Usermain.class);
                            in.putExtra("emailval",emaill);
                            startActivity(in);
                            finish();


                        } else {

                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }


    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();

        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    public static void printHashKey(Context pContext) {
        try {
            PackageInfo info = pContext.getPackageManager().getPackageInfo(pContext.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String hashKey = new String(Base64.encode(md.digest(), 0));
                Log.i("Hasheyyy", "printHashKey() Hash Key: " + hashKey);
            }
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "printHashKey()", e);
        } catch (Exception e) {
            Log.e(TAG, "printHashKey()", e);
        }
    }
}
