/**
  * @(#)AddNewMcoDistHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctrec.screen.mco.dist;

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
import com.tourapp.tour.acctrec.screen.cash.dist.*;
import com.tourapp.tour.acctrec.db.*;

/**
 *  AddNewMcoDistHandler - Add new Mco distribution record.
 */
public class AddNewMcoDistHandler extends AddNewCashDistHandler
{
    /**
     * Default constructor.
     */
    public AddNewMcoDistHandler()
    {
        super();
    }
    /**
     * AddNewMcoDistHandler Method.
     */
    public AddNewMcoDistHandler(Record record)
    {
        this();
        this.init(record);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record)
    {
        super.init(record);
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
        int iErrorCode = super.doRecordChange(field, iChangeType, bDisplayOption);
        if (iErrorCode != DBConstants.NORMAL_RETURN)
            return iErrorCode;
        if (!this.getOwner().getTableNames(false).equalsIgnoreCase(Mco.MCO_FILE))
            return DBConstants.NORMAL_RETURN;   // If this is being overidden, don't do rest of code.
        switch (iChangeType)
        {
            case DBConstants.AFTER_ADD_TYPE:
                BaseField fldBookingID = this.getOwner().getField(Mco.BOOKING_ID);
                if (!fldBookingID.isNull())
                {
                    Object bookmark = this.getOwner().getLastModified(DBConstants.BOOKMARK_HANDLE);
                    if (bookmark != null)
                    {
                        try {
                            if (m_recBankTrxBatchDist == null)
                            {
                                RecordOwner recordOwner = this.getOwner().findRecordOwner();
                                m_recBankTrxBatchDist = new McoBatchDist(recordOwner);
                                if (recordOwner != null)
                                    recordOwner.removeRecord(m_recBankTrxBatchDist);
                            }
                            m_recBankTrxBatchDist.addNew();
                            m_recBankTrxBatchDist.getField(McoBatchDist.BANK_TRX_BATCH_DETAIL_ID).setData(bookmark);
                            m_recBankTrxBatchDist.getField(McoBatchDist.BOOKING_ID).moveFieldToThis(fldBookingID);
                            m_recBankTrxBatchDist.getField(McoBatchDist.AMOUNT).moveFieldToThis(this.getOwner().getField(Mco.NET));
                            m_recBankTrxBatchDist.add();
                        } catch (DBException ex)    {
                            ex.printStackTrace();
                        }
                    }
                }
            break;
        }
        return iErrorCode;
    }

}
