package vn.edu.ntu.giakhiem.controller;

import java.util.ArrayList;

import vn.edu.ntu.giakhiem.model.Product;

public interface ICartController {
    public ArrayList<Product> getListProduct();
    public boolean addToGioHang(Product p);
    public ArrayList<Product> getGioHang();
    public void clearGioHang();
    public String getCountGioHang();
    public  void addToListProduct(Product product);
}
