/**
  * @(#)BankAcctHeaderScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.assetdr.screen.trx;

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
import java.util.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.assetdr.db.*;

/**
 *  BankAcctHeaderScreen - Header screen for acct detail inquiry.
 */
public class BankAcctHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public BankAcctHeaderScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?.
     */
    public BankAcctHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Header screen for acct detail inquiry";
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID).setEnabled(true);
        this.getScreenRecord().getField(BankTrxScreenRecord.START_DATE).addListener(new RegisterValueHandler(null));
        this.getScreenRecord().getField(BankTrxScreenRecord.DISPLAY_BALANCE).addListener(new RegisterValueHandler(null));
        if (this.getScreenRecord().getField(BankTrxScreenRecord.START_DATE).isNull())
        {   // If the starting and ending date haven't been set yet, set them to the current period start and end.
            Date date = new Date();     // Now
            Period recPeriod = new Period((BaseScreen)this.getParentScreen()); // Note: I Use READ_MOVE, because RegisterBehavior doesn't respond to it.
            if (this.getScreenRecord().getField(BankTrxScreenRecord.START_DATE).isNull())
                ((DateTimeField)this.getScreenRecord().getField(BankTrxScreenRecord.START_DATE)).setDate(recPeriod.getPeriodStartDate(date), DBConstants.DISPLAY, DBConstants.READ_MOVE);
            recPeriod.free();
            recPeriod = null;
        }
        this.getScreenRecord().getField(BankTrxScreenRecord.START_DATE).setEnabled(true);
        this.getScreenRecord().getField(BankTrxScreenRecord.DISPLAY_BALANCE).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        // This will synchronize the screen field and make sure the lookup uses the header record
        ((ReferenceField)this.getScreenRecord().getField(BankTrxScreenRecord.BANK_ACCT_ID)).setReference(this.getRecord(BankAcct.BANK_ACCT_FILE), DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
        this.getRecord(BankTrxScreenRecord.BANK_TRX_SCREEN_RECORD_FILE).getField(BankTrxScreenRecord.BANK_ACCT_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.BANK_TRX_SCREEN_RECORD_FILE).getField(BankTrxScreenRecord.START_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_INPUT_LOCATION, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.BANK_TRX_SCREEN_RECORD_FILE).getField(BankTrxScreenRecord.DISPLAY_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(BankTrxScreenRecord.BANK_TRX_SCREEN_RECORD_FILE).getField(BankTrxScreenRecord.END_BALANCE).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_WITH_DESC, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DEFAULT_DISPLAY);
    }

}
