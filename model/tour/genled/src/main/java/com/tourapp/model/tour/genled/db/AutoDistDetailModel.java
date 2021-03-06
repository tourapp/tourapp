/**
  * @(#)AutoDistDetailModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.genled.db;

import org.jbundle.model.db.*;

public interface AutoDistDetailModel extends Rec
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    public static final String AUTO_DIST_ID = "AutoDistID";
    public static final String DIST_ACCOUNT_ID = "DistAccountID";
    public static final String DIST_PERCENT = "DistPercent";

    public static final String AUTO_DIST_ID_KEY = "AutoDistID";
    public static final String AUTO_DIST_DETAIL_SCREEN_CLASS = "com.tourapp.tour.genled.screen.autodist.AutoDistDetailScreen";
    public static final String AUTO_DIST_DETAIL_GRID_SCREEN_CLASS = "com.tourapp.tour.genled.screen.autodist.AutoDistDetailGridScreen";

    public static final String AUTO_DIST_DETAIL_FILE = "AutoDistDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.genled.db.AutoDistDetail";
    public static final String THICK_CLASS = "com.tourapp.tour.genled.db.AutoDistDetail";

}
