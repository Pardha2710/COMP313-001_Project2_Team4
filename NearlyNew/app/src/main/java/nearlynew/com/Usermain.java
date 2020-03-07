package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Usermain extends AppCompatActivity  {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.usermain);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView nav_view = findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserHomeFragment()).commit();

        nav_view.setCheckedItem(R.id.home);

        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId())
                {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new UserHomeFragment()).commit();

                        break;
                    case R.id.products:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserProductsFragment()).commit();

                        break;
                    case R.id.chat:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserChatFragment()).commit();
                        break;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new UserProfileFragment()).commit();

                        break;
                    case R.id.logout:
                        Intent in = new Intent(Usermain.this,LoginActivity.class);
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

