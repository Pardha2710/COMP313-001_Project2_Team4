package nearlynew.com;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsFragment extends Fragment {

    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Products, ProductsViewHolder> mPeopleRVAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.productsfrag, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference("products");
        mDatabase.keepSynced(true);

        mPeopleRV = rootView.findViewById(R.id.myRecycleView);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("products");
        Query personsQuery = personsRef.orderByKey();

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerOptions personsOptions = new FirebaseRecyclerOptions.Builder<Products>().setQuery(personsQuery, Products.class).build();

        Log.d("Options"," data : "+personsOptions);

        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Products, ProductsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(ProductsViewHolder holder, final int position, final Products model) {
                holder.setTitle(model.getTitle());

                holder.setPrice(model.getprice());
                holder.setImage(getContext(), model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }

            @Override
            public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.prod_rows, parent, false);

                return new ProductsViewHolder(view);
            }
        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);
        mPeopleRVAdapter.startListening();
        return rootView;
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

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public ProductsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setPrice(String price){
            TextView post_price = (TextView)mView.findViewById(R.id.post_price);
            post_price.setText(price);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.post_image);
            Picasso.get().load(image).into(post_image);

        }
    }


}
