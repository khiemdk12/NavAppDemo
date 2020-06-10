package vn.edu.ntu.giakhiem.navappdemo;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import vn.edu.ntu.giakhiem.controller.CartController;
import vn.edu.ntu.giakhiem.controller.ICartController;
import vn.edu.ntu.giakhiem.model.Product;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConfirmFragment extends Fragment {

    NavController controller;
    TextView txtConfirm;
    ICartController cartController;
    ArrayList<Product> gioHang;
    Button btnConfirm;

    public ConfirmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_confirm, container, false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(ConfirmFragment.this);
        ((MainActivity)getActivity()).controller = controller;
        txtConfirm = view.findViewById(R.id.txtConfirm);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        cartController = (CartController) getActivity().getApplication();
        gioHang = cartController.getGioHang();

        if (gioHang.isEmpty()){
            txtConfirm.setText("Bạn chưa chọn mua mặt hàng nào!");
        }
        else {
            txtConfirm.setText("Cảm ơn bạn đã mua hàng");
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigateUp();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cartController.clearGioHang();
    }
}
