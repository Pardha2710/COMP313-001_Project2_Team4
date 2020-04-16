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

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class userproductOne extends AppCompatActivity {

    private TextView name,cat,type,comp,price,loc;
    private String keyval,emailval,n1,c1,t1,cc1,p1,l1,i1,e1;
    private ImageView iv1;
    private  Button btn,btn2;
    private String userId;


    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprod_single);

        name = findViewById(R.id.pname);
        cat = findViewById(R.id.pcat);
        type = findViewById(R.id.ptype);
        comp = findViewById(R.id.pcomp);
        price = findViewById(R.id.pprice);
        loc = findViewById(R.id.ploc);
        iv1 = findViewById(R.id.imageView4);


        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);



        emailval = getIntent().getExtras().getString("emailval");
        keyval = getIntent().getExtras().getString("keyval");

        mFirebaseInstance = FirebaseDatabase.getInstance();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        reference.child("products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {


                    if (zoneSnapshot.getKey().equals(keyval)) {


                        n1 = zoneSnapshot.child("product_name").getValue(String.class);
                        c1 = zoneSnapshot.child("product_category").getValue(String.class);
                        t1 = zoneSnapshot.child("product_type").getValue(String.class);
                        cc1 = zoneSnapshot.child("product_comp").getValue(String.class);
                        p1 = zoneSnapshot.child("product_price").getValue(String.class);
                        l1 = zoneSnapshot.child("product_location").getValue(String.class);
                        i1 = zoneSnapshot.child("product_img1").getValue(String.class);
                        e1 = zoneSnapshot.child("product_email").getValue(String.class);

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

                Toast.makeText(userproductOne.this, "Not Able To Login", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mFirebaseDatabase = mFirebaseInstance.getReference("wishlist");

                if (TextUtils.isEmpty(userId)) {
                    userId = mFirebaseDatabase.push().getKey();
                }



                Wishlist user = new Wishlist(emailval,keyval,n1,e1,p1,c1,t1,cc1,i1,l1);

                mFirebaseDatabase.child(userId).setValue(user);

                addUserChangeListener();


            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String femil = emailval+e1;


                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                final DatabaseReference userNameRef = rootRef.child("chat_users");
                Query queries=userNameRef.orderByChild("fullemail").equalTo(femil);
                ValueEventListener eventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(!dataSnapshot.exists()) {

                            mFirebaseDatabase = mFirebaseInstance.getReference("chat_users");
                            if (TextUtils.isEmpty(userId)) {
                                userId = mFirebaseDatabase.push().getKey();
                            }

                            String selleremail = e1;
                            String buyeremail = emailval;
                            String fullemail = emailval+e1;


                            Chatemail chatemail = new Chatemail(buyeremail,selleremail,fullemail);

                            mFirebaseDatabase.child(userId).setValue(chatemail);

                            addUserChangeListenerChat();


                        }else{
                            Intent in = new Intent(userproductOne.this,Usermain.class);
                            in.putExtra("emailval",emailval);
                            startActivity(in);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                };
                queries.addListenerForSingleValueEvent(eventListener);

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
                    Toast.makeText(userproductOne.this,"Wishlist NOt added",Toast.LENGTH_LONG).show();
                    return;
                }


                Intent in = new Intent(userproductOne.this,Usermain.class);
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

    private void addUserChangeListenerChat() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user == null) {
                    Log.e("Testing11", "User data is null!");
                    Toast.makeText(userproductOne.this,"Chat Memeber adding failed",Toast.LENGTH_LONG).show();
                    return;
                }


                Intent in = new Intent(userproductOne.this,Usermain.class);
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

        Intent in = new Intent(userproductOne.this,Usermain.class);
        in.putExtra("emailval",emailval);
        startActivity(in);
    }

}
