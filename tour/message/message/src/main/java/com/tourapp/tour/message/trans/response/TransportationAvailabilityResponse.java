/**
  * @(#)TransportationAvailabilityResponse.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.trans.response;

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
import com.tourapp.tour.message.base.response.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.model.message.*;

/**
 *  TransportationAvailabilityResponse - .
 */
public class TransportationAvailabilityResponse extends ProductAvailabilityResponse
{
    /**
     * Default constructor.
     */
    public TransportationAvailabilityResponse()
    {
        super();
    }
    /**
     * TransportationAvailabilityResponse Method.
     */
    public TransportationAvailabilityResponse(MessageDataParent messageDataParent, String strKey)
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

}
