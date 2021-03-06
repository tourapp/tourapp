/**
  * @(#)TourTypeModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.product.tour.db;

import org.jbundle.model.db.*;

public interface TourTypeModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String DESCRIPTION = "Description";
    public static final String CODE = "Code";

    public static final String DESCRIPTION_KEY = "Description";

    public static final String CODE_KEY = "Code";
    public static final int SERIES = 1 << 1;

    public static final String TOUR_TYPE_FILE = "TourType";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.product.tour.db.TourType";
    public static final String THICK_CLASS = "com.tourapp.tour.product.tour.db.TourType";

}
