/**
  * @(#)BaseArPay.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.acctrec.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.genled.db.*;
import com.tourapp.model.tour.acctrec.db.*;

public class BaseArPay extends BaseTrx
    implements BaseArPayModel
{
    private static final long serialVersionUID = 1L;


    public BaseArPay()
    {
        super();
    }
    public BaseArPay(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
