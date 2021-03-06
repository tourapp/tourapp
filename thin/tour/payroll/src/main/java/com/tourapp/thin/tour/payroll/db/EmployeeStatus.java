/**
  * @(#)EmployeeStatus.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.thin.tour.payroll.db;

import java.util.*;
import org.jbundle.thin.base.util.*;

import org.jbundle.thin.base.db.*;

import com.tourapp.thin.tour.payroll.db.*;
import com.tourapp.model.tour.payroll.db.*;

public class EmployeeStatus extends Employee
    implements EmployeeStatusModel
{
    private static final long serialVersionUID = 1L;


    public EmployeeStatus()
    {
        super();
    }
    public EmployeeStatus(Object recordOwner)
    {
        this();
        this.init(recordOwner);
    }

}
