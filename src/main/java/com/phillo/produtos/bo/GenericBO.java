package com.phillo.produtos.bo;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Angreh(angreh@gmail.com) on 7/31/2016.
 */
public class GenericBO
{
    @DatabaseField(generatedId = true, canBeNull = false)
    protected Integer ID;

    public Integer getID()
    {
        return ID;
    }
}
