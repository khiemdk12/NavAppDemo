package vn.edu.ntu.giakhiem.controller;

import android.app.Application;

import java.util.ArrayList;

import vn.edu.ntu.giakhiem.model.Product;

public class CartController extends Application implements ICartController {
    ArrayList<Product> listProduct = new ArrayList<>();
    ArrayList<Product> gioHang = new ArrayList<>();

    public CartController() {
        listProduct.add(new Product("Xoài cát", 60000, "Xoài cát Hòa lộc loại 1"));
        listProduct.add(new Product("Khoai lang", 45000, "Khoai lang tím giống Nhật"));
        listProduct.add(new Product("Me thái", 65000, "Me Thái lan nhập khẩu"));
        listProduct.add(new Product("Ổi", 60000, "Ổi chua"));
        listProduct.add(new Product("Mận tím", 50000, "Mận tím tây bắc"));
        listProduct.add(new Product("Táo đỏ", 60000, "Táo đỏ Mỹ"));
        listProduct.add(new Product("Sầu riêng Khánh Sơn", 90000, "Sầu riêng khánh sơn loại 1"));
    }

    @Override
    public ArrayList<Product> getListProduct() {
        return listProduct;
    }

    @Override
    public boolean addToGioHang(Product p) {
        if (gioHang.contains(p)) return false;
        else {
            gioHang.add(p);
            return true;
        }
    }

    @Override
    public ArrayList<Product> getGioHang() {
        return gioHang;
    }

    @Override
    public void clearGioHang() {
        gioHang.clear();
    }

    @Override
    public String getCountGioHang() {
        return gioHang.size() == 0 ? "" : new Integer(gioHang.size()).toString();
    }

    @Override
    public void addToListProduct(Product product) {
        listProduct.add(product);
    }
}
