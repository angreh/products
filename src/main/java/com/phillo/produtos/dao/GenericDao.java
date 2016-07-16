package com.phillo.produtos.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.phillo.produtos.config.DBConfig;

import java.util.List;

/**
 * Created by angre on 7/16/2016.
 */
public class GenericDao
{
    private Class file;

    public GenericDao(Class gFile)
    {
        file = gFile;
    }

    public void save(Object object)
    {
        ConnectionSource conn = conn();
        try
        {
            Dao dao = DaoManager.createDao( conn, file);
            dao.create(object);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public List getAll()
    {
        ConnectionSource conn = conn();
        try
        {
            //cria DAO
            Dao dao = DaoManager.createDao(conn, file);

            //função que pega todos os itens
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
