package nearlynew.com;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class ChatFragment extends Fragment {

    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Chatslistseller, ChatFragment.NewsViewHolder> mPeopleRVAdapter;
    String emailvv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.chatfrag, container, false);


        //getActionBar().setDisplayHomeAsUpEnabled(true);
        emailvv = getActivity().getIntent().getExtras().getString("emailval");

        //"News" here will reflect what you have called your database in Firebase.
        mDatabase = FirebaseDatabase.getInstance().getReference().child("chat_users");
        mDatabase.keepSynced(true);

        mPeopleRV = (RecyclerView) rootView.findViewById(R.id.myRecycleView);

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("chat_users");
        Query personsQuery = personsRef.orderByChild("selleremail").equalTo(emailvv);

        // Log.e("HYDE", String.valueOf(personsQuery));

        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions<Chatslistseller> personsOptions = new FirebaseRecyclerOptions.Builder<Chatslistseller>()
                .setQuery(personsQuery, Chatslistseller.class)
                .setLifecycleOwner(this)
                .build();

        Log.e("Options", " data : " + personsOptions);


        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Chatslistseller, ChatFragment.NewsViewHolder>(personsOptions) {


            @NonNull
            @Override
            public ChatFragment.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chat_rows, parent, false);

                return new ChatFragment.NewsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(ChatFragment.NewsViewHolder holder, final int position, final Chatslistseller model) {
                holder.setTitle(model.getTitle());

                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String vall = model.getTitle();
                        Intent intent = new Intent(getActivity(), chat_single_seller.class);
                        intent.putExtra("emailval", emailvv);
                        intent.putExtra("buyermail", vall);
                        startActivity(intent);



                        //Toast.makeText(ChatFragment.this,vall,Toast.LENGTH_LONG).show();
                    }
                });
            }


        };

        mPeopleRV.setAdapter(mPeopleRVAdapter);
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
