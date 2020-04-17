package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class chat_single extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    private RecyclerView mPeopleRV;
    private DatabaseReference mDatabase;
    private FirebaseRecyclerAdapter<Chatmessage, chat_single.NewsViewHolder> mPeopleRVAdapter;

    String emailvv,sellermail;
    Button btn;
    EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_single);

        emailvv = getIntent().getExtras().getString("emailval");
        sellermail = getIntent().getExtras().getString("sellermail");
        btn = findViewById(R.id.btSent);
        et = findViewById(R.id.etText);

        mPeopleRV = (RecyclerView) findViewById(R.id.rvChat);

        mFirebaseInstance = FirebaseDatabase.getInstance();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String chat = et.getText().toString();


                mFirebaseDatabase = mFirebaseInstance.getReference("chat_messg");
                if (TextUtils.isEmpty(userId)) {
                    userId = mFirebaseDatabase.push().getKey();
                }

                String chat_recev = sellermail;
                String chat_sender = emailvv;
                String chat_emailfull = emailvv+sellermail;
                String chat_sender_msg = chat;

                Chatmessage chatmessage = new Chatmessage(chat_emailfull,chat_recev,chat_sender,chat_sender_msg);

                mFirebaseDatabase.child(userId).setValue(chatmessage);

                addUserChangeListener();



            }
        });


        String fullemail = emailvv+sellermail;

        DatabaseReference personsRef = FirebaseDatabase.getInstance().getReference("chat_messg");
        Query personsQuery = personsRef.orderByChild("chat_emailfull").equalTo(fullemail);


        mPeopleRV.hasFixedSize();
        mPeopleRV.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Chatmessage> personsOptions = new FirebaseRecyclerOptions.Builder<Chatmessage>()
                .setQuery(personsQuery, Chatmessage.class)
                .setLifecycleOwner(this)
                .build();

        Log.e("Options", " data : " + personsOptions);


        mPeopleRVAdapter = new FirebaseRecyclerAdapter<Chatmessage, chat_single.NewsViewHolder>(personsOptions) {


            @NonNull
            @Override
            public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chat_rows_single, parent, false);

                return new NewsViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(chat_single.NewsViewHolder holder, final int position, final Chatmessage model) {
                holder.setTitle(model.getSender());
                holder.setMsg(model.getMsg());


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

        public void setMsg(String msg) {
            TextView post_msg = (TextView) mView.findViewById(R.id.post_msg);
            post_msg.setText(msg);
        }

    }


    private void addUserChangeListener() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);


                if (user == null) {
                    Log.e("Testing11", "User data is null!");
                    Toast.makeText(chat_single.this,"Wishlist NOt added",Toast.LENGTH_LONG).show();
                    return;
                }


                Intent in = new Intent(chat_single.this,chat_single.class);
                in.putExtra("emailval",emailvv);
                in.putExtra("sellermail",sellermail);
                startActivity(in);

                Log.e("Testing22", "User data is changed!" + user.name + ", " + user.email);

            }
            @Override
            public void onCancelled(DatabaseError error) {

                Log.e("Retived Data", "Failed to read user", error.toException());
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent in = new Intent(chat_single.this,Usermain.class);
        in.putExtra("emailval",emailvv);
        startActivity(in);
    }

}

