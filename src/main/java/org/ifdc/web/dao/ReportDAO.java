package org.ifdc.web.dao;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.ifdc.web.dao.bean.Activity;
import org.ifdc.web.dao.bean.Indicator;
import org.ifdc.web.dao.bean.Report;
import org.ifdc.web.util.DBUtil;
import org.ifdc.web.util.JsonUtil;
import spark.Request;

/**
 *
 * @author Meng Zhang
 */
public class ReportDAO {

    public static ArrayList<Report> list() {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("report").path("list")
                    .request().get();
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                ArrayList<HashMap> arr = JsonUtil.toList(json);
                ArrayList<Report> ret = new ArrayList();
                for (HashMap m : arr) {
                    ret.add(Report.toReport(m));
                }
                return ret;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList();
    }

    public static Report find(Report report) {
        if (report == null || report.isEmpty()) {
            return null;
        }
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("report").path("find")
                    .queryParam("project_name", report.getProject())
                    .queryParam("crop", report.getCrop())
                    .queryParam("tech", report.getTech())
                    .queryParam("year", report.getYear())
                    .request().get();
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                return Report.toReport(JsonUtil.toMap(json));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Report> search(Activity activity) {
        if (activity == null || activity.isEmpty()) {
            return new ArrayList();
        }
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("report").path("search")
                    .queryParam("project_name", activity.getProject())
                    .queryParam("crop", activity.getCrop())
                    .queryParam("tech", activity.getTech())
                    .request().get();
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                ArrayList<HashMap> arr = JsonUtil.toList(json);
                ArrayList<Report> ret = new ArrayList();
                for (HashMap m : arr) {
                    ret.add(Report.toReport(m));
                }
                return ret;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList();
    }

    public static TreeMap<String, HashMap<String, String>> toIndicatorValueMap(ArrayList<Report> reports) {
        TreeMap<String, HashMap<String, String>> ret = new TreeMap();
        for (Report r : reports) {
            ret.put(r.getYear(), r.getIndicatorMap());
        }
        return ret;
    }

    public static List<Indicator> toIndicatorList(ArrayList<Report> reports) {
        if (reports == null || reports.isEmpty()) {
            return new ArrayList();
        }
        HashSet<String> indiNames = new HashSet();
        for (Report r : reports) {
            indiNames.addAll(r.getIndicatorMap().keySet());
        }
        return IndicatorDAO.getIndicatorList(indiNames.toArray(new String[]{}));
    }

    public static ArrayList<Report> readPostBody(Request request) {
        String urlencoded = request.body();
        Set<String> paramSet = request.queryParams();
        Activity activity = new Activity(request);
        ArrayList<Report> ret = new ArrayList();
        HashMap indicators = new HashMap();
        String year = null;
        ArrayList<String> newIndiNames = new ArrayList();
        int newIndiValIdx = 0;
        for (String keyValue : urlencoded.trim().split("&")) {

            String[] tokens = keyValue.trim().split("=");
            String key = tokens[0];
            if (paramSet.contains(key)) {
                continue;
            }
            String value;
            try {
                value = tokens.length < 2 ? "" : URLDecoder.decode(tokens[1], "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                value = tokens[1];
            }
            if (key.equals("year")) {
                if (year == null) {
                    year = value;
                } else {
                    ret.add(new Report(activity, year, indicators));
                    indicators = new HashMap();
                    year = value;
                    newIndiValIdx = 0;
                }
            } else if (key.equals("indi_name")) {
                newIndiNames.add(value);
            } else if (key.equals("indi_value")) {
                indicators.put(newIndiNames.get(newIndiValIdx), value);
                newIndiValIdx++;
            }else {
                indicators.put(key, value);
            }

        }
        if (year != null) {
            ret.add(new Report(activity, year, indicators));
        }
        return ret;
    }
    
    public static boolean update(ArrayList<Report> reports) {
        boolean ret = true;
        for (Report report : reports) {
            if (!update(report)) {
                ret = false;
            }
        }
        return ret;
    }
    
    public static boolean update(Report report) {
        if (report == null || report.isEmpty()) {
            return false;
        } else {
            try {
                Client client = ClientBuilder.newClient();
                WebTarget service = client.target(DBUtil.getDBBaseURI());
                service = service.path("me").path("report").path("add")
                    .queryParam("project_name", report.getProject())
                    .queryParam("crop", report.getCrop())
                    .queryParam("tech", report.getTech())
                    .queryParam("year", report.getYear());
                boolean ret = true;
                HashMap<String, String> indicators = report.getIndicatorMap();
                for (String key : indicators.keySet()) {
                    Response response = service
                        .queryParam("indi_name", key)
                        .queryParam("indi_value", indicators.get(key))
                            .request().get();
                    if (response.getStatus() == 200) {
                        String id = response.readEntity(String.class);
                        if (id == null || id.equals("-1") || id.equals("-2")) {
                            ret = false;
                        }
                    }
                }
                return ret;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}
