/**
  * @(#)TaxRate.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.payroll.db.*;

public class TaxRate extends FieldList
    implements TaxRateModel
{
    private static final long serialVersionUID = 1L;


    public TaxRate()
    {
        super();
    }
    public TaxRate(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String TAX_RATE_FILE = "TaxRate";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? TaxRate.TAX_RATE_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "payroll";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL | Constants.USER_DATA;
    }
    /**
    * Set up the screen input fields.
    */
    public void setupFields()
    {
        FieldInfo field = null;
        field = new FieldInfo(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field.setHidden(true);
        field = new FieldInfo(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Date.class);
        field.setHidden(true);
        field = new FieldInfo(this, DELETED, 10, null, new Boolean(false));
        field.setDataClass(Boolean.class);
        field.setHidden(true);
        field = new FieldInfo(this, TAX_CODE, 2, null, null);
        field = new FieldInfo(this, MARITAL_STATUS, 1, null, null);
        field = new FieldInfo(this, CUT_OFF_AMOUNT, 8, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TAX_RATE, 5, null, null);
        field.setDataClass(Float.class);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, TAX_CODE_KEY);
        keyArea.addKeyField(TAX_CODE, Constants.ASCENDING);
        keyArea.addKeyField(MARITAL_STATUS, Constants.ASCENDING);
        keyArea.addKeyField(CUT_OFF_AMOUNT, Constants.ASCENDING);
    }

}
