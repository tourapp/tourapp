/**
  * @(#)UpdateOverrideAcctDetailHandler.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.acctpay.air.oride;

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
import org.jbundle.base.screen.model.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.genled.db.*;
import com.tourapp.tour.acctpay.db.event.*;

/**
 *  UpdateOverrideAcctDetailHandler - .
 */
public class UpdateOverrideAcctDetailHandler extends UpdateApTrxHandler
{
    /**
     * Default constructor.
     */
    public UpdateOverrideAcctDetailHandler()
    {
        super();
    }
    /**
     * UpdateOverrideAcctDetailHandler Method.
     */
    public UpdateOverrideAcctDetailHandler(Record record)
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
     * Get the transaction date.
     * @return The transaction date for this type of transaction.
     */
    public BaseField getTrxDate()
    {
        Record recTicketTrx = this.getOwner();
        return recTicketTrx.getField(TicketTrx.OVERRIDE_PAID_DATE);
    }
    /**
     * Get the Credit Account field.
     * @return The credit account field.
     */
    public ReferenceField getCrAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.OVERRIDE_REC_ACCOUNT_ID);
    }
    /**
     * Get the Debit Account field.
     * @return The debit account field.
     */
    public ReferenceField getDrAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.OVERRIDE_SUMM_ACCOUNT_ID);
    }
    /**
     * Get the differential account (Cost Over/Under) for this type of trx.
     * @return The field that contains the differential account.
     */
    public ReferenceField getDiffAccount()
    {
        return (ReferenceField)this.getOwner().getRecordOwner().getRecord(ApControl.AP_CONTROL_FILE).getField(ApControl.OVERRIDE_GAIN_LOSS_ACCOUNT_ID);
    }
    /**
     * Get the transaction amount for this type of transaction.
     * @param fldTypicalBalance The typical balance field (Debit/Credit/none).
     * @return The transaction amount.
     */
    public double getTrxAmount(BaseField fldTypicalBalance)
    {
        double dOverrideReceived = this.getOwner().getField(TicketTrx.OVERRIDE_PAID).getValue();
        double dOverrideAmount = this.getOwner().getField(TicketTrx.OVERRIDE_AMOUNT).getValue();
        if (Account.DEBIT.equalsIgnoreCase(fldTypicalBalance.getString()))
            return dOverrideReceived;
        else if (Account.CREDIT.equalsIgnoreCase(fldTypicalBalance.getString()))
            return dOverrideAmount;
        else
            return dOverrideAmount - dOverrideReceived;
    }
    /**
     * Is this a new transaction (or a modification of a current transaction).
     * If it is not new, the system will calculate the current posting and do an adjusting entry.
     */
    public boolean isNewTrx(int iChangeType)
    {
        if (this.getOwner().getField(ApTrx.TRX_STATUS_ID).getValue() == this.getTrxStatusID(TicketTrx.OVER_RIDE_PAID))
            return false;
        return super.isNewTrx(iChangeType);
    }
    /**
     * Utility to get the trx status ID for this code.
     */
    public int getTrxStatusID(String strTrxStatus)
    {
        return this.getTrxStatus().getTrxStatusID(TransactionType.AIR, TicketTrx.TICKET_TRX_FILE, strTrxStatus);
    }

}
