package com.phillo.produtos.model;

import com.j256.ormlite.dao.Dao;
import com.phillo.produtos.bo.GenericBO;
import com.phillo.produtos.bo.Product;
import com.phillo.produtos.dao.GenericDao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by angre on 7/31/2016.
 */
public class GenericModel
{
    protected Class file;
    protected GenericDao dao;

    protected GenericModel(Class gFile)
    {
        file = gFile;
        dao = new GenericDao(gFile);
    }

    /**
     * Pega apenas um registro no banco de dados
     *
     * @param ID
     * @return
     */
    public Object getOne(Integer ID)
    {
        return dao.getOne(ID);
    }

    /**
     * Pega TODOS os registros no BD
     *
     * @return
     */
    public List getAll()
    {
        return dao.getAll();
    }

    /**
     * Cria um novo registro no BD
     *
     * @param fields
     * @return
     */
    public Integer insert(Map fields)
    {
        GenericDao dao = new GenericDao(Product.class);

        try
        {
            Constructor constructor = file.getConstructor();
            GenericBO object = (GenericBO) constructor.newInstance();

            setAttributes( object, fields );
            return dao.save(object);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * Faz o update do registro já existente
     *
     * @param ID
     * @param fields
     * @return
     */
    public Integer edit(
            Integer ID,
            Map fields
    ){
        Object object = dao.getOne(ID);

        setAttributes( object, fields );

        return dao.edit( object );
    }

    /**
     * Pega o 'object' e define seus atributos de acordo com os valores existentes em 'map'
     *
     * @param object
     * @param map
     */
    public static void setAttributes(Object object, Map map)
    {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry pair = (Map.Entry) iterator.next();

            try
            {
                Field field = object.getClass().getDeclaredField( (String) pair.getKey() );
                field.set( object, pair.getValue() );
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

    }
}
