/**
  * @(#)BookingDetailQuery.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.booking.operations;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.booking.operations.*;

public class BookingDetailQuery extends FieldList
    implements BookingDetailQueryModel
{
    private static final long serialVersionUID = 1L;


    public BookingDetailQuery()
    {
        super();
    }
    public BookingDetailQuery(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
