package vn.edu.ntu.giakhiem.navappdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import vn.edu.ntu.giakhiem.controller.ICartController;
import vn.edu.ntu.giakhiem.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {

    NavController controller;
    EditText edtAddName, edtAddPrice, edtAddInfor;
    Button btnAddProduct, btnCloseProduct;

    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_product, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addViews(view);
        addEvents();
    }

    private void addViews(View view) {
        edtAddName = view.findViewById(R.id.edtAddName);
        edtAddPrice = view.findViewById(R.id.edtAddPrice);
        edtAddInfor = view.findViewById(R.id.edtAddInfor);

        btnAddProduct = view.findViewById(R.id.btnAddProduct);
        btnCloseProduct = view.findViewById(R.id.btnCloseProduct);
    }

    private void addEvents() {
        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtAddName.getText().toString();
                int price = edtAddPrice.getText().toString().matches("") ? 0 : Integer.parseInt(edtAddPrice.getText().toString());
                String infor = edtAddInfor.getText().toString();

                Product p = new Product(name, price, infor );
                ICartController controller = (ICartController)getActivity().getApplication();
                controller.addToListProduct(p);

                Toast.makeText(getContext(),"Đã thêm mặt hàng" + name, Toast.LENGTH_SHORT).show();

                edtAddName.setText("");
                edtAddPrice.setText("");
                edtAddInfor.setText("");
            }
        });

        btnCloseProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigateUp();
            }
        });
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        controller = NavHostFragment.findNavController(AddProductFragment.this);
        ((MainActivity)getActivity()).controller = controller;
    }
}
