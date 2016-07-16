package com.phillo.produtos.controller;

import com.google.gson.Gson;
import com.phillo.produtos.bo.Product;
import com.phillo.produtos.model.ProductModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
            @RequestParam(value = "description", required = false) String description
    ){
        ProductModel model = new ProductModel();
        model.insert(name, description);

        ModelAndView Return = new ModelAndView("returnFile", "Return", "Ok");
        return Return;
    }

    @RequestMapping(value = "/teste")
    public String teste()
    {
        return "teste";
    }
}
