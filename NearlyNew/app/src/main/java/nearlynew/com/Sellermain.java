package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Sellermain extends AppCompatActivity  {

    private DrawerLayout drawerLayout;
    private String emailvv, emailval,nameval,imgval;
    private TextView name,email;
    private ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellermain);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        emailvv = getIntent().getExtras().getString("emailval");

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);
        View header = nav_view.getHeaderView(0);
        name = header.findViewById(R.id.fullname);
        email = header.findViewById(R.id.emailid);
        iv = header.findViewById(R.id.img1);


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sellers");


        reference.orderByChild("email").equalTo(emailvv).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {

                    nameval = zoneSnapshot.child("name").getValue(String.class);
                    emailval = zoneSnapshot.child("email").getValue(String.class);


                }

                name.setText(nameval);
                email.setText(emailval);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Sellermain.this, "Not Able To Connect", Toast.LENGTH_LONG).show();
            }
        });


        DatabaseReference refere = FirebaseDatabase.getInstance().getReference("sellersimg");


        refere.orderByChild("email").equalTo(emailvv).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {

                    imgval = zoneSnapshot.child("profileimg").getValue(String.class);
                }

                if(imgval != null){
                    Picasso.get().load(imgval).into(iv);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Sellermain.this, "Not Able To Connect", Toast.LENGTH_LONG).show();
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

        nav_view.setCheckedItem(R.id.home);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();

                        break;
                    case R.id.products:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProductsActivity()).commit();

                        break;
                    case R.id.newpro:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewproFragment()).commit();
                        break;
                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ChatFragment()).commit();

                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();

                        break;
                    case R.id.logout:
                        Intent in = new Intent(Sellermain.this,LoginActivity.class);
                        startActivity(in);
                        finish();
                        break;
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }
}
