/**
  * @(#)ProductChain.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.base.db.*;

public class ProductChain extends FieldList
    implements ProductChainModel
{
    private static final long serialVersionUID = 1L;


    public ProductChain()
    {
        super();
    }
    public ProductChain(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
