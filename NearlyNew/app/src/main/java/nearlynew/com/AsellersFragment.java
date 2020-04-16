package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AsellersFragment extends Fragment {

    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Sellerslist, AsellersFragment.NewsViewHolder> mPeopleRVAdapter;

    public AsellersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sellersfrag, container, false);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("sellers");
        mDatabase.keepSynced(true);

        mPeopleRV = (RecyclerView) view.findViewById(R.id.myRecycleView);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("sellers");
        Query personsQuery = personsRef.orderByChild("email");


        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Sellerslist> personsOptions = new FirebaseRecyclerOptions.Builder<Sellerslist>()
                .setQuery(personsQuery, Sellerslist.class)
                .setLifecycleOwner(this)
                .build();

        Log.e("Options", " data : " + personsOptions);


        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Sellerslist, AsellersFragment.NewsViewHolder>(personsOptions) {


            @NonNull
            @Override
            public AsellersFragment.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chat_rows, parent, false);

                return new AsellersFragment.NewsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(AsellersFragment.NewsViewHolder holder, final int position, final Sellerslist model) {
                holder.setTitle(model.getTitle());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String pos =  mPeopleRVAdapter.getRef(position).getKey();
                        String vall = model.getTitle();
                        Intent intent = new Intent(getActivity(), User_single.class);
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

    }
}
