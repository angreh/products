package com.phillo.produtos.model;

import com.phillo.produtos.bo.Product;
import com.phillo.produtos.dao.GenericDao;

import java.util.List;

/**
 * Created by Angreh (angreh@gmail.com) on 7/16/2016.
 */
public class ProductModel
{
    public List getAll()
    {
        GenericDao dao = new GenericDao(Product.class);

        return dao.getAll();
    }

    public boolean insert(
            Integer domainID,
            String name,
            String description,
            Float price,
            Float sellPrice)
    {
        GenericDao dao = new GenericDao(Product.class);

        Product product = new Product();
        product.domainID = domainID;
        product.name = name;
        product.description = description;
        product.price = price;
        product.sellPrice = sellPrice;

        return dao.save(product);
    }
}
