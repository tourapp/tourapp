/**
  * @(#)CalcNewBalBeh.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.assetdr.db;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  CalcNewBalBeh - Calculate the new balance.
 */
public class CalcNewBalBeh extends FileListener
{
    protected Record m_AdChecking = null;
    protected CalcNewBal m_CalcBalBeh = null;
    protected BaseField m_DateField = null;
    /**
     * Default constructor.
     */
    public CalcNewBalBeh()
    {
        super();
    }
    /**
     * CalcNewBalBeh Method.
     */
    public CalcNewBalBeh(BaseField dateField, Record adChecking, CalcNewBal calcBalBeh)
    {
        this();
        this.init(dateField, adChecking, calcBalBeh);
    }
    /**
     * Initialize class fields.
     */
    public void init(BaseField dateField, Record adChecking, CalcNewBal calcBalBeh)
    {
        m_AdChecking = null;
        m_CalcBalBeh = null;
        m_DateField = null;
        m_DateField = dateField;
        m_AdChecking = adChecking;
        m_CalcBalBeh = calcBalBeh;
        super.init(null);
    }
    /**
     * Called when a valid record is read from the table/query.
     * @param bDisplayOption If true, display any changes.
     */
    public void doValidRecord(boolean bDisplayOption)
    {
        super.doValidRecord(bDisplayOption);
        if (m_DateField.getLength() == 0)
            this.getOwner().getField(BankAcct.BALANCE).initField(DBConstants.DISPLAY);
        else
        {
            double balance = 0;
            m_AdChecking.close();
            try   {
                while (m_AdChecking.hasNext())
                {
                    m_AdChecking.next();
                    balance += m_AdChecking.getField(BankTrx.AMOUNT).getValue();
                }
            } catch (DBException ex)    {
                ex.printStackTrace();
            }
            this.getOwner().getField(BankAcct.BALANCE).setValue(balance);
        }
    }

}
