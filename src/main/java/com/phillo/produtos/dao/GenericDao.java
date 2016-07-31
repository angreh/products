package com.phillo.produtos.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.phillo.produtos.bo.GenericBO;
import com.phillo.produtos.config.DBConfig;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Angreh (angreh@gmail.com) on 7/16/2016.
 */
public class GenericDao
{
    private Class file;

    public GenericDao(Class gFile)
    {
        file = gFile;
    }

    public Integer save(GenericBO object)
    {
        ConnectionSource conn = conn();
        try
        {
            Dao dao = DaoManager.createDao( conn, file);
            Integer result = dao.create(object);

            Integer boID;
            if ( result==1 )
            {
                 boID = object.getID();
            }
            else
            {
                boID = 0;
            }

            return boID;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public Integer edit(Object object)
    {
        ConnectionSource conn = conn();
        try
        {
            Dao dao = DaoManager.createDao( conn, file );

            return dao.update(object);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    public Object getOne(Integer ID)
    {
        ConnectionSource conn = conn();
        try
        {
            Dao dao = DaoManager.createDao(conn, file);
            Object object = dao.queryForId(ID);

            conn.close();

            return object;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public List getAll()
    {
        ConnectionSource conn = conn();
        try
        {
            Dao dao = DaoManager.createDao(conn, file);

            List genericList = dao.queryForAll();

            conn.close();

            return genericList;

        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    private ConnectionSource conn()
    {
        DBConfig config = new DBConfig();
        try {
            ConnectionSource conn = new JdbcConnectionSource(
                    config.ConnectionURL,
                    config.ConnectionUser,
                    config.ConnectionPassword
            );
            return conn;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
