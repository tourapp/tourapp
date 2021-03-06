/**
  * @(#)StatusSummaryField.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.product.base.db;

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
import org.jbundle.base.screen.model.util.*;
import com.tourapp.thin.app.booking.entry.*;
import org.jbundle.base.screen.model.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  StatusSummaryField - This field is used to summarize the CustSaleDetail status of:
Information
Price
Inventory.
 */
public class StatusSummaryField extends IntegerField
{
    /**
     * Default constructor.
     */
    public StatusSummaryField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public StatusSummaryField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * When they ask for data, make sure the main icon is correct (for grid usage).
     */
    public Object getData()
    {
        Object data = super.getData();
        String strProductType = this.getProductType();
        if (this.getComponent(0) instanceof org.jbundle.base.screen.model.opt.SBlinkImageView)
        { // Always
            org.jbundle.base.screen.model.opt.SBlinkImageView blink = (org.jbundle.base.screen.model.opt.SBlinkImageView)this.getComponent(0);
            BasePanel targetScreen = blink.getParentScreen();
            if (targetScreen != null)
                if (targetScreen.getAppletScreen() != null)
                {
                    org.jbundle.thin.base.screen.BaseApplet applet = (org.jbundle.thin.base.screen.BaseApplet)targetScreen.getAppletScreen().getTask();
                    if ((blink.getImageIcon(IntegerField.ZERO) == null)
                        || (!strProductType.equalsIgnoreCase(blink.getImageIcon(IntegerField.ZERO).toString())))
                        {
                            blink.addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + strProductType, strProductType), 0);
                        }
                    BookingDetailModel recCustSaleDetail = (BookingDetailModel)((BaseField)this.getField()).getRecord();
                    if (recCustSaleDetail.getField(BookingDetailModel.PRODUCT_STATUS_ID).getValue() == ProductStatus.CANCELED)
                        blink.addIcon(applet.loadImageIcon(ThinMenuConstants.CANCEL, ThinMenuConstants.CANCEL), 0);
                }
        }
        return data;
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        if (properties == null)
            properties = new HashMap<String,Object>();
        properties.put(ScreenModel.NEVER_DISABLE, Constants.TRUE);
        ScreenComponent blink = createScreenComponent(ScreenModel.BLINK_IMAGE, itsLocation, targetScreen, converter, iDisplayFieldDesc, properties);
        blink.setRequestFocusEnabled(false);     // By default, make user click with mouse
        if (((BasePanel)targetScreen).getAppletScreen() != null)
            if (((BasePanel)targetScreen).getAppletScreen().getTask() instanceof org.jbundle.thin.base.screen.BaseApplet)
        {
            org.jbundle.thin.base.screen.BaseApplet applet = (org.jbundle.thin.base.screen.BaseApplet)((BasePanel)targetScreen).getAppletScreen().getTask();
            String strProductType = this.getProductType();
            ((ExtendedComponent)blink).addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + strProductType, strProductType), 0);
            ((ExtendedComponent)blink).addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INFO, BookingConstants.INFO), BookingConstants.INFO_LOOKUP);
            ((ExtendedComponent)blink).addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.COST, BookingConstants.COST), BookingConstants.COST_LOOKUP);
            ((ExtendedComponent)blink).addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.INVENTORY, BookingConstants.INVENTORY), BookingConstants.INVENTORY_LOOKUP);
            ((ExtendedComponent)blink).addIcon(applet.loadImageIcon(BookingConstants.BUTTON_LOCATION + BookingConstants.PRODUCT, BookingConstants.PRODUCT), BookingConstants.PRODUCT_LOOKUP);
        }
        
        if (!(targetScreen instanceof GridScreen))
        {   // If it is not in a grid screen, add the description
            itsLocation = targetScreen.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR);
            iDisplayFieldDesc = ScreenConstants.DONT_DISPLAY_FIELD_DESC;
            converter = new StatusDescConverter((Converter)converter);
            ScreenComponent sEditText = createScreenComponent(ScreenModel.EDIT_TEXT, itsLocation, targetScreen, converter, iDisplayFieldDesc, properties);
            sEditText.setEnabled(false);    // Remember, can't change the cost status.
        }
        
        return blink;
    }
    /**
     * GetProductType Method.
     */
    public String getProductType()
    {
        BookingDetailModel recCustSaleDetail = (BookingDetailModel)((BaseField)this.getField()).getRecord();
        String strProductType = recCustSaleDetail.getField(BookingDetailModel.PRODUCT_TYPE).toString();
        if ((strProductType == null) || (strProductType.length() == 0))
            strProductType = ProductType.ITEM;
        return strProductType;
    }

}
