package nearlynew.com;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.productsfrag, container, false);

        recyclerView = rootView.findViewById(R.id.list);


        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        fetch();

        return rootView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout root;
        public TextView txtTitle;
        public TextView txtDesc,textPrice;
        public ImageView imgv;

        public ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_main);
            txtTitle = itemView.findViewById(R.id.list_title);
            txtDesc = itemView.findViewById(R.id.list_desc);
            textPrice = itemView.findViewById(R.id.textPrice);
            imgv = itemView.findViewById(R.id.imageView2);
        }

        public void setTxtTitle(String string) {
            txtTitle.setText(string);
        }


        public void setTxtDesc(String string) {
            txtDesc.setText(string);
        }
        public void setTxtPrice(String string) {
            textPrice.setText(string);
        }

        public void setImage(String string) throws IOException {
            URL newurl = new URL(string);
            Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
            imgv.setImageBitmap(mIcon_val);
        }
    }

    private void fetch() {
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("posts");

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>()
                        .setQuery(query, new SnapshotParser<Product>() {
                            @NonNull
                            @Override

                            public Product parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new Product(snapshot.child("product_name").getValue().toString(),
                                        snapshot.child("product_category").getValue().toString(),
                                        snapshot.child("product_comp").getValue().toString(),
                                        snapshot.child("product_price").getValue().toString(),
                                        snapshot.child("product_type").getValue().toString(),
                                        snapshot.child("product_img1").getValue().toString()
                                );
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<Product, ViewHolder>(options) {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item, parent, false);

                return new ViewHolder(view);
            }


            @Override
            protected void onBindViewHolder(ViewHolder holder, final int position, Product product) {
                holder.setTxtTitle(product.getPname());
                holder.setTxtDesc(product.getType());
                holder.setTxtPrice(product.getPrice());
                try {
                    holder.setImage(product.getImglnk());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                holder.root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
