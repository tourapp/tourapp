/**
  * @(#)BookingItemAvailMsgReplyInProcessor.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.item.response.in;

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
import com.tourapp.tour.message.base.response.in.*;
import com.tourapp.tour.booking.detail.db.*;

/**
 *  BookingItemAvailMsgReplyInProcessor - .
 */
public class BookingItemAvailMsgReplyInProcessor extends BookingDetailAvailMsgReplyInProcessor
{
    /**
     * Default constructor.
     */
    public BookingItemAvailMsgReplyInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public BookingItemAvailMsgReplyInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
    {
        this();
        this.init(taskParent, recordMain, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwnerParent taskParent, Record recordMain, Map<String, Object> properties)
    {
        super.init(taskParent, recordMain, properties);
    }
    /**
     * Open the main file.
     */
    public Record openMainRecord()
    {
        return new BookingItem(this);
    }

}
