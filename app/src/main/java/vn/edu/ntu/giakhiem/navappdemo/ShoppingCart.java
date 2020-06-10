package vn.edu.ntu.giakhiem.navappdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;

import vn.edu.ntu.giakhiem.controller.CartController;
import vn.edu.ntu.giakhiem.controller.ICartController;
import vn.edu.ntu.giakhiem.model.Product;

public class ShoppingCart extends Fragment {

    Button btnBuy, btnClear;
    TextView txtGioHang;
    ICartController cartController;
    NavController controller;
    ArrayList<Product> gioHang;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addViews(view);
        controller = NavHostFragment.findNavController(ShoppingCart.this);

        addEvents();
    }

    private void addViews(View view){
        btnBuy = view.findViewById(R.id.btnBuy);
        txtGioHang = view.findViewById(R.id.txtGioHang);
        btnClear = view.findViewById(R.id.btnClear);
        cartController = (CartController) getActivity().getApplication();
    }

    private void addEvents() {
        //txtGioHang
        gioHang = cartController.getGioHang();
        String s = "";

        for (int i =  0; i < gioHang.size(); i++){
            s += gioHang.get(i).getName() + "  Giá: " + gioHang.get(i).getPrice() + "\n";
        }
        if (s == "") s = "Bạn chưa thêm hàng vào giỏ hàng";
        txtGioHang.setText(s);

        //btnBuy
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_shoppingCart_to_confirmFragment);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartController.clearGioHang();
                txtGioHang.setText("Bạn chưa thêm hàng vào giỏ hàng");
            }
        });
    }
}
