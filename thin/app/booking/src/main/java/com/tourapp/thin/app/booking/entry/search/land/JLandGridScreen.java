/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.land;

/**
 * OrderEntry.java:   Applet
 *  Copyright © 2012 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.db.FieldTable;
import org.jbundle.thin.base.screen.AbstractThinTableModel;
import org.jbundle.thin.base.screen.JBaseScreen;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;

import com.tourapp.thin.app.booking.entry.search.base.JProductGridScreen;

/**
 * Main Class for applet OrderEntry
 */
public class JLandGridScreen extends JProductGridScreen
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JLandGridScreen()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JLandGridScreen(Object parent, Object model)
    {
        this();
        this.init(parent, model);
    }
    /**
     * The init() method is called first.
     */
    public void init(Object parent, Object model)
    {
        super.init(parent, model);
    }
    /**
     * Create a grid screen for this form.
     * @param record the (optional) record for this screen.
     * @return The new grid screen.
     */
    public JBaseScreen createMaintScreen(FieldList record)
    {
        return new JLandScreen(this.getParentObject(), record);
    }
    /**
     * Get the remote table name (For remote table lookup).
     * @return The table name.
     */
    public String getRemoteTableName()
    {
        return ProductConstants.LAND;
    }
    /**
     * Setup the grid model.
     * @param table The table.
     * @return The grid model.
     */
    public AbstractThinTableModel setupGridModel(FieldTable table)
    {
        return new LandGridModel(table);
    }
}
