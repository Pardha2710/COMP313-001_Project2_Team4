package nearlynew.com;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class searchProducts extends AppCompatActivity {

    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Products, ProductsActivity.NewsViewHolder> mPeopleRVAdapter;
    String emailvv,sval;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userproductsfrag);

        emailvv = getIntent().getExtras().getString("emailval");
        sval = getIntent().getExtras().getString("sval");


        tv = findViewById(R.id.banner);

        tv.setText("Searched Products");


        //getActionBar().setDisplayHomeAsUpEnabled(true);

        //"News" here will reflect what you have called your database in Firebase.
        mDatabase = FirebaseDatabase.getInstance().getReference().child("products");
        mDatabase.keepSynced(true);

        mPeopleRV = (RecyclerView) findViewById(R.id.myRecycleView);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("products");
        Query personsQuery = personsRef.orderByChild("product_comp").equalTo(sval);

         //Log.e("HYDE", String.valueOf(personsQuery));

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Products> personsOptions = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(personsQuery, Products.class)
                .setLifecycleOwner(this)
                .build();

        Log.d("Options", " data : " + personsOptions);

        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Products, ProductsActivity.NewsViewHolder>(personsOptions) {


            @NonNull
            @Override
            public ProductsActivity.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prod_rows, parent, false);

                return new ProductsActivity.NewsViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(ProductsActivity.NewsViewHolder holder, final int position, final Products model) {
                holder.setTitle("Name: "+model.getTitle());
                //  holder.setDesc("Company: "+model.getcomp());
                holder.setPrice("Price: "+model.getprice());
                holder.setCategory("Category: "+model.getCategory());
                //  holder.setType("Type: "+model.getType());
                holder.setImage(getBaseContext(), model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //final String url = model.getUrl();
                        //Intent intent = new Intent(getApplicationContext(), NewsWebView.class);
                        //intent.putExtra("id", url);
                        //startActivity(intent);
                        String pos =  mPeopleRVAdapter.getRef(position).getKey();
                        //String va = String.valueOf(position);
                        //  Toast.makeText(ProductsActivity.this,pos,Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), userproductOne.class);
                        intent.putExtra("emailval", emailvv);
                        intent.putExtra("keyval",pos);
                        startActivity(intent);
                        finish();
                    }
                });
            }


        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        mPeopleRVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPeopleRVAdapter.stopListening();


    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        /*

        public void setDesc(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }

         */
        public void setPrice(String price) {
            TextView post_price = (TextView) mView.findViewById(R.id.post_price);
            post_price.setText(price);
        }

        public void setCategory(String category) {
            TextView post_cat = (TextView) mView.findViewById(R.id.post_cat);
            post_cat.setText(category);

        }
        /*
        public void setType(String type) {
            TextView post_type= (TextView) mView.findViewById(R.id.post_type);
            post_type.setText(type);
        }

         */

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_image);
        }
    }

    @Override
    public void onBackPressed() {

        Intent in = new Intent(searchProducts.this, Usermain.class);
        in.putExtra("emailval", emailvv);
        startActivity(in);
    }
}