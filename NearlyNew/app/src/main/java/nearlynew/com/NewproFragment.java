package nearlynew.com;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class NewproFragment extends Fragment {

    private AppCompatEditText pname, price,compname;
    private RadioGroup gender,role;
    private RadioButton radbut,radrole;
    private AppCompatButton register;
    private Spinner sp1;
    String emailvv;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    // Uri indicates, where the image will be picked from
    private Uri filePath;

    // request code
    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;

    String[] cat = {"Select Category","Shirts","Dresses","Jackets","Jeans","Trousers","Skirts"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.newprofrag, container, false);

        pname = view.findViewById(R.id.textPname);
        price = view.findViewById(R.id.textPrice);
        compname = view.findViewById(R.id.textComp);
        gender = view.findViewById(R.id.radioSex);
        register = view.findViewById(R.id.appCompatRegister);

        sp1 = view.findViewById(R.id.spinner);

        emailvv = getActivity().getIntent().getExtras().getString("emailval");

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mFirebaseInstance = FirebaseDatabase.getInstance();

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,cat);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sp1.setAdapter(aa);


        int selectedId = gender.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        radbut = view.findViewById(selectedId);





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelectImage();
            }
        });



        return view;
    }

    // Select Image method
    private void SelectImage()
    {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    // Override onActivityResult method
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data)
    {

        super.onActivityResult(requestCode, resultCode, data);

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            uploadImage();
        }
    }

    // UploadImage method
    private void uploadImage()
    {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            final ProgressDialog progressDialog
                    = new ProgressDialog(getActivity());
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            // Defining the child of storageReference
            final StorageReference ref  = storageReference.child("images/"+ UUID.randomUUID().toString());

            ref.putFile(filePath).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                progressDialog.dismiss();
                                String URL = uri.toString();

                                String product_name = pname.getText().toString();
                                String product_price = price.getText().toString();
                                String product_comp = compname.getText().toString();
                                String product_type = sp1.getSelectedItem().toString();
                                String product_email = emailvv;
                                String product_img1 = URL;



                                mFirebaseDatabase = mFirebaseInstance.getReference("products");



                                // find the radiobutton by returned id

                                String product_category = radbut.getText().toString().trim();

                                if (TextUtils.isEmpty(userId)) {
                                    userId = mFirebaseDatabase.push().getKey();
                                }

                                ImageUpload imageUpload= new ImageUpload(product_name,product_email,
                                        product_price,product_category,product_type,product_comp,product_img1);

                                mFirebaseDatabase.child(userId).setValue(imageUpload);

                                addUserChangeListener();

                                //Toast.makeText(getActivity(), URL, Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }

            });
        }
    }

    /**
     * User data change listener
     */
    private void addUserChangeListener() {
        // User data change listener
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                // Check for null
                if (user == null) {
                    Log.e("Testing11", "User data is null!");
                    Toast.makeText(getActivity(),"Registration Failed",Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getActivity(),"Registration Success. Please Login",Toast.LENGTH_LONG).show();

               Intent in = new Intent(getActivity(),Sellermain.class);
               in.putExtra("emailval",emailvv);
               startActivity(in);

                Log.e("Testing22", "User data is changed!" + user.name + ", " + user.email);

                // Display newly updated name and email
                //txtDetails.setText(user.name + ", " + user.email);

                // clear edit text
                // inputEmail.setText("");
                //inputName.setText("");

                //toggleButton();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.e("Retived Data", "Failed to read user", error.toException());
            }
        });
    }




}
