/**
  * @(#)CreditCardEntryScreen.
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
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.acctrec.screen.mco.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctrec.screen.credit.dist.*;

/**
 *  CreditCardEntryScreen - Credit Card File.
 */
public class CreditCardEntryScreen extends McoEntryScreen
{
    /**
     * Default constructor.
     */
    public CreditCardEntryScreen()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public CreditCardEntryScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Credit Card File";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new CreditCard(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        // Don't call super (because MCO stuff would be set up)
        this.getMainRecord().getField(CreditCard.CARD_ID).addListener(new InitFieldHandler(this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.CARD_ID)));
        this.getMainRecord().getField(CreditCard.SVC_PER).addListener(new InitFieldHandler(this.getRecord(ArControl.AR_CONTROL_FILE).getField(ArControl.CREDIT_CARD_SVC_PER)));
        
        ((TrxStatus)this.getRecord(TrxStatus.TRX_STATUS_FILE)).getTrxStatusID(TransactionType.ACCTREC, CreditCard.CREDIT_CARD_FILE, CreditCard.BATCH);
        this.getMainRecord().setKeyArea(CreditCard.TRX_STATUS_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getRecord(TrxStatus.TRX_STATUS_FILE)));
        
        this.getMainRecord().getField(CreditCard.NET).setEnabled(false);
        
        this.getMainRecord().addListener(new SubFileIntegrityHandler(CreditCardBatchDist.class.getName(), true));
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        String strDesc = "Distribution";
        if (this.getTask() != null)
            strDesc = ((BaseApplication)this.getTask().getApplication()).getResources(ResourceConstants.ACCTREC_RESOURCE, true).getString(strDesc);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, strDesc, MenuConstants.FORMDETAIL, MenuConstants.FORMDETAIL, null);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, null, MenuConstants.POST, MenuConstants.POST, MenuConstants.POST, null);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        Converter converter = this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.BOOKING_ID);
        converter = new CreditCardDistConverter(converter);
        converter.setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.TRX_STATUS_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.CARD_NO).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.CARD_ID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.EXPIRATION).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.TRX_DATE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.GROSS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.SVC_PER).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.SVC_AMT).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.NET).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.AMT_APPLY).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(CreditCard.CREDIT_CARD_FILE).getField(CreditCard.COMMENTS).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Process the command.
     * <br />Step 1 - Process the command if possible and return true if processed.
     * <br />Step 2 - If I can't process, pass to all children (with me as the source).
     * <br />Step 3 - If children didn't process, pass to parent (with me as the source).
     * <br />Note: Never pass to a parent or child that matches the source (to avoid an endless loop).
     * @param strCommand The command to process.
     * @param sourceSField The source screen field (to avoid echos).
     * @param iCommandOptions If this command creates a new screen, create in a new window?
     * @return true if success.
     */
    public boolean doCommand(String strCommand, ScreenField sourceSField, int iCommandOptions)
    {
        if ((strCommand.equalsIgnoreCase(MenuConstants.FORM)) || (strCommand.equalsIgnoreCase(MenuConstants.FORMLINK)))
            return (this.onForm(null, CreditCard.ENTRY_SCREEN, true, iCommandOptions, null) != null);
        else
            return super.doCommand(strCommand, sourceSField, iCommandOptions);
    }

}
