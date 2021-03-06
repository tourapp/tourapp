/**
  * @(#)PaymentRequest.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.acctpay.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.acctpay.db.*;

public class PaymentRequest extends FieldList
    implements PaymentRequestModel
{
    private static final long serialVersionUID = 1L;


    public PaymentRequest()
    {
        super();
    }
    public PaymentRequest(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }
    public static final String PAYMENT_REQUEST_FILE = "PaymentRequest";
    /**
     *  Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? PaymentRequest.PAYMENT_REQUEST_FILE : super.getTableNames(bAddQuotes);
    }
    /**
     *  Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "acctpay";
    }
    /**
     *  Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return Constants.LOCAL;
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
        field = new FieldInfo(this, BANK_ACCT_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, VENDOR_ID, 6, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, AMOUNT, 12, null, null);
        field.setDataClass(Double.class);
        field = new FieldInfo(this, TRX_STATUS_ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, ACCOUNT_ID, 7, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, CHECK_NO, 8, null, null);
        field.setDataClass(Integer.class);
        field = new FieldInfo(this, COMMENTS, 30, null, null);
    }
    /**
    * Set up the key areas.
    */
    public void setupKeys()
    {
        KeyAreaInfo keyArea = null;
        keyArea = new KeyAreaInfo(this, Constants.UNIQUE, ID_KEY);
        keyArea.addKeyField(ID, Constants.ASCENDING);
        keyArea = new KeyAreaInfo(this, Constants.NOT_UNIQUE, BANK_ACCT_ID_KEY);
        keyArea.addKeyField(BANK_ACCT_ID, Constants.ASCENDING);
        keyArea.addKeyField(VENDOR_ID, Constants.ASCENDING);
    }

}
