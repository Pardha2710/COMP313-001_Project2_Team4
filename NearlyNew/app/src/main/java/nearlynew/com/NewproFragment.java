package nearlynew.com;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
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
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.app.Activity.RESULT_OK;

public class NewproFragment extends Fragment {

    private AppCompatEditText pname, price, compname;

    private AppCompatButton register;
    private Spinner sp1,sp2;
    String emailvv;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private String userId;

    double latitude, longitude;
    private TextView tvLocation;
    private Button btnGetLocation;
    private FusedLocationProviderClient locationProviderClient;
    private Geocoder geocoder;
    private List<Address> addresses;

    String city;

    String cate;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 22;

    FirebaseStorage storage;
    StorageReference storageReference;

    String[] cat = {"Select Category", "Shirts", "Dresses", "Jackets", "Jeans", "Trousers", "Skirts"};
    String[] typ ={"Select Type","Ladies","Gents","Kids"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.newprofrag, container, false);


        pname = view.findViewById(R.id.textPname);
        price = view.findViewById(R.id.textPrice);
        compname = view.findViewById(R.id.textComp);

        register = view.findViewById(R.id.appCompatRegister);

        sp1 = view.findViewById(R.id.spinner);
        sp2 = view.findViewById(R.id.spinner1);

        emailvv = getActivity().getIntent().getExtras().getString("emailval");

        requestPermission();

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        mFirebaseInstance = FirebaseDatabase.getInstance();

        locationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());


        geocoder = new Geocoder(getActivity(), Locale.getDefault());


        locationProviderClient.getLastLocation().addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();

                    try {

                        addresses = geocoder.getFromLocation(latitude, longitude, 1);


                        city = addresses.get(0).getLocality();
                        Log.e("city", city);


                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e("MainActivity", e.getMessage());
                    }

                }
            }
        });

        ArrayAdapter aa = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, cat);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp1.setAdapter(aa);

        ArrayAdapter ab = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, typ);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sp2.setAdapter(ab);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SelectImage();
            }
        });



        return view;
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
                                String product_category = sp2.getSelectedItem().toString();
                                String product_img1 = URL;
                                String product_location=city;

                                mFirebaseDatabase = mFirebaseInstance.getReference("products");

                                if (TextUtils.isEmpty(userId)) {
                                    userId = mFirebaseDatabase.push().getKey();
                                }

                                ImageUpload imageUpload= new ImageUpload(product_name,product_email,
                                        product_price,product_category,product_type,product_comp,product_img1,product_location);

                                mFirebaseDatabase.child(userId).setValue(imageUpload);

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

                    Toast.makeText(getActivity(),"Product Upload Failed",Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(getActivity(),"Product Uploaded Sucesfully.",Toast.LENGTH_LONG).show();

               Intent in = new Intent(getActivity(),Sellermain.class);
               in.putExtra("emailval",emailvv);
               startActivity(in);


            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.e("Retived Data", "Failed to read user", error.toException());
            }
        });
    }


    private void requestPermission() {
        ActivityCompat.requestPermissions(getActivity(), new String[]{ACCESS_FINE_LOCATION}, 1);
    }


}
