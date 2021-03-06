/**
  * @(#)CarInventoryModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.booking.inventory.db;

import com.tourapp.model.tour.booking.inventory.db.*;

public interface CarInventoryModel extends InventoryModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    //public static final String PRODUCT_ID = PRODUCT_ID;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String OTHER_ID = OTHER_ID;
    //public static final String INV_DATE = INV_DATE;
    //public static final String BLOCKED = BLOCKED;
    //public static final String USED = USED;
    //public static final String AVAILABLE = AVAILABLE;
    //public static final String OVERSELL = OVERSELL;
    //public static final String CLOSED = CLOSED;
    public static final String CAR_INVENTORY_SCREEN_CLASS = "com.tourapp.tour.product.car.screen.CarInventoryScreen";
    public static final String CAR_INVENTORY_GRID_SCREEN_CLASS = "com.tourapp.tour.product.car.screen.CarInventoryGridScreen";
    public static final String CAR_INVENTORY_RANGE_ADJUST_CLASS = "com.tourapp.tour.product.car.screen.CarInventoryRangeAdjust";

    public static final String CAR_INVENTORY_FILE = "Inventory";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.inventory.db.CarInventory";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.inventory.db.CarInventory";

}
