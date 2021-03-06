/**
  * @(#)CreditCardBatchDistGridScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.credit;

import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;
import com.tourapp.tour.acctrec.screen.cash.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.acctrec.screen.mco.*;

/**
 *  CreditCardBatchDistGridScreen - Credit Cards.
 */
public class CreditCardBatchDistGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public CreditCardBatchDistGridScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public CreditCardBatchDistGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Credit Cards";
    }
    /**
     * CreditCardBatchDistGridScreen Method.
     */
    public CreditCardBatchDistGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
     properties)
    {
        this();
        this.init(recHeader, record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new CreditCardBatchDist(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new CreditCard(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CashBatchScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.ID).addListener(new FieldReSelectHandler(this));
        
        this.getRecord(CreditCardBatchDist.CREDIT_CARD_BATCH_DIST_FILE).addListener(new SubCountHandler(this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE), CreditCardBatchDist.AMOUNT, false, true));
        this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE).addListener(new CalcBalanceHandler(this.getScreenRecord().getField(CashBatchScreenRecord.END_BALANCE), this.getScreenRecord().getField(CashBatchScreenRecord.CHANGE_BALANCE), this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.NET), true));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        this.getRecord(CreditCardBatchDist.CREDIT_CARD_BATCH_DIST_FILE).getField(CreditCardBatchDist.BOOKING_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCardBatchDist.CREDIT_CARD_BATCH_DIST_FILE).getField(CreditCardBatchDist.AMOUNT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCardBatchDist.CREDIT_CARD_BATCH_DIST_FILE).getField(CreditCardBatchDist.ACCOUNT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new BaseArPayDistHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
