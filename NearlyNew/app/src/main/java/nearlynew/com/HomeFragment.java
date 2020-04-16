package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class HomeFragment extends Fragment {

    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Products, ProductsActivity.NewsViewHolder> mPeopleRVAdapter;
    String emailvv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.homefrag, container, false);

        emailvv = getActivity().getIntent().getExtras().getString("emailval");

        mDatabase = FirebaseDatabase.getInstance().getReference().child("products");
        mDatabase.keepSynced(true);

        mPeopleRV = (RecyclerView) rootView.findViewById(R.id.myRecycleView);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("products");
        Query personsQuery = personsRef.orderByChild("product_email").equalTo(emailvv);

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Products> personsOptions = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(personsQuery, Products.class)
                .setLifecycleOwner(this)
                .build();

        Log.d("Options"," data : "+personsOptions);

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

                holder.setPrice("Price: "+model.getprice());
                holder.setCategory("Category: "+model.getCategory());

                holder.setImage(getActivity().getBaseContext(), model.getImage());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String pos =  mPeopleRVAdapter.getRef(position).getKey();

                        Intent intent = new Intent(getContext(), productOne.class);
                        intent.putExtra("emailval", emailvv);
                        intent.putExtra("keyval",pos);
                        startActivity(intent);

                    }
                });
            }


        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);

        return rootView;
    }
}
