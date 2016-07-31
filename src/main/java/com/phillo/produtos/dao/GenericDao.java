package com.phillo.produtos.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.phillo.produtos.bo.GenericBO;
import com.phillo.produtos.config.DBConfig;

import java.util.List;

/**
 * Created by Angreh (angreh@gmail.com) on 7/16/2016.
 */
public class GenericDao
{
    /**
     * Classe do objeto
     */
    private Class file;

    /**
     * É o Dao padrão do ORMLite, responsável por executar todas as chamadas ao banco de dados
     */
    private Dao dao;

    /**
     * Link do banco de dados
     */
    private ConnectionSource conn;

    /**
     * São inicializados o file, conn e dao para uso nas funções
     *
     * @param gFile Classe do objeto vindo da classe filha (ex.: ProductModel)
     */
    public GenericDao(Class gFile)
    {
        file = gFile;
        conn = conn();
        try
        {
            dao = DaoManager.createDao( conn, file );
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    /**
     * Cria registro no banco de dados com o objeto informado
     *
     * @param object
     * @return retorna o ID do objeto registrado ou 0 em caso de falha
     */
    public Integer save(GenericBO object)
    {
        try {
            Integer result = dao.create(object);

            /**
             * Quando há sucesso na criação do registro o objeto recebe os dados de preenchimento automático
             * do BD, como ID e createdAt, sabendo disso, abaixo eu utilizo a função getID do próprio objeto
             * para poder retornar o ID do mesmo em caso do sucesso anteriomente citado.
             */
            Integer boID;
            if (result == 1)
                boID = object.getID();
            else
                boID = 0;

            return boID;
        }
        catch (Exception e){ e.printStackTrace(); }
        return 0;
    }

    /**
     * Faz o update do registro relativo ao registro informado
     *
     * @param object
     * @return 1 caso tenha sucesso ou 0 em caso de falha
     */
    public Integer edit(Object object)
    {
        try
        {
            Integer result = dao.update(object);
            conn.close();

            return result;
        }
        catch (Exception e){ e.printStackTrace(); }
        return 0;
    }

    /**
     * Busca um registro unico do BD buscando apenas pelo ID
     *
     * @param ID
     * @return
     */
    public Object getOne(Integer ID)
    {
        try
        {
            Object object = dao.queryForId(ID);
            conn.close();

            return object;
        }
        catch (Exception e){ e.printStackTrace(); }

        return null;
    }

    /**
     * Busca a lista completa de registros
     *
     * @return
     */
    public List getAll()
    {
        try
        {
            List genericList = dao.queryForAll();
            conn.close();

            return genericList;
        }
        catch (Exception e){ e.printStackTrace(); }

        return null;
    }

    /**
     * Cria o link de conexão com o BD
     *
     * @return
     */
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
        catch (Exception e){ e.printStackTrace(); }

        return null;
    }
}
