package vn.edu.ntu.giakhiem.navappdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

import vn.edu.ntu.giakhiem.controller.ICartController;
import vn.edu.ntu.giakhiem.model.Product;

public class DSMatHangFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton fab;
    RecyclerView rvListProduct;
    NavController controller;
    TextView txtCount;
    ImageView imvCart;
    ICartController cartController;
    ArrayList<Product> gioHang;
    ProductAdapter productAdapter;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ds_mat_hang, container, false);
        fab = view.findViewById(R.id.floatingActionButton);
        rvListProduct = view.findViewById(R.id.rvListProduct);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.mnu_cart, menu);

        MenuItem menuItem = menu.findItem(R.id.mnu_cart);
        txtCount = menuItem.getActionView().findViewById(R.id.txtCount);
        imvCart = menuItem.getActionView().findViewById(R.id.imvCart);
        txtCount.setOnClickListener(this);
        txtCount.setText(cartController.getCountGioHang());
        imvCart.setOnClickListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        controller = NavHostFragment.findNavController(DSMatHangFragment.this);
        //tại sao lại có dòng này
        ((MainActivity)getActivity()).controller = controller;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.navigate(R.id.action_DSMatHang_to_addProductFragment);
            }
        });

        addViews();
    }

    private void addViews() {
        rvListProduct = getView().findViewById(R.id.rvListProduct);
        cartController = (ICartController) getActivity().getApplication();
        productAdapter = new ProductAdapter(cartController.getListProduct());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvListProduct.setAdapter(productAdapter);
        rvListProduct.setLayoutManager(linearLayoutManager);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        controller.navigate(R.id.action_DSMatHang_to_shoppingCart);
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtName, txtPrice, txtDesc;
        ImageButton imBtnCart;
        Product p;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = this.itemView.findViewById(R.id.txtName);
            txtPrice = this.itemView.findViewById(R.id.txtPrice);
            txtDesc = this.itemView.findViewById(R.id.txtDes);
            imBtnCart = this.itemView.findViewById(R.id.imBtnCart);
            imBtnCart.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (cartController.addToGioHang(p)){
//                int i = cartController.getGioHang().size();
//                txtCount.setText(new Integer(i).toString());
                txtCount.setText(cartController.getCountGioHang());
                Toast.makeText(getActivity(), "Bạn đưa sản phẩm " + txtName.getText().toString() + " vào giỏ hàng",Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(getActivity(), "Sản phẩm " + txtName.getText().toString() + " đã có trong giỏ hàng",Toast.LENGTH_SHORT).show();
        }

        public void bind(Product p) {
            this.p = p;
            txtName.setText(p.getName());
            txtPrice.setText(new Integer(p.getPrice()).toString());
            txtDesc.setText(p.getDesc());
            imBtnCart.setImageResource(R.drawable.ic_action_add_to_cart);
        }
    }

    //Lớp adapter kết nối RecycleView và dữ liệu
    private class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
        ArrayList<Product> listProduct;

        public ProductAdapter(ArrayList<Product> listProduct) {
            this.listProduct = listProduct;
        }

        //tạo một ViewHolder để hiển thị dữ liệu
        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            //chuyển layout đã thiết kế bằng xml thành một đối tượng View

            View view = layoutInflater.inflate(R.layout.product_item, parent, false);
            return new ProductViewHolder(view);
        }

        //Kết nối một mục dữ liệu trong danh sách với một ViewHolder
        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            Product product = listProduct.get(position);
            holder.bind(product);
        }

        @Override
        public int getItemCount() {
            return listProduct.size();
        }
    }
}
