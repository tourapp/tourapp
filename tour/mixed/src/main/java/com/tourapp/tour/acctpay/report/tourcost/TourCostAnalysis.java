/**
 * @(#)TourCostAnalysis.
 * Copyright © 2011 tourapp.com. All rights reserved.
 */
package com.tourapp.tour.acctpay.report.tourcost;

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
import org.jbundle.base.screen.model.report.*;
import com.tourapp.tour.acctpay.db.*;
import com.tourapp.tour.base.db.*;
import com.tourapp.tour.acctpay.report.*;
import com.tourapp.tour.booking.db.*;

/**
 *  TourCostAnalysis - Tour cost analysis report.
 */
public class TourCostAnalysis extends ReportScreen
{
    /**
     * Default constructor.
     */
    public TourCostAnalysis()
    {
        super();
    }
    /**
     * Constructor.
     * @param record The main record for this screen.
     * @param itsLocation The location of this component within the parent.
     * @param parentScreen The parent screen.
     * @param fieldConverter The field this screen field is linked to.
     * @param iDisplayFieldDesc Do I display the field desc?
     * @param properties Addition properties to pass to the screen.
     */
    public TourCostAnalysis(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        this();
        this.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Initialize class fields.
     */
    public void init(Record record, ScreenLocation itsLocation, BasePanel parentScreen, Converter fieldConverter, int iDisplayFieldDesc, Map<String,Object> properties)
    {
        super.init(record, itsLocation, parentScreen, fieldConverter, iDisplayFieldDesc, properties);
    }
    /**
     * Get the screen display title.
     */
    public String getTitle()
    {
        return "Tour cost analysis report";
    }
    /**
     * Override this to open the main file.
     * <p />You should pass this record owner to the new main file (ie., new MyNewTable(thisRecordOwner)).
     * @return The new record.
     */
    public Record openMainRecord()
    {
        return new ApTrx(this);
    }
    /**
     * Override this to open the other files in the query.
     */
    public void openOtherRecords()
    {
        super.openOtherRecords();
        new Tour(this);
        new PaymentHistory(this);
    }
    /**
     * Add the screen fields.
     * Override this to create (and return) the screen record for this recordowner.
     * @return The screen record.
     */
    public Record addScreenRecord()
    {
        return new CostAnalysisScreenRecord(this);
    }
    /**
     * Add all the screen listeners.
     */
    public void addListeners()
    {
        super.addListeners();
        this.getRecord(ApTrx.kApTrxFile).setKeyArea(ApTrx.kTourIDKey);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTourID).addListener(new ReadSecondaryHandler(this.getRecord(Tour.kTourFile)));
        
        this.getRecord(ApTrx.kApTrxFile).addListener(new CompareFileFilter(this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTourID), this.getScreenRecord().getField(CostAnalysisScreenRecord.kTourID), "=", null, true));
        this.getRecord(ApTrx.kApTrxFile).addListener(new FilterApTrxDateRange(ApTrx.kDepartureDate, this.getScreenRecord().getField(ApReportScreenRecord.kStartDeparture), this.getScreenRecord().getField(ApReportScreenRecord.kEndDeparture)));
        
        Record recPaymentHistory = this.getRecord(PaymentHistory.kPaymentHistoryFile);
        Record recApTrx = this.getRecord(ApTrx.kApTrxFile);
        recPaymentHistory.addListener(new SubFileFilter(recApTrx));
        recApTrx.addListener(new RecountOnValidHandler(recPaymentHistory));
        recPaymentHistory.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kPayments), PaymentHistory.kAmountLocal, false, true));
        
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kSubTotalDepEstUSD), ApTrx.kDepartureEstimateLocal, true, true, true));
        recApTrx.addListener(new InvoiceAmountSubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kSubTotalInvoiceUSD), ApTrx.kInvoiceLocal, true, true, true));
        recPaymentHistory.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kSubTotalPaymentsUSD), PaymentHistory.kAmountLocal, true, true, true));
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kSubTotalBalUSD), ApTrx.kInvoiceBalanceLocal, true, true, true));
        
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kTotalBalance), ApTrx.kDepartureEstimateLocal, true, true));
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kTotalInvoiceUSD), ApTrx.kInvoiceLocal, true, true));
        recPaymentHistory.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kTotalPaymentsUSD), PaymentHistory.kAmountLocal, true, true));
        recApTrx.addListener(new SubCountHandler(this.getScreenRecord().getField(CostAnalysisScreenRecord.kTotalUSDBal), ApTrx.kInvoiceBalanceLocal, true, true));
    }
    /**
     * Add the toolbars that belong with this screen.
     * @return The new toolbar.
     */
    public ToolScreen addToolbars()
    {
        return new TourCostAnalysisToolbar(null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC);
    }
    /**
     * SetupSFields Method.
     */
    public void setupSFields()
    {
        new TourCostAnalysisTourHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.HEADING_SCREEN, null);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kCode).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kTrxStatusID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kVendorID).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDescription).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kStartServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kEndServiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceDate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceNo).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimate).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceAmount).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalance).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kDepartureEstimateLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        this.getRecord(ApTrx.kApTrxFile).getField(ApTrx.kInvoiceBalanceLocal).setupDefaultView(this.getNextLocation(ScreenConstants.NEXT_LOGICAL, ScreenConstants.ANCHOR_DEFAULT), this, ScreenConstants.DEFAULT_DISPLAY);
        new TourCostAnalysisTourFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC | HtmlConstants.FOOTING_SCREEN, null);
    }
    /**
     * Get the footing for this report.
     */
    public BaseScreen addReportFooting()
    {
        return new TourCostAnalysisFooting(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }
    /**
     * Get the Heading for this report.
     */
    public BaseScreen addReportHeading()
    {
        return new TourCostAnalysisHeading(null, null, this, null, ScreenConstants.DONT_DISPLAY_FIELD_DESC, null);
    }

}
