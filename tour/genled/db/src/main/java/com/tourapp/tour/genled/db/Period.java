/**
  * @(#)Period.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.tour.genled.db;

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
import java.util.*;
import com.tourapp.model.tour.genled.db.*;

/**
 *  Period - Periods.
 */
public class Period extends VirtualRecord
     implements PeriodModel
{
    private static final long serialVersionUID = 1L;

    protected Date m_lastEndDate = null;
    protected Date m_lastEndTargetDate = null;
    protected Date m_lastStartDate = null;
    protected Date m_lastStartTargetDate = null;
    /**
     * Default constructor.
     */
    public Period()
    {
        super();
    }
    /**
     * Constructor.
     */
    public Period(RecordOwner screen)
    {
        this();
        this.init(screen);
    }
    /**
     * Initialize class fields.
     */
    public void init(RecordOwner screen)
    {
        m_lastEndDate = null;
        m_lastEndTargetDate = null;
        m_lastStartDate = null;
        m_lastStartTargetDate = null;
        super.init(screen);
    }
    /**
     * Get the table name.
     */
    public String getTableNames(boolean bAddQuotes)
    {
        return (m_tableName == null) ? Record.formatTableNames(PERIOD_FILE, bAddQuotes) : super.getTableNames(bAddQuotes);
    }
    /**
     * Get the name of a single record.
     */
    public String getRecordName()
    {
        return "Accounting period";
    }
    /**
     * Get the Database Name.
     */
    public String getDatabaseName()
    {
        return "genled";
    }
    /**
     * Is this a local (vs remote) file?.
     */
    public int getDatabaseType()
    {
        return DBConstants.LOCAL | DBConstants.USER_DATA;
    }
    /**
     * Add this field in the Record's field sequence.
     */
    public BaseField setupField(int iFieldSeq)
    {
        BaseField field = null;
        //if (iFieldSeq == 0)
        //{
        //  field = new CounterField(this, ID, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 1)
        //{
        //  field = new RecordChangedField(this, LAST_CHANGED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        //  field.setHidden(true);
        //}
        //if (iFieldSeq == 2)
        //{
        //  field = new BooleanField(this, DELETED, Constants.DEFAULT_FIELD_LENGTH, null, new Boolean(false));
        //  field.setHidden(true);
        //}
        if (iFieldSeq == 3)
        {
            field = new DateField(this, END_PERIOD, Constants.DEFAULT_FIELD_LENGTH, null, null);
            field.setNullable(false);
        }
        if (iFieldSeq == 4)
            field = new DateField(this, PERIOD_CLOSED, Constants.DEFAULT_FIELD_LENGTH, null, null);
        if (field == null)
            field = super.setupField(iFieldSeq);
        return field;
    }
    /**
     * Add this key area description to the Record.
     */
    public KeyArea setupKey(int iKeyArea)
    {
        KeyArea keyArea = null;
        if (iKeyArea == 0)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, ID_KEY);
            keyArea.addKeyField(ID, DBConstants.ASCENDING);
        }
        if (iKeyArea == 1)
        {
            keyArea = this.makeIndex(DBConstants.UNIQUE, END_PERIOD_KEY);
            keyArea.addKeyField(END_PERIOD, DBConstants.ASCENDING);
        }
        if (keyArea == null)
            keyArea = super.setupKey(iKeyArea);     
        return keyArea;
    }
    /**
     * AddMasterListeners Method.
     */
    public void addMasterListeners()
    {
        this.setKeyArea(Period.END_PERIOD_KEY);   // Default order
        super.addMasterListeners();
    }
    /**
     * nding date for this target date.
     */
    public Date getPeriodEndDate(Date targetDate)
    {
        Date entryDate = null;
        if (targetDate != null)
        {
            if (targetDate.equals(m_lastEndTargetDate))
                return m_lastEndDate;
        }
        else
        {
            if (m_lastEndTargetDate == null) if (m_lastEndDate != null)
                return m_lastEndDate;
        }   
        m_lastEndTargetDate = targetDate;
        
        if ((targetDate == null) || (targetDate.getTime() == 0))
            targetDate = new Date();
        
        //      criteria = "[EndPeriod] >= #" & targetDate & "# And [PeriodClosed] = 0"
        try   {
            this.close();
        // Move this field as a virtual field
            DateField fldDate = new DateField(null, "EndPeriod", DBConstants.DEFAULT_FIELD_LENGTH, "Period end", null);
            fldDate.setDateTime(targetDate, DBConstants.DISPLAY, DBConstants.SCREEN_MOVE);
            FileListener behavior1 = new CompareFileFilter(Period.END_PERIOD, fldDate, ">=", null, false);
            FileListener behavior2 = new CompareFileFilter(Period.PERIOD_CLOSED, (String)null, "=", null, true);
            this.addListener(behavior1);
            this.addListener(behavior2);
            if (this.hasNext())
            {
                this.next();
                entryDate = ((DateField)this.getField(Period.END_PERIOD)).getDateTime();
            }
            else
            {   // Past last date, use last day of current month.
                entryDate = targetDate;
                Calendar calendar = DateTimeField.m_calendar;
                calendar.setTime(entryDate);
                int iMonth = calendar.get(Calendar.MONTH);
                if (iMonth == Calendar.DECEMBER)
                {
                    calendar.set(Calendar.MONTH, Calendar.JANUARY);
                    calendar.add(Calendar.YEAR, +1);
                }
                else
                    calendar.add(Calendar.MONTH, +1);
                calendar.set(Calendar.DATE, 1);
                calendar.add(Calendar.DATE, -1);
                fldDate.setCalendar(calendar, DBConstants.DONT_DISPLAY, DBConstants.SCREEN_MOVE);
                entryDate = fldDate.getDateTime();
            }
            this.removeListener(behavior1, true);
            this.removeListener(behavior2, true);
            fldDate.free();
        } catch (DBException ex)    {
            entryDate = targetDate;
        }
        
        m_lastEndDate = entryDate;
        
        return entryDate;
    }
    /**
     * Get one day after the previous period (starting date of this period).
     */
    public Date getPeriodStartDate(Date targetDate)
    {
        Date entryDate = null;
        if (targetDate != null)
        {
            if (targetDate.equals(m_lastStartTargetDate))
                return m_lastStartDate;
        }
        else
        {
            if (m_lastStartTargetDate == null) if (m_lastStartDate != null)
                return m_lastStartDate;
        }   
        m_lastStartTargetDate = targetDate;
        
        Date endDate = this.getPeriodEndDate(targetDate);
        if ((targetDate == null) || (targetDate.getTime() == 0))
            targetDate = new Date();
        //  criteria = "[EndPeriod] < #" & endDate & "#"
        try   {
            // Make sure your get the largest one.
            this.getKeyArea(Period.END_PERIOD_KEY).getKeyField(DBConstants.MAIN_KEY_FIELD).setKeyOrder(DBConstants.DESCENDING);
            boolean bSuccess = this.seek("<");
            if (!bSuccess)
                entryDate = targetDate;
            else
            {
                Calendar calendar = ((DateTimeField)this.getField(Period.END_PERIOD)).getCalendar();
                calendar.add(Calendar.DATE, 1);
                entryDate = calendar.getTime();
            }
        } catch (DBException ex)    {
            entryDate = targetDate;
        } finally {
            this.getKeyArea(Period.END_PERIOD_KEY).getKeyField(DBConstants.MAIN_KEY_FIELD).setKeyOrder(DBConstants.ASCENDING);
        }
        
        m_lastStartDate = entryDate;
        
        return entryDate;
    }
    /**
     * Set the start and end fields to the current period.
     * @param record The record to set.
     * @param fsStartDateField The field to set to the start period date.
     * @param fsEndDateField The field to set to the end period date.
     * @param datePeriod The date to calc the period for (null for the current date).
     */
    public void setPeriodDefaults(Record record, String fsStartDateField, String fsEndDateField, Date datePeriod)
    {
        if (fsStartDateField != null)
        {
            Date startDate = this.getPeriodStartDate(datePeriod);
            ((DateTimeField)record.getField(fsStartDateField)).setDate(startDate, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
        if (fsEndDateField != null)
        {
            Date endDate = this.getPeriodEndDate(datePeriod);
            ((DateTimeField)record.getField(fsEndDateField)).setDate(endDate, DBConstants.DISPLAY, DBConstants.INIT_MOVE);
        }
    }

}
