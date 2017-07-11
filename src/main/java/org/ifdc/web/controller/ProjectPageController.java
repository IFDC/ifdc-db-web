package org.ifdc.web.controller;

import java.util.HashMap;
import java.util.Map;
import static org.ifdc.web.Main.LOG;
import org.ifdc.web.dao.ProjectDAO;
import org.ifdc.web.dao.bean.Project;
import org.ifdc.web.util.Path;
import static org.ifdc.web.view.ProjectViewUtil.getCreatePage;
import static org.ifdc.web.view.ProjectViewUtil.getDetailPage;
import static org.ifdc.web.view.ProjectViewUtil.getListPage;
import org.ifdc.web.view.ViewUtil;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 *
 * @author Meng Zhang
 */
public class ProjectPageController {

    public static Route serveCreatePage = (Request request, Response response) -> {
        LOG.info("Serve Create Project Page");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        return getCreatePage(request, attributes);
    };

    public static Route serveListPage = (Request request, Response response) -> {
        LOG.info("Serve List Project Page");
        Map<String, Object> attributes = new HashMap<>();
        return getListPage(request, attributes);
    };

    public static Route handleCreatePost = (Request request, Response response) -> {
        LOG.info("Handle Create Project Post");
        Map<String, Object> attributes = new HashMap<>();
        if (!ViewUtil.isLogined(request)) {
            response.redirect(Path.Web.LOGIN);
            return ViewUtil.getLoginPage(request, attributes);
        }
        Project project = new Project(request.queryParams("name"));
        project.setDescription(request.queryParams("description"));
        attributes.putAll(project);

        if (!ProjectDAO.add(project)) {
            attributes.put("operation_result", "Failed");
            return getCreatePage(request, attributes);
        }

        response.redirect(Path.Web.Project.LIST);
        return getListPage(request, attributes);
    };

    public static Route serveDetailPage = (Request request, Response response) -> {
        LOG.info("Serve Project Detail Page");
        Map<String, Object> attributes = new HashMap<>();
        
        String id = request.queryParams("id");
        HashMap ret = ProjectDAO.find(id);
        if (ret.isEmpty()) {
            attributes.put("operation_result", "Failed");
        } else {
            ret.remove("_id");
            attributes.put("id", id);
            attributes.putAll(ret);
        }
        return getDetailPage(request, attributes);
    };
    
    
}
