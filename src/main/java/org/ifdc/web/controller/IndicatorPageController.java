package org.ifdc.web.controller;

import java.util.HashMap;
import java.util.Map;
import static org.ifdc.web.Main.LOG;
import org.ifdc.web.dao.IndicatorDAO;
import org.ifdc.web.dao.bean.Indicator;
import org.ifdc.web.util.Path;
import static org.ifdc.web.view.IndicatorViewUtil.getCreatePage;
import static org.ifdc.web.view.IndicatorViewUtil.getEditPage;
import static org.ifdc.web.view.IndicatorViewUtil.getListPage;
import org.ifdc.web.view.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author Meng Zhang
 */
public class IndicatorPageController {

    public static Route serveCreatePage = (Request request, Response response) -> {
        LOG.info("Serve Create Indicator Page");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        return getCreatePage(request, attributes);
    };

    public static Route serveListPage = (Request request, Response response) -> {
        LOG.info("Serve List Indicator Page");
        Map<String, Object> attributes = new HashMap<>();
        return getListPage(request, attributes);
    };

    public static Route handleCreatePost = (Request request, Response response) -> {
        LOG.info("Handle Create Indicator Post");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        Indicator indicator = new Indicator(request);
        attributes.putAll(indicator);

        if (!IndicatorDAO.add(indicator)) {
            attributes.put("operation_result", "Failed");
            return getCreatePage(request, attributes);
        }

        response.redirect(Path.Web.Project.LIST);
        return getListPage(request, attributes);
    };

    public static Route serveEditPage = (Request request, Response response) -> {
        LOG.info("Serve Edit Indicator Page");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        String name = request.queryParams("name");
        Indicator indi = IndicatorDAO.find("name", name);
        if (indi != null && !indi.isEmpty()) {
            attributes.putAll(indi);
        } else {
            attributes.put("name", name);
            response.redirect(Path.Web.Indicator.CREATE + "?name=" + name);
            return getCreatePage(request, attributes);
        }
        return getEditPage(request, attributes);
    };

    public static Route handleEditPost = (Request request, Response response) -> {
        LOG.info("Handle Edit Indicator Post");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        Indicator indicator = new Indicator(request);
        attributes.putAll(indicator);

        if (!IndicatorDAO.update(indicator)) {
            attributes.put("operation_result", "Failed");
            return getEditPage(request, attributes);
        }

        response.redirect(Path.Web.Indicator.LIST);
        return getListPage(request, attributes);
    };
}
