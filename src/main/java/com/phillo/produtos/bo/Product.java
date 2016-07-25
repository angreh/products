package com.phillo.produtos.bo;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Angreh (angreh@gmail.com) on 7/16/2016.
 */

@DatabaseTable(tableName = "product")
public class Product
{
    @DatabaseField(generatedId = true, canBeNull = false)
    public Integer ID;

    @DatabaseField(canBeNull = false)
    public Integer domainID;

    @DatabaseField(canBeNull = false)
    public String name;

    @DatabaseField
    public String description;

    @DatabaseField
    public Float price;

    @DatabaseField
    public Float sellPrice;

    @DatabaseField
    public String img;

    @DatabaseField(canBeNull = false)
    public String addedAt;

    public Product() {}
}
