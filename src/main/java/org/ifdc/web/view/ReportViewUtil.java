package org.ifdc.web.view;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.ifdc.web.dao.IndicatorDAO;
import org.ifdc.web.dao.ReportDAO;
import org.ifdc.web.dao.bean.Activity;
import org.ifdc.web.dao.bean.Report;
import org.ifdc.web.util.Path;
import static org.ifdc.web.view.ViewUtil.setCommonParam;
import spark.ModelAndView;
import spark.Request;
import spark.template.freemarker.FreeMarkerEngine;

/**
 *
 * @author Meng Zhang
 */
public class ReportViewUtil {
    
    public static String getEditPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        Activity activity = Activity.toActivity(attributes);
        ArrayList<Report> indiList = ReportDAO.search(activity);
        TreeMap<String, HashMap<String, String>> indiMap = ReportDAO.toIndicatorValueMap(indiList);
        attributes.put("indicators", ReportDAO.toIndicatorList(indiList));
        attributes.put("allIndicators", IndicatorDAO.getIndicatorList());
        attributes.put("indicatorValues", indiMap);
        attributes.put("years", indiMap.keySet());
        attributes.put("curYear", new SimpleDateFormat("yyyy").format(new Date()));
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Report.EDIT));
    }
    
    public static String getViewPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        Activity activity = Activity.toActivity(attributes);
        ArrayList<Report> indiList = ReportDAO.search(activity);
        TreeMap<String, HashMap<String, String>> indiMap = ReportDAO.toIndicatorValueMap(indiList);
        attributes.put("indicators", ReportDAO.toIndicatorList(indiList));
        attributes.put("indicatorValues", indiMap);
        attributes.put("years", indiMap.keySet());
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Report.VIEW));
    }
    
    public static String getListPage(Request request, Map<String, Object> attributes) {
        setCommonParam(request, attributes);
        ArrayList<Report> reports = ReportDAO.list();
        attributes.put("reports", reports);
        attributes.put("indicators", ReportDAO.toIndicatorList(reports));
        return new FreeMarkerEngine().render(new ModelAndView(attributes, Path.Template.Report.LIST));
    }
    
}
