/*
 * Copyright © 2012 jbundle.org. All rights reserved.
 */
package com.tourapp.thin.app.booking.entry.search.car;

/**
 * OrderEntry.java:   Applet
 *  Copyright � 1997 tourgeek.com. All rights reserved.
 *  
 *  @author Don Corley don@donandann.com
 *  @version 1.0.0.
 */
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;

import javax.swing.JComboBox;
import javax.swing.JComponent;

import org.jbundle.thin.base.db.FieldList;
import org.jbundle.thin.base.remote.RemoteSession;
import org.jbundle.thin.base.screen.BaseApplet;
import org.jbundle.thin.base.screen.cal.popup.ProductConstants;

import com.tourapp.thin.app.booking.entry.search.SearchConstants;
import com.tourapp.thin.app.booking.entry.search.base.JProductSearchPane;
import com.tourapp.thin.tour.product.car.db.CarClass;

/**
 * Main Class for applet OrderEntry
 */
public class JCarSearchPane extends JProductSearchPane
{
	private static final long serialVersionUID = 1L;

	/**
     *  OrderEntry Class Constructor.
     */
    public JCarSearchPane()
    {
        super();
    }
    /**
     *  OrderEntry Class Constructor.
     */
    public JCarSearchPane(Object parent, RemoteSession remoteSession)
    {
        this();
        this.init(parent, remoteSession);
    }
    /**
     * The init() method is called by the AWT when an applet is first loaded or
     * reloaded.  Override this method to perform whatever initialization your
     * applet needs, such as initializing data structures, loading images or
     * fonts, creating frame windows, setting the layout manager, or adding UI
     * components.
     */
    public void init(Object parent, RemoteSession remoteSession)
    {
        super.init(parent, remoteSession);
        BaseApplet applet = this.getBaseApplet();

        JComponent location = this.buildLookupEdit(applet, m_remoteSession, null, null, null);
        this.add(location);

        location = this.buildLookupEdit(applet, m_remoteSession, applet.getString(SearchConstants.LOCATION_TO), SearchConstants.LOCATION_TO, null);
        this.add(location);

        FieldList recCarClass = new CarClass(this);
        this.addFieldList(recCarClass);
        JComboBox popup = this.buildLinkedPopup(applet, m_remoteSession, recCarClass, applet.getString(SearchConstants.CAR_CLASS), CarClass.DESCRIPTION, SearchConstants.CAR_CLASS, CarClass.ID, null);
        this.add(popup);
    }
    /**
     *  For the action listener (menu commands).
     */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() instanceof JComponent)
        {
            String strButtonName = ((JComponent)evt.getSource()).getName();
            if (SearchConstants.CAR_CLASS.equals(strButtonName))
            {
                this.popupPropChange(evt, strButtonName);
                return;
            }
        }
        super.actionPerformed(evt);
    }
    /**
     * Property change (listen for date change from JLocationScreen).
     */
    public void propertyChange(PropertyChangeEvent evt)
    {
        String strPropertyName = evt.getPropertyName();
        if (SearchConstants.CAR_CLASS.equalsIgnoreCase(strPropertyName))
        {
            if (evt.getSource() != this)
                this.requeryRemoteSession();
        }
        else
            super.propertyChange(evt);
    }
    /**
     * Get the remote table name.
     */
    public String getRemoteTableName()
    {
        return ProductConstants.CAR;
    }
}
