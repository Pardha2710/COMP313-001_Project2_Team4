package nearlynew.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment {

    private TextView name,email,phone, et;
    private String emailval,n1,e1,p1,i1;

    private Uri filePath;
    private String userId;

    private de.hdodenhof.circleimageview.CircleImageView iv;

    private final int PICK_IMAGE_REQUEST = 22;

    private FirebaseStorage storage;
    private StorageReference storageReference;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.profilefrag, container, false);

        name = rootView.findViewById(R.id.name);
        email = rootView.findViewById(R.id.email);
        phone = rootView.findViewById(R.id.phone);
        et = rootView.findViewById(R.id.addImage);
        iv = rootView.findViewById(R.id.imageView4);

        emailval = getActivity().getIntent().getExtras().getString("emailval");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("sellers");


        reference.orderByChild("email").equalTo(emailval).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {
                    Log.d("info", zoneSnapshot.child("name").getValue(String.class));

                    n1 = zoneSnapshot.child("name").getValue(String.class);
                    e1 = zoneSnapshot.child("email").getValue(String.class);
                    p1 = zoneSnapshot.child("phone").getValue(String.class);

                }

                name.setText(n1);
                email.setText(e1);
                phone.setText(p1);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Not Able To Login", Toast.LENGTH_LONG).show();
            }
        });

        DatabaseReference refere = FirebaseDatabase.getInstance().getReference("sellersimg");

        refere.orderByChild("email").equalTo(emailval).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot zoneSnapshot: dataSnapshot.getChildren()) {

                    i1 = zoneSnapshot.child("profileimg").getValue(String.class);

                }

                if(i1 != null){
                    Picasso.get().load(i1).into(iv);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(getActivity(), "Not Able To Login", Toast.LENGTH_LONG).show();
            }
        });


        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelectImage();

            }
        });


        return rootView;
    }

    private void SelectImage()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            filePath = data.getData();
            uploadImage();
        }
    }

    private void uploadImage()
    {
        if (filePath != null) {

            final ProgressDialog progressDialog
                    = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref  = storageReference.child("sellerprofile/"+ UUID.randomUUID().toString());

            ref.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.dismiss();
                                String URL = uri.toString();


                                final String profileimg = URL;
                                final String email = emailval;

                                mFirebaseInstance = FirebaseDatabase.getInstance();

                                mFirebaseDatabase = mFirebaseInstance.getReference("sellersimg");

                                if (TextUtils.isEmpty(userId)) {
                                    userId = mFirebaseDatabase.push().getKey();
                                }

                                ProfileImageUpload profileImageUpload= new ProfileImageUpload(email,profileimg);

                                mFirebaseDatabase.child(userId).setValue(profileImageUpload);

                                addUserChangeListener();


                            }
                        });
                    }
                }

            });
        }
    }

    private void addUserChangeListener() {

        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user == null) {

                    Toast.makeText(getActivity(),"Image Upload Failed",Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getActivity(),"Image Uploaded Sucesfully.",Toast.LENGTH_LONG).show();

                Intent in = new Intent(getActivity(),Sellermain.class);
                in.putExtra("emailval",emailval);
                startActivity(in);

            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.e("Retived Data", "Failed to read user", error.toException());
            }
        });
    }




}
