/**
  * @(#)BookingStatusModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.product.base.db;

import com.tourapp.model.tour.product.base.db.*;

public interface BookingStatusModel extends BaseProductStatusModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String ICON = ICON;
    //public static final String CODE = CODE;
    public static final String FOLLOWS_BOOKING_STATUS_ID = "FollowsBookingStatusID";

    public static final String CODE_KEY = "Code";
    public static final String BOOKING_STATUS_GRID_SCREEN_CLASS = "com.tourapp.tour.product.base.screen.BookingStatusGridScreen";

    public static final String BOOKING_STATUS_FILE = "BookingStatus";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.base.db.BookingStatus";
    public static final String THICK_CLASS = "com.tourapp.tour.product.base.db.BookingStatus";

}
