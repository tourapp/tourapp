/**
  * @(#)BookingSoftDeleteHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.booking.db.event;

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
import com.tourapp.tour.booking.db.*;
import com.tourapp.tour.product.tour.db.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.acctrec.db.*;
import com.tourapp.tour.booking.history.db.*;
import com.tourapp.tour.profile.db.*;
import com.tourapp.tour.product.tour.schedule.db.*;
import com.tourapp.tour.booking.detail.db.*;
import com.tourapp.tour.product.tour.detail.db.*;
import com.tourapp.tour.acctrec.db.event.*;
import com.tourapp.model.tour.acctrec.db.*;
import com.tourapp.model.tour.booking.detail.db.*;
import com.tourapp.model.tour.product.tour.db.*;
import java.util.*;

/**
 *  BookingSoftDeleteHandler - Soft delete this bookingAdd code to make this work..
 */
public class BookingSoftDeleteHandler extends SoftDeleteHandler
{
    /**
     * Default constructor.
     */
    public BookingSoftDeleteHandler()
    {
        super();
    }
    /**
     * BookingSoftDeleteHandler Method.
     */
    public BookingSoftDeleteHandler(BaseField fldDeleteFlag)
    {
        this();
        this.init(fldDeleteFlag);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
    }
    /**
     * Soft delete this record.
     * Set the deleted flag.
     */
    public int softDeleteThisRecord()
    {
        return DBConstants.NORMAL_RETURN; // Fix this
    }
    /**
     * Soft delete this record?
     * Override this to decide whether to soft delete or physically delete the record.
     */
    public boolean isSoftDeleteThisRecord()
    {
        return true;    // Fix this
    }

}
