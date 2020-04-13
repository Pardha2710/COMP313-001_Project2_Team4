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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class AproductsFragment extends Fragment {

    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Products, AproductsFragment.NewsViewHolder> mPeopleRVAdapter;
    String emailvv;

    public AproductsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.adminprodfrag, container, false);


        //"News" here will reflect what you have called your database in Firebase.
        mDatabase = FirebaseDatabase.getInstance().getReference().child("products");
        mDatabase.keepSynced(true);

        mPeopleRV = (RecyclerView) view.findViewById(R.id.myRecycleView);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("products");
        Query personsQuery = personsRef.orderByChild("product_email");

        // Log.e("HYDE", String.valueOf(personsQuery));

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Products> personsOptions = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(personsQuery, Products.class)
                .setLifecycleOwner(this)
                .build();

        Log.d("Options", " data : " + personsOptions);


        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Products, AproductsFragment.NewsViewHolder>(personsOptions) {


            @NonNull
            @Override
            public AproductsFragment.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prod_rows, parent, false);

                return new AproductsFragment.NewsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(AproductsFragment.NewsViewHolder holder, final int position, final Products model) {
                holder.setTitle("Name: " + model.getTitle());
                //  holder.setDesc("Company: "+model.getcomp());
                holder.setPrice("Price: " + model.getprice());
                holder.setCategory("Category: " + model.getCategory());
                //  holder.setType("Type: "+model.getType());
                holder.setImage(getContext(), model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //final String url = model.getUrl();
                        //Intent intent = new Intent(getApplicationContext(), NewsWebView.class);
                        //intent.putExtra("id", url);
                        //startActivity(intent);
                        String pos = mPeopleRVAdapter.getRef(position).getKey();
                        //String va = String.valueOf(position);
                        //  Toast.makeText(ProductsActivity.this,pos,Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getActivity(), AproductOne.class);
                        intent.putExtra("keyval", pos);
                        startActivity(intent);

                    }
                });
            }


        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);
        return view;
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


}
