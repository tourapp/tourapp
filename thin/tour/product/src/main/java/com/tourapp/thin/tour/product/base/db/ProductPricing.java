/**
  * @(#)ProductPricing.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.product.base.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.model.tour.product.base.db.*;

public class ProductPricing extends FieldList
    implements ProductPricingModel
{
    private static final long serialVersionUID = 1L;


    public ProductPricing()
    {
        super();
    }
    public ProductPricing(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
