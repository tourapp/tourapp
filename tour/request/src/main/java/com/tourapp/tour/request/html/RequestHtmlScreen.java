/**
 * @(#)RequestHtmlScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.request.html;

import java.awt.*;
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
import org.jbundle.base.screen.view.html.*;
import com.tourapp.tour.request.db.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import org.jbundle.base.screen.view.*;
import com.tourapp.tour.request.screen.*;
import com.tourapp.tour.base.db.shared.*;

/**
 *  RequestHtmlScreen - Brochure request entry - HTML version.
 */
public class RequestHtmlScreen extends Screen
{
    /**
     * Default constructor.
     */
    public RequestHtmlScreen()
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
    public RequestHtmlScreen(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
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
        return "Brochure request entry - HTML version";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new Request(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new RequestDetail(this);
        new RequestInput(this);
        new RequestControl(this);
        new BundleDetail(this);
        new Brochure(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        
        Record recRequestControl = this.getRecord(RequestControl.kRequestControlFile);
        Request recRequest = (Request)this.getRecord(Request.kRequestFile);
        RequestDetail recRequestDetail = (RequestDetail)this.getRecord(RequestDetail.kRequestDetailFile);
        BundleDetail recBundleDetail = (BundleDetail)this.getRecord(BundleDetail.kBundleDetailFile);
        Brochure recItem = (Brochure)this.getRecord(Brochure.kBrochureFile);
        RequestHtmlDetailGrid subScreen = null;
        for (int i = 0; i < this.getSFieldCount(); i++)
        {
            if (this.getSField(i) instanceof RequestHtmlDetailGrid)
                subScreen = (RequestHtmlDetailGrid)this.getSField(i);
        }
        RequestInput recRequestInput = (RequestInput)subScreen.getRecord(RequestInput.kRequestInputFile);
        
        try   {
            recRequestControl.open();
        } catch (DBException ex)    {
            ex.printStackTrace(); // Never
        }
        // Set up the initial detail.
        Record recBundle = ((ReferenceField)recRequestControl.getField(RequestControl.kHtmlBundleID)).getReference(); //this.getRecord(Bundle.kBundleFile);
        recBundleDetail.addListener(new SubFileFilter(recBundle));
        recRequestInput.addBundle(recRequestControl.getField(RequestControl.kHtmlBundleID), recBundleDetail, recItem, null);
        // Behaviors to add brochures on submit.
        recRequestDetail.addListener(new SubFileFilter(recRequest));
        recRequest.addListener(new SetupBrocDetailHandler(recRequest, recRequestDetail, recBundleDetail, recItem, recRequestInput));
        recRequest.setOpenMode(recRequest.getOpenMode() | DBConstants.OPEN_REFRESH_AND_LOCK_ON_CHANGE_STRATEGY);
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new ToolScreen(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Add button(s) to the toolbar.
     */
    public void addToolbarButtons(ToolScreen toolScreen)
    {
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST_BUTTON_WITH_GAP, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, MenuConstants.SUBMIT);
        new SCannedBox(toolScreen.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.SET_ANCHOR), toolScreen, null, ScreenConstants.DEFAULT_DISPLAY, MenuConstants.RESET);
    }
    /**
     * Set up all the screen fields.
     */
    public void setupSFields()
    {
        String strAgentParam = this.getProperty("agent");
        if (strAgentParam == null)
            strAgentParam = "no";
        if (strAgentParam.equalsIgnoreCase("yes"))
        {
            this.getRecord(Request.kRequestFile).getField(Request.kAttention).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
            this.getRecord(Request.kRequestFile).getField(Request.kProfileCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        }
        this.getRecord(Request.kRequestFile).getField(Request.kGenericName).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAddressLine1).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kAddressLine2).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kCityOrTown).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kStateOrRegion).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kPostalCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kCountry).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kEmail).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(Request.kRequestFile).getField(Request.kBrochureText).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        RequestInput recRequestInput = (RequestInput)this.getRecord(RequestInput.kRequestInputFile);
        new RequestHtmlDetailGrid(recRequestInput, this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, null, ScreenConstants.DEFAULT_DISPLAY, null);
    }
    /**
     * Same as onAdd, but don't clear the record, so I can redisplay it.
     */
    public boolean onAdd()
    {
        Record record = this.getMainRecord();
        try
        {
            if (record.isModified(false))
            {
                if (record.getEditMode() == Constants.EDIT_IN_PROGRESS)
                    record.set();
                else if (record.getEditMode() == Constants.EDIT_ADD)
                    record.add();
            }
        //x   record.addNew();
        }
        catch(DBException e)
        {
            this.displayError(e);
        }
        return true;
    }
    /**
     * Set up the physical control (that implements Component).
     * @param bEditableControl If false, set up a read-only control.
     * @return The new view.
     */
    public ScreenFieldView setupScreenFieldView(boolean bEditableControl)
    {
        if (ScreenModel.HTML_TYPE.equalsIgnoreCase(this.getViewFactory().getViewSubpackage()))
            return new HRequestHtmlScreen(this, bEditableControl);
        else
            return null;    // Not supported!
    }

}
