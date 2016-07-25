package com.phillo.produtos.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.phillo.produtos.config.DBConfig;

import java.util.List;

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

    public boolean save(Object object)
    {
        ConnectionSource conn = conn();
        try
        {
            Dao dao = DaoManager.createDao( conn, file);
            Integer result = dao.create(object);

            return (result==1);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
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
