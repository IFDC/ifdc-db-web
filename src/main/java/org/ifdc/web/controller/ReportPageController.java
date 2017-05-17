package org.ifdc.web.controller;

import java.util.HashMap;
import java.util.Map;
import static org.ifdc.web.Main.LOG;
import org.ifdc.web.dao.ReportDAO;
import org.ifdc.web.dao.bean.Activity;
import org.ifdc.web.util.Path;
import static org.ifdc.web.view.ReportViewUtil.getEditPage;
import static org.ifdc.web.view.ReportViewUtil.getViewPage;
import static org.ifdc.web.view.ReportViewUtil.getListPage;
import org.ifdc.web.view.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author Meng Zhang
 */
public class ReportPageController {

    public static Route serveEditPage = (Request request, Response response) -> {
        LOG.info("Serve Edit Report Page");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        Activity activity = new Activity(request);
        if (activity.getProject() == null || activity.getCrop() == null || activity.getTech() == null) {
            attributes.put("operation_result", "Failed");
        }
        attributes.putAll(activity);
        return getEditPage(request, attributes);
    };

    public static Route serveViewPage = (Request request, Response response) -> {
        LOG.info("Serve View Report Page");
        Map<String, Object> attributes = new HashMap<>();
        Activity activity = new Activity(request);
        if (activity.getProject() == null || activity.getCrop() == null || activity.getTech() == null) {
            attributes.put("operation_result", "Failed");
        }
        attributes.putAll(activity);
        return getViewPage(request, attributes);
    };

    public static Route serveListPage = (Request request, Response response) -> {
        LOG.info("Serve List Report Page");
        Map<String, Object> attributes = new HashMap<>();
        return getListPage(request, attributes);
    };

    public static Route handleEditPost = (Request request, Response response) -> {
        LOG.info("Handle Edit Report Post");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        
        if (!ReportDAO.update(ReportDAO.readPostBody(request))) {
            attributes.put("operation_result", "Failed");
        }
        attributes.putAll(new Activity(request));

        return getEditPage(request, attributes);
    };
    
}
