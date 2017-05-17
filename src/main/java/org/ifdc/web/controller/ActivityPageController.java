package org.ifdc.web.controller;

import java.util.HashMap;
import java.util.Map;
import static org.ifdc.web.Main.LOG;
import org.ifdc.web.dao.ActivityDAO;
import org.ifdc.web.dao.bean.Activity;
import org.ifdc.web.view.ActivityViewUtil;
import org.ifdc.web.util.Path;
import org.ifdc.web.view.ProjectViewUtil;
import org.ifdc.web.view.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author Meng Zhang
 */
public class ActivityPageController {

    public static Route serveCreatePage = (Request request, Response response) -> {
        LOG.info("Serve Create M&E Activity Page");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        return ActivityViewUtil.getCreatePage(request, attributes);
    };

    public static Route handleCreatePost = (Request request, Response response) -> {
        LOG.info("Handle Create Activity Post");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        Activity activity = new Activity(
                request.queryParams("project_name"),
                request.queryParams("crop"),
                request.queryParams("tech"));
        attributes.putAll(attributes);

        if (!ActivityDAO.add(activity)) {
            attributes.put("operation_result", "Failed");
            return ActivityViewUtil.getCreatePage(request, attributes);
        }

//        response.redirect("detail");
//        response.redirect(Path.Web.Activity.CREATE_DETAIL);
        return ProjectViewUtil.getDetailPage(request, attributes);
    };

    public static Route serveListPage = (Request request, Response response) -> {
        LOG.info("Serve List M&E Activity Page");
        Map<String, Object> attributes = new HashMap<>();
        return ActivityViewUtil.getListPage(request, attributes);
    };

//    public static Route handleCreatePost_Old = (Request request, Response response) -> {
//        LOG.info("Handle Create M&E Activity Post Old");
//        Map<String, Object> attributes = new HashMap<>();
//        if (!ViewUtil.isLogined(request)) {
//            response.redirect(Path.Web.LOGIN);
//            return ViewUtil.getLoginPage(request, attributes);
//        }
//        attributes.put("project", request.queryParams("project"));
//        attributes.put("crop", request.queryParams("crop"));
//        attributes.put("technology", request.queryParams("technology"));
//
//        String detailFlg = request.queryParams("detail_flg");
//        if (detailFlg == null) {
//            if (!ActivityDAO.updateList(attributes)) {
//                attributes.put("operation_result", "Failed");
//                return ActivityViewUtil.getCreatePage(request, attributes);
//            }
//        } else if (detailFlg.equals("addDetail")) {
//            attributes.put("year", request.queryParams("year"));
//            attributes.put("indicator", request.queryParams("indicator"));
//            attributes.put("value", request.queryParams("value"));
//            if (!ActivityDAO.addIndicatorValue(attributes)) {
//                attributes.put("value", request.queryParams("value"));
//                attributes.put("operation_result", "Failed");
//            }
//        }
//
////        response.redirect("detail");
////        response.redirect(Path.Web.Activity.CREATE_DETAIL);
//        return ActivityViewUtil.getCreateDetailPage(request, attributes);
//    };
//    public static Route handleCreateDetailPost = (Request request, Response response) -> {
//        LOG.info("Handle Create M&E Activity Detail Post");
//        Map<String, Object> attributes = new HashMap<>();
//        if (!ViewUtil.isLogined(request)) {
//            response.redirect(Path.Web.LOGIN);
//            return ViewUtil.getLoginPage(request, attributes);
//        }
//        attributes.put("project", request.queryParams("project"));
//        attributes.put("crop", request.queryParams("crop"));
//        attributes.put("technology", request.queryParams("technology"));
//        attributes.put("year", request.queryParams("year"));
////        HashMap<String, HashMap<String, String>> indicatorValues = ActivityDAO.getIndicatorValues(attributes);
////        HashSet<String> indicators = new HashSet();
////        for (String key : indicatorValues.keySet()) {
////            indicators.addAll(indicatorValues.get(key).keySet());
////        }
////        attributes.put("years", indicatorValues.keySet());
////        attributes.put("indicators", indicators);
////        attributes.put("indicatorValues", indicatorValues.keySet());
//        return ActivityViewUtil.getCreateDetailPage(request, attributes);
//    };
//    public static Route handleCreateDetailGet = (Request request, Response response) -> {
//        LOG.info("Handle Create M&E Activity Detail Get");
//        LOG.info(request.body());
//        Map<String, Object> attributes = new HashMap<>();
//        if (!ViewUtil.isLogined(request)) {
//            response.redirect(Path.Web.LOGIN);
//            return ViewUtil.getLoginPage(request, attributes);
//        }
//        attributes.put("project", request.params("project"));
//        attributes.put("crop", request.params("crop"));
//        attributes.put("technology", request.params("technology"));
//        attributes.put("year", request.params("year"));
//        attributes.put("indicator", request.params("indicator"));
//        if (!ActivityDAO.addIndicatorValue(attributes)) {
//            attributes.put("value", request.params("value"));
//            attributes.put("operation_result", "Failed");
//        }
////        HashMap<String, HashMap<String, String>> indicatorValues = ActivityDAO.getIndicatorValues(attributes);
////        HashSet<String> indicators = new HashSet();
////        for (String key : indicatorValues.keySet()) {
////            indicators.addAll(indicatorValues.get(key).keySet());
////        }
////        attributes.put("years", indicatorValues.keySet());
////        attributes.put("indicators", indicators);
////        attributes.put("indicatorValues", indicatorValues.keySet());
//        return ActivityViewUtil.getCreateDetailPage(request, attributes);
//    };
}
