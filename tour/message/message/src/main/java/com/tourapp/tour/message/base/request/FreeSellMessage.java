/**
  * @(#)FreeSellMessage.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.base.request;

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
import com.tourapp.tour.message.base.*;
import org.jbundle.thin.base.message.*;
import org.jbundle.model.message.*;
import com.tourapp.model.tour.product.base.db.*;

/**
 *  FreeSellMessage - Free sell this item (status is automatically set to VALID).
 */
public class FreeSellMessage extends BaseProductMessageDesc
{
    /**
     * Default constructor.
     */
    public FreeSellMessage()
    {
        super();
    }
    /**
     * FreeSellMessage Method.
     */
    public FreeSellMessage(MessageDataParent messageDataParent, String strKey)
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
     * Check to make sure all the data is present to attempt a cost lookup.
     * Note: You are NOT returning the status, you are returning the status of the params,
     * The calling program will change the status if required.
     * @return DATA_REQUIRED if all the data is not present, DATA_VALID if the data is OKAY.
     */
    public int checkRequestParams(Rec record)
    {
        return BaseDataStatusModel.VALID; // Status is always valid for free-sells
    }

}
