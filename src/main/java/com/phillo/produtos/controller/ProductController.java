package com.phillo.produtos.controller;

import com.google.gson.Gson;
import com.phillo.produtos.bo.Product;
import com.phillo.produtos.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Angreh (angreh@gmail.com) on 7/16/2016.
 */

@Controller
public class ProductController
{
    @RequestMapping(value = "/getall")
    public ModelAndView getAll()
    {
        ProductModel model = new ProductModel();
        List<Product> products = model.getAll();

        Gson gson = new Gson();
        String returnJson = gson.toJson(products);

        ModelAndView Return = new ModelAndView("returnFile", "Return", returnJson);
        return Return;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ModelAndView insert(
            @RequestParam("name") String name,
            @RequestParam("domainID") Integer domainID,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "price", required = false) Float price,
            @RequestParam(value = "sellPrice", required = false) Float sellPrice
    ){
        Map fields = new HashMap();
        fields.put("name", name);
        fields.put("domainID", domainID);
        fields.put("description", description);
        fields.put("price", price);
        fields.put("sellPrice", sellPrice);

        ProductModel model = new ProductModel();

        Integer response = model.insert(fields);

        ModelAndView Return = new ModelAndView("returnFile", "Return", response);
        return Return;
    }

    @RequestMapping(value = "/edit/image", method = RequestMethod.POST)
    public ModelAndView edit(
            @RequestParam("ID") Integer ID,
            @RequestParam("img") String img
    ){
        Map fields = new HashMap();
        fields.put("img", img);

        ProductModel model = new ProductModel();

        Integer response = model.edit(ID, fields);

        ModelAndView Return = new ModelAndView("returnFile", "Return", response);
        return Return;
    }

    @RequestMapping(value = "/teste")
    public String teste()
    {
        return "teste";
    }
}
