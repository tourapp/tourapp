/**
  * @(#)TransBookingRequestMessageInProcessor.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.message.trans.request.in;

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
import com.tourapp.tour.message.base.request.in.*;
import com.tourapp.tour.product.base.db.*;
import com.tourapp.tour.product.trans.db.*;

/**
 *  TransBookingRequestMessageInProcessor - .
 */
public class TransBookingRequestMessageInProcessor extends ProductBookingRequestMessageInProcessor
{
    /**
     * Default constructor.
     */
    public TransBookingRequestMessageInProcessor()
    {
        super();
    }
    /**
     * Constructor.
     */
    public TransBookingRequestMessageInProcessor(RecordOwnerParent taskParent, Record recordMain, Map<String,Object> properties)
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
     * GetProductRecord Method.
     */
    public Product getProductRecord()
    {
        return new Transportation(this);
    }

}
