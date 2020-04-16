package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OtpActivity extends AppCompatActivity {

    private EditText et;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

     et = findViewById(R.id.textOtp);
     btn = findViewById(R.id.appCompatOtp);

     final String Otp = getIntent().getExtras().getString("otpval");

     btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             String otpver = et.getText().toString();

             if(otpver.equals(Otp)){

                 Toast.makeText(OtpActivity.this,"OTP Correct,Please Login",Toast.LENGTH_LONG).show();
                 Intent in = new Intent(OtpActivity.this,LoginActivity.class);
                 startActivity(in);

             }else{
                 Toast.makeText(OtpActivity.this,"OTP Wrong Enter Again",Toast.LENGTH_LONG).show();
             }


         }
     });



    }
}
