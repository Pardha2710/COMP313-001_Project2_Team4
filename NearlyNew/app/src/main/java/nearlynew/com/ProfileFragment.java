package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    TextView name,email,phone,gender;
    String emailval,n1,e1,p1,g1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.profilefrag, container, false);

        name = rootView.findViewById(R.id.name);
        email = rootView.findViewById(R.id.email);
        phone = rootView.findViewById(R.id.phone);
        gender = rootView.findViewById(R.id.gender);

        emailval = getActivity().getIntent().getExtras().getString("emailval");



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sellers");


        reference.orderByChild("email").equalTo(emailval).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {
                    Log.d("info", zoneSnapshot.child("name").getValue(String.class));

                    n1 = zoneSnapshot.child("name").getValue(String.class);
                    e1 = zoneSnapshot.child("email").getValue(String.class);
                    p1 = zoneSnapshot.child("phone").getValue(String.class);
                    g1 = zoneSnapshot.child("gender").getValue(String.class);

                }

                name.setText("Full Name: "+n1);
                email.setText("Email : "+e1);
                phone.setText("Phone : "+p1);
                gender.setText("Gender : "+g1);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Not Able To Login", Toast.LENGTH_LONG).show();
            }
        });


        return rootView;
    }
}
