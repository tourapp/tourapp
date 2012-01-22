/**
 * @(#)TitleField.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.base.field;

import java.awt.*;
import java.util.*;

import org.jbundle.base.db.*;
import org.jbundle.thin.base.util.*;
import org.jbundle.thin.base.db.*;
import org.jbundle.base.db.event.*;
import org.jbundle.base.db.filter.*;
import org.jbundle.base.field.*;
import org.jbundle.base.field.convert.*;
import org.jbundle.base.field.event.*;
import org.jbundle.base.screen.model.*;
import org.jbundle.base.screen.model.util.*;
import org.jbundle.base.model.*;
import org.jbundle.base.util.*;
import org.jbundle.model.*;
import org.jbundle.model.db.*;
import org.jbundle.model.screen.*;

/**
 *  TitleField - Title (popup).
 */
public class TitleField extends StringField
{
    /**
     * Default constructor.
     */
    public TitleField()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The parent record.
     * @param strName The field name.
     * @param iDataLength The maximum string length (pass -1 for default).
     * @param strDesc The string description (usually pass null, to use the resource file desc).
     * @param strDefault The default value (if object, this value is the default value, if string, the string is the default).
     */
    public TitleField(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        this();
        this.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, String strName, int iDataLength, String strDesc, Object strDefault)
    {
        super.init(record, strName, iDataLength, strDesc, strDefault);
    }
    /**
     * Convert this index to a string.
     * This method is usually overidden by popup fields.
     * @param index The index to convert.
     * @return The display string.
     */
    public String convertIndexToString(int index)
    {
        String tempString = null;
        switch(index)
        {
            case 1:
                tempString = "Mr";break;
            case 2:
                tempString = "Ms";break;
            case 3:
                tempString = "Mrs";break;
            case 4:
                tempString = "Miss";break;
            case 5:
                tempString = "Dr";break;
            case 0:
            default:
                tempString = "";break;
        }
        return tempString;
    }
    /**
     * Convert this string to it's index value.
     */
    public int convertStringToIndex(String tempString)
    {
        short index = 0;
        if (tempString.equalsIgnoreCase("Mr"))
            index = 1;
        if (tempString.equalsIgnoreCase("Ms"))
            index = 2;
        if (tempString.equalsIgnoreCase("Mrs"))
            index = 3;
        if (tempString.equalsIgnoreCase("Miss"))
            index = 4;
        if (tempString.equalsIgnoreCase("Dr"))
            index = 5;
        return index;
    }
    /**
     * Set up the default screen control for this field.
     * @param itsLocation Location of this component on screen (ie., GridBagConstraint).
     * @param targetScreen Where to place this component (ie., Parent screen or GridBagLayout).
     * @param converter The converter to set the screenfield to.
     * @param iDisplayFieldDesc Display the label? (optional).
     * @param properties Extra properties
     * @return Return the component or ScreenField that is created for this field.
     */
    public ScreenComponent setupDefaultView(ScreenLoc itsLocation, ComponentParent targetScreen, Convert converter, int iDisplayFieldDesc, Map<String, Object> properties)
    {
        return createScreenComponent(ScreenModel.POPUP_BOX, itsLocation, targetScreen, this, iDisplayFieldDesc, properties);
    }

}
