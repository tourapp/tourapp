/**
  * @(#)UpdateTrxDescHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.screen.trx;

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
import com.tourapp.tour.genled.db.*;

/**
 *  UpdateTrxDescHandler - Update the detail transaction cache fields when the desc file changes..
 */
public class UpdateTrxDescHandler extends FileListener
{
    protected TrxGroup m_recTrxGroup = null;
    protected TrxStatus m_recTrxStatus = null;
    /**
     * Default constructor.
     */
    public UpdateTrxDescHandler()
    {
        super();
    }
    /**
     * UpdateTrxDescHandler Method.
     */
    public UpdateTrxDescHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        m_recTrxGroup = null;
        m_recTrxStatus = null;
        super.init(record);
    }
    /**
     * Free Method.
     */
    public void free()
    {
        if (m_recTrxStatus != null)
            m_recTrxStatus.free();
        m_recTrxStatus = null;
        if (m_recTrxGroup != null)
            m_recTrxGroup.free();
        m_recTrxGroup = null;
        super.free();
    }
    /**
     * Clone Method.
     */
    public Object clone()
    {
        return new UpdateTrxDescHandler(null);
    }
    /**
     * Called when a change is the record status is about to happen/has happened.
     * @param field If this file change is due to a field, this is the field.
     * @param iChangeType The type of change that occurred.
     * @param bDisplayOption If true, display any changes.
     * @return an error code.
     * ADD_TYPE - Before a write.
     * UPDATE_TYPE - Before an update.
     * DELETE_TYPE - Before a delete.
     * AFTER_UPDATE_TYPE - After a write or update.
     * LOCK_TYPE - Before a lock.
     * SELECT_TYPE - After a select.
     * DESELECT_TYPE - After a deselect.
     * MOVE_NEXT_TYPE - After a move.
     * AFTER_REQUERY_TYPE - Record opened.
     * SELECT_EOF_TYPE - EOF Hit.
     */
    public int doRecordChange(FieldInfo field, int iChangeType, boolean bDisplayOption)
    {
        if ((iChangeType == DBConstants.UPDATE_TYPE)
            || (iChangeType == DBConstants.DELETE_TYPE))
        {
            TrxDesc recTrxDesc = (TrxDesc)this.getOwner();
            RecordOwner recordOwner = recTrxDesc.findRecordOwner();
            try {
                // Update the transaction status file
                if (m_recTrxStatus == null)
                {
                    m_recTrxStatus = new TrxStatus(recordOwner);
                    m_recTrxStatus.addListener(new SubFileFilter(recTrxDesc));
                    m_recTrxStatus.addListener(new UpdateTrxStatusHandler(null, recTrxDesc));
                    if (recordOwner != null)
                        recordOwner.removeRecord(m_recTrxStatus);
                }
        
                m_recTrxStatus.close();
                while (m_recTrxStatus.hasNext())
                {
                    m_recTrxStatus.next();
                    m_recTrxStatus.edit();
                    m_recTrxStatus.getField(TrxStatus.DESC_CODE).moveFieldToThis(recTrxDesc.getField(TrxDesc.DESC_CODE));
        
                    Record recTrxSystem = ((ReferenceField)recTrxDesc.getField(TrxDesc.TRX_SYSTEM_ID)).getReference();
                    if (recTrxSystem != null)
                    {
                        m_recTrxStatus.getField(TrxStatus.SYSTEM_CODE).moveFieldToThis(recTrxSystem.getField(TrxSystem.SYSTEM_CODE));
                        m_recTrxStatus.getField(TrxStatus.TRX_SYSTEM_ID).moveFieldToThis(recTrxSystem.getField(TrxSystem.ID));
                    }
        
                    if (iChangeType == DBConstants.UPDATE_TYPE)
                        m_recTrxStatus.set();
                    else if (iChangeType == DBConstants.DELETE_TYPE)
                        m_recTrxStatus.remove();
                }
        
                // Update the transaction group file
                if (m_recTrxGroup == null)
                {
                    m_recTrxGroup = new TrxGroup(recordOwner);
                    m_recTrxGroup.addListener(new SubFileFilter(recTrxDesc));
                    m_recTrxGroup.addListener(new UpdateTrxGroupHandler(null, recTrxDesc));
                    if (recordOwner != null)
                        recordOwner.removeRecord(m_recTrxGroup);
                }
        
                m_recTrxGroup.close();
                while (m_recTrxGroup.hasNext())
                {
                    m_recTrxGroup.next();
                    m_recTrxGroup.edit();
                    m_recTrxGroup.getField(TrxGroup.DESC_CODE).moveFieldToThis(recTrxDesc.getField(TrxDesc.DESC_CODE));
        
                    Record recTrxSystem = ((ReferenceField)recTrxDesc.getField(TrxDesc.TRX_SYSTEM_ID)).getReference();
                    if (recTrxSystem != null)
                    {
                        m_recTrxGroup.getField(TrxGroup.SYSTEM_CODE).moveFieldToThis(recTrxSystem.getField(TrxSystem.SYSTEM_CODE));
                        m_recTrxGroup.getField(TrxGroup.TRX_SYSTEM_ID).moveFieldToThis(recTrxSystem.getField(TrxSystem.ID));
                    }
        
                    if (iChangeType == DBConstants.UPDATE_TYPE)
                        m_recTrxGroup.set();
                    else if (iChangeType == DBConstants.DELETE_TYPE)
                        m_recTrxGroup.remove();
                }
            } catch (DBException ex) {
                ex.printStackTrace();
            }
        }
        return super.doRecordChange(field, iChangeType, bDisplayOption);
    }

}
