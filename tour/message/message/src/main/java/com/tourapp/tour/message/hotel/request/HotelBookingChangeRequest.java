/**
  * @(#)HotelBookingChangeRequest.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.hotel.request;

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
import org.jbundle.thin.base.message.*;
import org.jbundle.main.msg.db.*;
import com.tourapp.tour.message.base.request.data.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.booking.detail.db.*;

/**
 *  HotelBookingChangeRequest - .
 */
public class HotelBookingChangeRequest extends HotelBookingRequest
{
    /**
     * Default constructor.
     */
    public HotelBookingChangeRequest()
    {
        super();
    }
    /**
     * HotelBookingChangeRequest Method.
     */
    public HotelBookingChangeRequest(MessageDataParent messageDataParent, String strKey)
    {
        this();
        this.init(messageDataParent, strKey);
    }
    /**
     * Initialize class fields.
     */
    public void init(MessageDataParent messageDataParent, String strKey)
    {
        super.init(messageDataParent, strKey);
    }
    /**
     * Setup sub-Message Data.
     */
    public void setupMessageDataDesc()
    {
        super.setupMessageDataDesc();
        ((ProductMessageData)this.getMessageDataDesc(PRODUCT_MESSAGE)).addMessageFieldDesc(BookingDetailModel.REMOTE_BOOKING_NO, String.class, MessageFieldDesc.REQUIRED, MessageFieldDesc.NOT_UNIQUE | MessageFieldDesc.DONT_INIT, null);
    }
    /**
     * GetRequestType Method.
     */
    public String getRequestType()
    {
        return RequestType.BOOKING_CHANGE;
    }

}
