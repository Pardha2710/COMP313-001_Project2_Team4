package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
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

public class productOne extends AppCompatActivity {

    TextView name,cat,type,comp,price,loc;
    String keyval,emailval,n1,c1,t1,cc1,p1,l1,i1;
    ImageView iv1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prod_single);

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

                Toast.makeText(productOne.this, "Not Able To Login", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference mPostReference = FirebaseDatabase.getInstance().getReference()
                        .child("products").child(keyval);
                mPostReference.removeValue();

                Intent in = new Intent(productOne.this,Sellermain.class);
                in.putExtra("emailval",emailval);
                startActivity(in);
            }
        });

    }

    @Override
    public void onBackPressed() {

        Intent in = new Intent(productOne.this,Sellermain.class);
        in.putExtra("emailval",emailval);
        startActivity(in);
    }

}
