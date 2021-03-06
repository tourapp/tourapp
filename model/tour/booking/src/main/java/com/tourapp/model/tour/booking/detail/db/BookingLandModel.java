/**
  * @(#)BookingLandModel.
  * Copyright © 2012 tourapp.com. All rights reserved.
  * GPL3 Open Source Software License.
  */
package com.tourapp.model.tour.booking.detail.db;

import com.tourapp.model.tour.booking.detail.db.*;

public interface BookingLandModel extends BookingDetailModel
{

    //public static final String ID = ID;
    //public static final String LAST_CHANGED = LAST_CHANGED;
    //public static final String DELETED = DELETED;
    //public static final String BOOKING_ID = BOOKING_ID;
    //public static final String BOOKING_PAX_ID = BOOKING_PAX_ID;
    //public static final String MODULE_ID = MODULE_ID;
    //public static final String TOUR_HEADER_DETAIL_ID = TOUR_HEADER_DETAIL_ID;
    //public static final String TOUR_HEADER_OPTION_ID = TOUR_HEADER_OPTION_ID;
    //public static final String MODULE_START_DATE = MODULE_START_DATE;
    //public static final String DESCRIPTION = DESCRIPTION;
    //public static final String PRODUCT_TYPE = PRODUCT_TYPE;
    //public static final String REMOTE_REFERENCE_NO = REMOTE_REFERENCE_NO;
    //public static final String PRODUCT_TYPE_ID = PRODUCT_TYPE_ID;
    //public static final String DETAIL_DATE = DETAIL_DATE;
    //public static final String GMT_DATE = GMT_DATE;
    //public static final String GMT_OFFSET = GMT_OFFSET;
    //public static final String PRODUCT_ID = PRODUCT_ID;
    //public static final String RATE_ID = RATE_ID;
    //public static final String CLASS_ID = CLASS_ID;
    //public static final String MARKUP_FROM_LAST = MARKUP_FROM_LAST;
    //public static final String VENDOR_ID = VENDOR_ID;
    //public static final String TOUR_ID = TOUR_ID;
    //public static final String AP_TRX_ID = AP_TRX_ID;
    //public static final String PRICING_DETAIL_KEY = PRICING_DETAIL_KEY;
    //public static final String TOTAL_COST = TOTAL_COST;
    //public static final String EXCHANGE = EXCHANGE;
    //public static final String CURRENCY_CODE = CURRENCY_CODE;
    //public static final String TOTAL_COST_LOCAL = TOTAL_COST_LOCAL;
    //public static final String TOTAL_PRICE_LOCAL = TOTAL_PRICE_LOCAL;
    //public static final String INFO_MESSAGE_TRANSPORT_ID = INFO_MESSAGE_TRANSPORT_ID;
    //public static final String INFO_STATUS_ID = INFO_STATUS_ID;
    //public static final String INFO_REQUEST_KEY = INFO_REQUEST_KEY;
    //public static final String INFO_STATUS_REQUEST = INFO_STATUS_REQUEST;
    //public static final String COST_MESSAGE_TRANSPORT_ID = COST_MESSAGE_TRANSPORT_ID;
    //public static final String COST_STATUS_ID = COST_STATUS_ID;
    //public static final String COST_REQUEST_KEY = COST_REQUEST_KEY;
    //public static final String COST_STATUS_REQUEST = COST_STATUS_REQUEST;
    //public static final String INVENTORY_MESSAGE_TRANSPORT_ID = INVENTORY_MESSAGE_TRANSPORT_ID;
    //public static final String INVENTORY_STATUS_ID = INVENTORY_STATUS_ID;
    //public static final String INVENTORY_REQUEST_KEY = INVENTORY_REQUEST_KEY;
    //public static final String INVENTORY_STATUS_REQUEST = INVENTORY_STATUS_REQUEST;
    //public static final String PRODUCT_MESSAGE_TRANSPORT_ID = PRODUCT_MESSAGE_TRANSPORT_ID;
    //public static final String PRODUCT_STATUS_ID = PRODUCT_STATUS_ID;
    //public static final String PRODUCT_REQUEST_KEY = PRODUCT_REQUEST_KEY;
    //public static final String PRODUCT_STATUS_REQUEST = PRODUCT_STATUS_REQUEST;
    //public static final String REMOTE_BOOKING_NO = REMOTE_BOOKING_NO;
    //public static final String ACK_DAYS = ACK_DAYS;
    //public static final String DETAIL_END_DATE = DETAIL_END_DATE;
    //public static final String GMT_END_DATE = GMT_END_DATE;
    //public static final String MEAL_SUMMARY = MEAL_SUMMARY;
    //public static final String STATUS_SUMMARY = STATUS_SUMMARY;
    //public static final String ITINERARY_DESC = ITINERARY_DESC;
    //public static final String PROPERTIES = PROPERTIES;
    //public static final String ERROR_PROPERTIES = ERROR_PROPERTIES;
    //public static final String PP_COST = PP_COST;
    //public static final String PP_PRICE_LOCAL = PP_PRICE_LOCAL;
    //public static final String NIGHTS = NIGHTS;
    //public static final String MEAL_PLAN_1ID = MEAL_PLAN_1ID;
    //public static final String MEAL_PLAN_1_QTY = MEAL_PLAN_1_QTY;
    //public static final String MEAL_PLAN_1_DAYS = MEAL_PLAN_1_DAYS;
    //public static final String MEAL_PLAN_2ID = MEAL_PLAN_2ID;
    //public static final String MEAL_PLAN_2_QTY = MEAL_PLAN_2_QTY;
    //public static final String MEAL_PLAN_2_DAYS = MEAL_PLAN_2_DAYS;
    //public static final String MEAL_PLAN_3ID = MEAL_PLAN_3ID;
    //public static final String MEAL_PLAN_3_QTY = MEAL_PLAN_3_QTY;
    //public static final String MEAL_PLAN_3_DAYS = MEAL_PLAN_3_DAYS;
    //public static final String MEAL_PLAN_4ID = MEAL_PLAN_4ID;
    //public static final String MEAL_PLAN_4_QTY = MEAL_PLAN_4_QTY;
    //public static final String MEAL_PLAN_4_DAYS = MEAL_PLAN_4_DAYS;
    //public static final String SINGLE_PAX = SINGLE_PAX;
    //public static final String DOUBLE_PAX = DOUBLE_PAX;
    //public static final String TRIPLE_PAX = TRIPLE_PAX;
    //public static final String QUAD_PAX = QUAD_PAX;
    //public static final String CHILDREN = CHILDREN;
    //public static final String SINGLE_COST = SINGLE_COST;
    //public static final String DOUBLE_COST = DOUBLE_COST;
    //public static final String TRIPLE_COST = TRIPLE_COST;
    //public static final String QUAD_COST = QUAD_COST;
    //public static final String CHILDREN_COST = CHILDREN_COST;
    //public static final String ROOM_COST = ROOM_COST;
    //public static final String MEAL_COST = MEAL_COST;
    //public static final String ROOM_COST_LOCAL = ROOM_COST_LOCAL;
    //public static final String MEAL_COST_LOCAL = MEAL_COST_LOCAL;
    //public static final String VARIES_CODE = VARIES_CODE;
    //public static final String VARIES_QTY = VARIES_QTY;
    //public static final String VARIES_COST = VARIES_COST;
    //public static final String PMC_CUTOFF = PMC_CUTOFF;
    //public static final String PMC_COST = PMC_COST;
    //public static final String SIC_COST = SIC_COST;
    //public static final String BOOKING_AIR_HEADER_ID = BOOKING_AIR_HEADER_ID;
    //public static final String GMT_TIME = GMT_TIME;
    //public static final String ETD = ETD;
    //public static final String XO = XO;
    //public static final String CITY_CODE = CITY_CODE;
    //public static final String CITY_DESC = CITY_DESC;
    //public static final String AIRLINE_ID = AIRLINE_ID;
    //public static final String CARRIER = CARRIER;
    //public static final String FLIGHT_NO = FLIGHT_NO;
    //public static final String FLIGHT_CLASS = FLIGHT_CLASS;
    //public static final String TO_CITY_CODE = TO_CITY_CODE;
    //public static final String TO_CITY_DESC = TO_CITY_DESC;
    //public static final String ARRIVE_TIME = ARRIVE_TIME;
    //public static final String ADD_DAYS = ADD_DAYS;
    //public static final String ARC_STATUS = ARC_STATUS;
    //public static final String FARE_BASIS = FARE_BASIS;
    //public static final String START_DATE = START_DATE;
    //public static final String END_DATE = END_DATE;
    //public static final String ALLOW = ALLOW;
    //public static final String DET_FARE = DET_FARE;
    //public static final String SEGMENT_CONF_NO = SEGMENT_CONF_NO;
    //public static final String SEGMENT_CONFIRMED_BY = SEGMENT_CONFIRMED_BY;
    //public static final String COUPON = COUPON;
    //public static final String SEAT = SEAT;
    //public static final String GATE = GATE;
    //public static final String SEAT_PREF = SEAT_PREF;
    //public static final String SMOKING = SMOKING;
    //public static final String MEALS = MEALS;
    //public static final String DAYS = DAYS;
    //public static final String QUANTITY = QUANTITY;
    //public static final String ASK_FOR_ANSWER = ASK_FOR_ANSWER;
    //public static final String ALWAYS_RESOLVE = ALWAYS_RESOLVE;
    //public static final String PRICING_TYPE_ID = PRICING_TYPE_ID;
    public static final String BOOKING_LAND_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.land.BookingLandScreen";
    public static final String BOOKING_LAND_GRID_SCREEN_CLASS = "com.tourapp.tour.booking.entry.detail.land.BookingLandGridScreen";

    public static final String BOOKING_LAND_FILE = "BookingDetail";
    public static final String THIN_CLASS = "com.tourapp.thin.tour.booking.detail.db.BookingLand";
    public static final String THICK_CLASS = "com.tourapp.tour.booking.detail.db.BookingLand";

}
