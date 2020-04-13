package nearlynew.com;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class UserHomeFragment extends Fragment {


    private ImageView iv1,iv2,iv3,iv4,iv5,iv6;
    String emailvv;
    private EditText et;
    private Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.userhomefrag, container, false);

        iv1 = rootView.findViewById(R.id.ivdress);
        iv2 = rootView.findViewById(R.id.ivjackets);
        iv3 = rootView.findViewById(R.id.ivjeans);
        iv4 = rootView.findViewById(R.id.ivshirts);
        iv5 = rootView.findViewById(R.id.ivskirts);
        iv6 = rootView.findViewById(R.id.ivtrousers);
        et = rootView.findViewById(R.id.setext);
        btn = rootView.findViewById(R.id.search);

        emailvv = getActivity().getIntent().getExtras().getString("emailval");

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(getActivity(),UserProducts.class);
                ii.putExtra("emailval",emailvv);
                ii.putExtra("pcat","Dresses");
                startActivity(ii);

            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(getActivity(),UserProducts.class);
                ii.putExtra("emailval",emailvv);
                ii.putExtra("pcat","Jackets");
                startActivity(ii);

            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getActivity(),UserProducts.class);
                ii.putExtra("emailval",emailvv);
                ii.putExtra("pcat","Jeans");
                startActivity(ii);

            }
        });


        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(getActivity(),UserProducts.class);
                ii.putExtra("emailval",emailvv);
                ii.putExtra("pcat","Shirts");
                startActivity(ii);

            }
        });

        iv5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(getActivity(),UserProducts.class);
                ii.putExtra("emailval",emailvv);
                ii.putExtra("pcat","Skirts");
                startActivity(ii);

            }
        });


        iv6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ii = new Intent(getActivity(),UserProducts.class);
                ii.putExtra("emailval",emailvv);
                ii.putExtra("pcat","Trousers");
                startActivity(ii);

            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sval = et.getText().toString();
                Intent ii = new Intent(getActivity(),searchProducts.class);
                ii.putExtra("emailval",emailvv);
                ii.putExtra("sval",sval);
                startActivity(ii);

            }
        });


        return rootView;
    }
}
