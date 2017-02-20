package com.capital.dragon.service;

import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Event;
import com.bloomberglp.blpapi.Message;
import com.bloomberglp.blpapi.MessageIterator;
import com.bloomberglp.blpapi.Request;
import com.bloomberglp.blpapi.Service;
import com.bloomberglp.blpapi.Session;
import com.bloomberglp.blpapi.SessionOptions;
import com.capital.dragon.model.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service("BlpService")
public class BlpServiceImpl implements BlpService {

    private void modifySecurity(Security security, String name, String value) {

        switch (name) {
            case "PX_LAST":
                security.setLast(value);
                break;
            case "BID":
                security.setBid(value);
                break;
            case "ASK":
                security.setAsk(value);
                break;

        }
        if (security instanceof SecurityHistory) {
            SecurityHistory securityHistory = (SecurityHistory) security;
            switch (name) {
                case "OPEN":
                    securityHistory.setOpen(value);
                    break;
                case "date":
                    securityHistory.setDate(value);
                    break;
            }

        }


        if (security instanceof SecurityReference) {
            SecurityReference securityReference = (SecurityReference) security;
            switch (name) {
                case "RTG_MOODY":
                    securityReference.setRTG_MOODY(value);
                    break;
                case "RTG_FITCH":
                    securityReference.setRTG_FITCH(value);
                    break;
                case "RTG_SP_LT_LC_ISSUER_CREDIT":
                    securityReference.setRTG_SP(value);
                    break;
                case "HIGH":
                    securityReference.setHigh(value);
                    break;
                case "LOW":
                    securityReference.setLow(value);
                    break;
                case "CRNCY":
                    securityReference.setCrncy(value);
                    break;
                case "ID_ISIN":
                    securityReference.setIsin(value);
                    break;

                case "HIGH_52WEEK":
                    securityReference.setHigh_52w(value);
                    break;
                case "LOW_52WEEK":
                    securityReference.setLow_52w(value);
                    break;
                case "DS156":
                    securityReference.setDescription(value);
                    break;
                case "DS201":
                    securityReference.setIndustryGroup(value);
                    break;
                case "CHG_PCT_1D":
                    securityReference.setCHG_PCT_1D(value);
                    break;
                case "CHG_PCT_5D":
                    securityReference.setCHG_PCT_5D(value);
                    break;
                case "CHG_PCT_1M":
                    securityReference.setCHG_PCT_1M(value);
                    break;
                case "CHG_PCT_HIGH_52WEEK":
                    securityReference.setCHG_PCT_HIGH_52WEEK(value);
                    break;
                case "CHG_PCT_LOW_52WEEK":
                    securityReference.setCHG_PCT_LOW_52WEEK(value);
                    break;
            }
        }
        if (security instanceof Bond) {
            Bond bond = (Bond) security;
            switch (name) {
                case "MATURITY":
                    bond.setMATURITY(value);
                    break;
                case "CPN":
                    bond.setCoupon(value);
                    break;
                case "YLD_YTM_BID":
                    bond.setYLD_YTM_BID(value);
                    break;
                case "YLD_YTM_ASK":
                    bond.setYLD_YTM_ASK(value);
                    break;
            }

        }

        if (security instanceof Equity) {
            Equity equity = (Equity) security;
            switch (name) {


                case "HIGH_LIFETIME":
                    equity.setHigh_alltime(value);
                    break;

                case "LOW_LIFETIME":
                    equity.setLow_alltime(value);
                    break;
            }
        }
    }

    private List<Security> processReferenceDataResponse(Message msg) throws Exception {
        List<Security> securityList = new ArrayList<>();
        Element securityDataArray = msg.getElement("securityData");
        for (int i = 0; i < securityDataArray.numValues(); ++i) {
            Element securityData = securityDataArray.getValueAsElement(i);

            String secName = securityData.getElement("security").getValueAsString();
            Security security;
            if (secName.contains("Govt")) {
                security = new Bond();

            } else {
                security = new Equity();
            }
            security.setName(secName);
            System.out.println(securityData.getElementAsString("security"));
            Element fieldData = securityData.getElement("fieldData");
            for (int j = 0; j < fieldData.numElements(); ++j) {
                Element field = fieldData.getElement(j);
                modifySecurity(security, field.name().toString(), field.getValueAsString());
                System.out.println(field.name() + " = " +
                        field.getValueAsString());
            }
            System.out.println("\n");
            securityList.add(security);
        }

        return securityList;
    }


    private List<Security> processHistoricalDataResponse(Message msg) throws
            Exception {
        List<Security> securityList = new ArrayList<>();

        Element securityData = msg.getElement("securityData");
        Element fieldDataArray = securityData.getElement("fieldData");
        String secName = securityData.getElement("security").getValueAsString();


        for (int j = 0; j < fieldDataArray.numValues(); ++j) {
            Security security;
            security = new SecurityHistory();
            security.setName(secName);
            Element fieldData = fieldDataArray.getValueAsElement(j);
            for (int k = 0; k < fieldData.numElements(); ++k) {
                Element field = fieldData.getElement(k);
                modifySecurity(security, field.name().toString(), field.getValueAsString());
                System.out.println("\t" + field.name() + " = "
                        + field.getValueAsString());
            }
            securityList.add(security);
        }

        return securityList;
    }

