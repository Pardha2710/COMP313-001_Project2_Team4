package nearlynew.com;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class User_single extends AppCompatActivity {

    String keyval;
    private TextView name,email,phone, et;
    private String emailval,n1,e1,p1,i1;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminprofilefrag);


        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        et = findViewById(R.id.addImage);
        btn = findViewById(R.id.button);


        keyval = getIntent().getExtras().getString("keyval");



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        reference.child("sellers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {
                    Log.d("info", zoneSnapshot.child("name").getValue(String.class));

                    if (zoneSnapshot.getKey().equals(keyval)) {

                        n1 = zoneSnapshot.child("name").getValue(String.class);
                        e1 = zoneSnapshot.child("email").getValue(String.class);
                        p1 = zoneSnapshot.child("phone").getValue(String.class);

                    }

                }

                name.setText(n1);
                email.setText(e1);
                phone.setText(p1);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(User_single.this, "Not Able To Connect", Toast.LENGTH_LONG).show();
            }
        });





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference()
                        .child("sellers").child(keyval);
                mPostReference.removeValue();

                Intent in = new Intent(User_single.this,Adminmain.class);
                startActivity(in);
            }
        });





    }



}
