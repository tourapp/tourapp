/**
 * @(#)ProductHeaderScreen.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.product.base.screen;

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
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;

/**
 *  ProductHeaderScreen - .
 */
public class ProductHeaderScreen extends HeaderScreen
{
    /**
     * Default constructor.
     */
    public ProductHeaderScreen()
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
    public ProductHeaderScreen(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        this();
        this.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Initialize class fields.
     */
    public void init(ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc)
    {
        super.init(itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(ProductScreenRecord.kProductScreenRecordFile).getField(ProductScreenRecord.kProductID).setEnabled(true);
        this.getRecord(ProductScreenRecord.kProductScreenRecordFile).getField(ProductScreenRecord.kStartDate).setEnabled(true);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        // Make sure these are linked.
        Record recVen = ((ReferenceField)((BaseScreen)this.getParentScreen()).getHeaderRecord().getField(Product.kVendorID)).getReferenceRecord((BaseScreen)this.getParentScreen());
        Record recCurrency = ((ReferenceField)recVen.getField(Vendor.kCurrencysID)).getReferenceRecord((BaseScreen)this.getParentScreen());
        Record recProduct = ((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.kProductID)).getReferenceRecord(null, false);
        if (recProduct == null)
        {    // No product is linked to the product field (link the one from the main screen)
            recProduct = this.getHeaderRecord();
            ((ReferenceField)this.getRecord(ProductScreenRecord.kProductScreenRecordFile).getField(ProductScreenRecord.kProductID)).setReferenceRecord(recProduct);
        }
        ((ReferenceField)this.getScreenRecord().getField(ProductScreenRecord.kProductID)).syncReference(recProduct);
        
        this.getRecord(ProductScreenRecord.kProductScreenRecordFile).getField(ProductScreenRecord.kProductID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        recProduct.getField(Product.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Record recVendor = ((ReferenceField)recProduct.getField(Product.kVendorID)).getReferenceRecord();
        recVendor.getField(Vendor.kCurrencysID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        Record recCurrencys = ((ReferenceField)recVendor.getField(Vendor.kCurrencysID)).getReferenceRecord();
        recCurrencys.getField(Currencys.kCostingRate).setupDefaultView(this.getNextLocation(ScreenConstants.RIGHT_OF_LAST, ScreenConstants.DONT_SET_ANCHOR), this, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * I added this method, so I can override it for other header screens
     * (see InventoryHeaderScreen).
     */
    public Record getHeaderRecord()
    {
        return ((BaseScreen)this.getParentScreen()).getHeaderRecord();
    }

}