    @Override
    public List<Security> getSecurityListHistoricalInfo(String data1, String data2, List<String> securityList) throws
            Exception {
        List<Security> resultList = new ArrayList<>();
        String serverHost = "localhost";
        int serverPort = 8194;

        SessionOptions sessionOptions = new SessionOptions();
        sessionOptions.setServerHost(serverHost);
        sessionOptions.setServerPort(serverPort);

        System.out.println("Connecting to " + serverHost + ":" + serverPort);
        Session session = new Session(sessionOptions);
        if (!session.start()) {
            System.err.println("Failed to start session.");
            return resultList;
        }
        if (!session.openService("//blp/refdata"))

        {
            System.err.println("Failed to open //blp/refdata");
            return resultList;
        }

        Service refDataService = session.getService("//blp/refdata");
        Request request = refDataService.createRequest("HistoricalDataRequest");

        Element securities = request.getElement("securities");

        for (String sec : securityList) {
            securities.appendValue(sec);

        }

        Element fields = request.getElement("fields");
        fields.appendValue("PX_LAST");
        fields.appendValue("OPEN");
        fields.appendValue("BID");
        fields.appendValue("ASK");
        fields.appendValue("CPN");
        fields.appendValue("DS033");

        request.set("periodicityAdjustment", "ACTUAL");
        request.set("periodicitySelection", "DAILY");
        //request.set("startDate","20161118");
        request.set("startDate", data1);
        request.set("endDate", data2);
        request.set("maxDataPoints", 100);
        request.set("returnEids", true);

        System.out.println("Sending Request: " + request);
        session.sendRequest(request, null);


        return processResponse(session, "history");

    }

    private List<Security> processResponse(Session session, String typeOfQuery) throws Exception {
        List<Security> resultList = new ArrayList<>();
        while (true) {
            Event event = session.nextEvent();
            MessageIterator msgIter = event.messageIterator();
            while (msgIter.hasNext()) {
                Message msg = msgIter.next();
                String msgstr = msg.toString();
                if (msg.hasElement("securityData")) {
                    System.out.println(msgstr);

                    switch (typeOfQuery) {
                        case "history":
                            resultList.addAll(processHistoricalDataResponse(msg));
                            break;
                        case "current":
                            resultList.addAll(processReferenceDataResponse(msg));
                            break;
                    }
                }


            }
            if (event.eventType() == Event.EventType.RESPONSE) {
                break;
            }

        }
        return resultList;

    }

    public List<Security> getSecurityListInfo(List<String> securityList) throws Exception {
        List<Security> resultList = new ArrayList<>();
        String serverHost = "localhost";
        int serverPort = 8194;

        SessionOptions sessionOptions = new SessionOptions();
        sessionOptions.setServerHost(serverHost);
        sessionOptions.setServerPort(serverPort);

        System.out.println("Connecting to " + serverHost + ":" + serverPort);
        Session session = new Session(sessionOptions);
        if (!session.start()) {
            System.err.println("Failed to start session.");
            return resultList;
        }
        if (!session.openService("//blp/refdata"))

        {
            System.err.println("Failed to open //blp/refdata");
            return resultList;
        }

        Service refDataService = session.getService("//blp/refdata");
        Request request = refDataService.createRequest("ReferenceDataRequest");

        Element securities = request.getElement("securities");

        for (String sec : securityList) {
            securities.appendValue(sec);

        }

        Element fields = request.getElement("fields");
        fields.appendValue("PX_LAST");
        fields.appendValue("OPEN");
        fields.appendValue("BID");
        fields.appendValue("ASK");
        fields.appendValue("HIGH");
        fields.appendValue("LOW");
        fields.appendValue("CRNCY");
        fields.appendValue("ID_ISIN");
        fields.appendValue("HIGH_52WEEK");
        fields.appendValue("HIGH_LIFETIME");
        fields.appendValue("LOW_52WEEK");
        fields.appendValue("LOW_LIFETIME");

        fields.appendValue("CPN"); // Coupon
        fields.appendValue("DS156");// Description
        fields.appendValue("DS201");// Industry group

        fields.appendValue("YLD_YTM_BID");
        fields.appendValue("YLD_YTM_ASK");

        fields.appendValue("CHG_PCT_1D");
        fields.appendValue("CHG_PCT_5D");
        fields.appendValue("CHG_PCT_1M");

        fields.appendValue("CHG_PCT_HIGH_52WEEK");
        fields.appendValue("CHG_PCT_LOW_52WEEK");
        fields.appendValue("MATURITY");
        fields.appendValue("RTG_MOODY");
        fields.appendValue("RTG_FITCH");
        fields.appendValue("RTG_SP_LT_LC_ISSUER_CREDIT"); //S&P
        fields.appendValue("RTG_SP_LT_LC_ISSUER_CREDIT"); //S&P


        System.out.println("Sending Request: " + request);
        session.sendRequest(request, null);
        return processResponse(session, "current");
    }


    public static void run() throws Exception {

    }
}
