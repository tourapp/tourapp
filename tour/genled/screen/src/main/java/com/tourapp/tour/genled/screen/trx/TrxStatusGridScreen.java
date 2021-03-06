/**
  * @(#)TrxStatusGridScreen.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.screen.trx;

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
import com.tourapp.tour.genled.db.*;

/**
 *  TrxStatusGridScreen - Transaction type.
 */
public class TrxStatusGridScreen extends DetailGridScreen
{
    /**
     * Default constructor.
     */
    public TrxStatusGridScreen()
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
    public TrxStatusGridScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Transaction type";
    }
    /**
     * TrxStatusGridScreen Method.
     */
    public TrxStatusGridScreen(Record recHeader, Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object>
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
        return new TrxStatus(this);
    }
    /**
     * OpenHeaderRecord Method.
     */
    public Record openHeaderRecord()
    {
        return new TrxDesc(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new TrxStatusScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getMainRecord().setKeyArea(TrxStatus.TRX_DESC_ID_KEY);
        this.getMainRecord().addListener(new SubFileFilter(this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_DESC_ID), TrxStatus.TRX_DESC_ID, null, null, null, null));
        this.getScreenRecord().getField(TrxStatusScreenRecord.TRX_DESC_ID).addListener(new FieldReSelectHandler(this));
        
        this.getMainRecord().addListener(new UpdateTrxStatusHandler(null, null));
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        Record recTrxStatus = this.getMainRecord();
        Record recTrxDesc = this.getRecord(TrxDesc.TRX_DESC_FILE);
        Record recScreenRecord = this.getScreenRecord();
        
        ((ReferenceField)recScreenRecord.getField(TrxStatusScreenRecord.TRX_DESC_ID)).setReferenceRecord(recTrxDesc);
        if (!recTrxDesc.getField(TrxDesc.ID).isNull())
            recScreenRecord.getField(TrxStatusScreenRecord.TRX_DESC_ID).moveFieldToThis(recTrxDesc.getField(TrxDesc.ID));
        else
        {
            ReferenceField field = (ReferenceField)recScreenRecord.getField(TrxStatusScreenRecord.TRX_DESC_ID);
            field.moveFieldToThis(recTrxStatus.getField(TrxStatus.TRX_DESC_ID));
            if (field.isNull())
                if (this.getProperty(DBParams.HEADER_OBJECT_ID) != null)
                    field.setString(this.getProperty(DBParams.HEADER_OBJECT_ID));
            field.getReference();    // Read the record
        }
        ((ReferenceField)recScreenRecord.getField(TrxStatusScreenRecord.TRX_SYSTEM_ID)).getReferenceRecord(this);
        this.getRecord(TrxStatus.TRX_STATUS_FILE).getField(TrxStatus.STATUS_CODE).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TrxStatus.TRX_STATUS_FILE).getField(TrxStatus.STATUS_DESC).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TrxStatus.TRX_STATUS_FILE).getField(TrxStatus.PREFERRED_SIGN).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(TrxStatus.TRX_STATUS_FILE).getField(TrxStatus.ACTIVE_TRX).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
    }
    /**
     * Make a sub-screen.
     * @return the new sub-screen.
     */
    public BasePanel makeSubScreen()
    {
        return new TrxDescStatusHeaderScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
