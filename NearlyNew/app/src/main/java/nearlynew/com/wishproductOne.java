package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class wishproductOne extends AppCompatActivity {

    private TextView name,cat,type,comp,price,loc;
    private String keyval,emailval,n1,c1,t1,cc1,p1,l1,i1,e1;
    private ImageView iv1;
    private  Button btn;
    private String userId;

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wishuserprod_single);

        name = findViewById(R.id.pname);
        cat = findViewById(R.id.pcat);
        type = findViewById(R.id.ptype);
        comp = findViewById(R.id.pcomp);
        price = findViewById(R.id.pprice);
        loc = findViewById(R.id.ploc);
        iv1 = findViewById(R.id.imageView4);


        btn = findViewById(R.id.button);


        emailval = getIntent().getExtras().getString("emailval");
        keyval = getIntent().getExtras().getString("keyval");

        mFirebaseInstance = FirebaseDatabase.getInstance();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        reference.child("wishlist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {


                    if (zoneSnapshot.getKey().equals(keyval)) {


                        n1 = zoneSnapshot.child("pname").getValue(String.class);
                        c1 = zoneSnapshot.child("pcat").getValue(String.class);
                        t1 = zoneSnapshot.child("ptype").getValue(String.class);
                        cc1 = zoneSnapshot.child("pcomp").getValue(String.class);
                        p1 = zoneSnapshot.child("pprice").getValue(String.class);
                        l1 = zoneSnapshot.child("plocation").getValue(String.class);
                        i1 = zoneSnapshot.child("pimg").getValue(String.class);

                    }

                }

                name.setText(n1);
                cat.setText(c1);
                type.setText(t1);
                comp.setText(cc1);
                price.setText(p1);
                loc.setText(l1);
                Picasso.get().load(i1).into(iv1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(wishproductOne.this, "Not Able To Login", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference()
                        .child("wishlist").child(keyval);
                mPostReference.removeValue();

                Intent in = new Intent(wishproductOne.this,Usermain.class);
                in.putExtra("emailval",emailval);
                startActivity(in);



            }
        });


    }

    private void addUserChangeListener() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user == null) {
                    Log.e("Testing11", "User data is null!");
                    Toast.makeText(wishproductOne.this,"Wishlist NOt added",Toast.LENGTH_LONG).show();
                    return;
                }


                Intent in = new Intent(wishproductOne.this,Usermain.class);
                in.putExtra("emailval",emailval);
                startActivity(in);

                Log.e("Testing22", "User data is changed!" + user.name + ", " + user.email);


            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.e("Retived Data", "Failed to read user", error.toException());
            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent in = new Intent(wishproductOne.this,Usermain.class);
        in.putExtra("emailval",emailval);
        startActivity(in);
    }

}
