package org.ifdc.web;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.ifdc.web.controller.PageController;
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
        before("*",                  Filters.addTrailingSlashes);
//        before("*",                  Filters.handleLocaleChange);

        // Set up routes
        get(Path.Web.INDEX,          PageController.serveIndexPage);
        get("/",                     PageController.serveIndexPage);
//        get(Path.Web.BOOKS,          BookController.fetchAllBooks);
//        get(Path.Web.ONE_BOOK,       BookController.fetchOneBook);
        get(Path.Web.LOGIN,          PageController.serveLoginPage);
        post(Path.Web.LOGIN,         PageController.handleLoginPost);
        get(Path.Web.LOGOUT,        PageController.handleLogoutRequest);
        post(Path.Web.LOGOUT,        PageController.handleLogoutRequest);
        get(Path.Web.UPLOAD,        PageController.serveUploadPage);
        post(Path.Web.UPLOAD,        PageController.handleUploadPost);
        get("*",                     PageController.serveNotFoundPage, new FreeMarkerEngine());

        //Set up after-filters (called after each get/post)
        after("*",                   Filters.addGzipHeader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("System start @ " + port);
    }
}
