/**
  * @(#)CashBatchScreenRecord_UserID.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.cash;

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
import org.jbundle.main.user.db.*;

/**
 *  CashBatchScreenRecord_UserID - .
 */
public class CashBatchScreenRecord_UserID extends UserField
{
    /**
     * Default constructor.
     */
    public CashBatchScreenRecord_UserID()
    {
        super();
    }
    /**
     * CashBatchScreenRecord_UserID Method.
     */
    public CashBatchScreenRecord_UserID(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize the field.
     */
    public int initField(boolean displayOption)
    {
        return this.setValue(getUserID(), displayOption, DBConstants.INIT_MOVE);
    }

}
