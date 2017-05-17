package org.ifdc.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.ifdc.web.controller.ActivityPageController;
import org.ifdc.web.controller.IndicatorPageController;
import org.ifdc.web.controller.PageController;
import org.ifdc.web.controller.ProjectPageController;
import org.ifdc.web.controller.ReportPageController;
import org.ifdc.web.util.Filters;
import org.ifdc.web.util.Path;
import org.slf4j.LoggerFactory;
import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;
import spark.template.freemarker.FreeMarkerEngine;

/**
 *
 * @author Meng Zhang
 */
public class Main {
    
    private static final int DEF_PORT = 8081;
    public static final Logger LOG = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

    public Main() {
    }
    
    public static void main(String[] args) {
        // Configure Spark
        LOG.setLevel(Level.INFO);

        String portStr = System.getenv("PORT");
        int port;
        try {
            port = Integer.parseInt(portStr);
        } catch (Exception e) {
            port = DEF_PORT;
        }
        try {
        port(port);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);

        // Set up before-filters (called before each get/post)
//        before("*",                  Filters.addTrailingSlashes);
//        before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX,          PageController.serveIndexPage);
        get("/",                     PageController.serveIndexPage);
//        get(Path.Web.BOOKS,          BookController.fetchAllBooks);
//        get(Path.Web.ONE_BOOK,       BookController.fetchOneBook);
        get(Path.Web.REGISTER,       PageController.serveRegisterPage);
        post(Path.Web.REGISTER,      PageController.handleRegisterPost);
        get(Path.Web.LOGIN,          PageController.serveLoginPage);
        post(Path.Web.LOGIN,         PageController.handleLoginPost);
        get(Path.Web.LOGOUT,         PageController.handleLogoutRequest);
        post(Path.Web.LOGOUT,        PageController.handleLogoutRequest);
//        get(Path.Web.UPLOAD,        PageController.serveUploadPage);
//        post(Path.Web.UPLOAD,        PageController.handleUploadPost);
        
        get(Path.Web.Project.LIST,             ProjectPageController.serveListPage);
        get(Path.Web.Project.CREATE,           ProjectPageController.serveCreatePage);
        post(Path.Web.Project.CREATE,          ProjectPageController.handleCreatePost);
        get(Path.Web.Project.FIND,             ProjectPageController.serveDetailPage);
        
        get(Path.Web.Activity.CREATE,             ActivityPageController.serveCreatePage);
        post(Path.Web.Activity.CREATE,             ActivityPageController.handleCreatePost);
        get(Path.Web.Activity.LIST,              ActivityPageController.serveListPage);
//        get(Path.Web.Activity.CREATE_DETAIL,       ActivityPageController.handleCreateDetailGet);
//        post(Path.Web.Activity.CREATE_DETAIL,       ActivityPageController.handleCreateDetailPost);
        
        get(Path.Web.Report.LIST,               ReportPageController.serveListPage);
        get(Path.Web.Report.VIEW,               ReportPageController.serveViewPage);
        get(Path.Web.Report.EDIT,               ReportPageController.serveEditPage);
        post(Path.Web.Report.EDIT,              ReportPageController.handleEditPost);
        
        get(Path.Web.Indicator.LIST,             IndicatorPageController.serveListPage);
        get(Path.Web.Indicator.CREATE,           IndicatorPageController.serveCreatePage);
        post(Path.Web.Indicator.CREATE,          IndicatorPageController.handleCreatePost);
        get(Path.Web.Indicator.EDIT,             IndicatorPageController.serveEditPage);
        post(Path.Web.Indicator.EDIT,            IndicatorPageController.handleEditPost);
        
        get("*",                     PageController.serveNotFoundPage, new FreeMarkerEngine());

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("System start @ " + port);
    }
}
