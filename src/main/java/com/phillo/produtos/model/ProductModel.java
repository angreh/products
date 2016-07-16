package com.phillo.produtos.model;

import com.phillo.produtos.bo.Product;
import com.phillo.produtos.dao.GenericDao;

import java.util.List;

/**
 * Created by angre on 7/16/2016.
 */
public class ProductModel
{
    public List getAll()
    {
        GenericDao dao = new GenericDao(Product.class);

        return dao.getAll();
    }

    public boolean insert(String name, String description)
    {
        GenericDao dao = new GenericDao(Product.class);

        Product product = new Product();
        product.name = name;
        product.description = description;

        dao.save(product);

        return true;
    }
}
