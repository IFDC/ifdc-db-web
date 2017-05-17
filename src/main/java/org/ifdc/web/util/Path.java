package org.ifdc.web.util;

import lombok.*;

public class Path {

    // The @Getter methods are needed in order to access
    public static class Web {
        @Getter public static final String INDEX = "/index";
        @Getter public static final String REGISTER = "/register";
        @Getter public static final String LOGIN = "/login";
        @Getter public static final String LOGOUT = "/logout";
        @Getter public static final String UPLOAD = "/upload";
//        @Getter public static final String ONE_BOOK = "/books/:isbn/";
        
        public static class Project {
            private static final String PACKAGE = "/" + Project.class.getSimpleName().toLowerCase();
            @Getter public static final String CREATE = PACKAGE + "/create";
            @Getter public static final String LIST = PACKAGE + "/list";
            @Getter public static final String SEARCH = PACKAGE + "/search";
            @Getter public static final String FIND = PACKAGE + "/find";
        }
        
        public static class Activity {
            private static final String PACKAGE = "/" + Activity.class.getSimpleName().toLowerCase();
            @Getter public static final String CREATE = PACKAGE + "/create";
            @Getter public static final String LIST = PACKAGE + "/list";
//            @Getter public static final String CREATE_DETAIL = PACKAGE + "/create/detail/";
        }
        
        public static class Report {
            private static final String PACKAGE = "/" + Report.class.getSimpleName().toLowerCase();
            @Getter public static final String EDIT = PACKAGE + "/edit";
            @Getter public static final String LIST = PACKAGE + "/list";
            @Getter public static final String VIEW = PACKAGE + "/view";
        }
        
        public static class Indicator {
            private static final String PACKAGE = "/" + Indicator.class.getSimpleName().toLowerCase();
            @Getter public static final String CREATE = PACKAGE + "/create";
            @Getter public static final String LIST = PACKAGE + "/list";
            @Getter public static final String EDIT = PACKAGE + "/edit";
        }
    }
    
    public static class Template {
        public final static String INDEX = "index.ftl";
        public final static String REGISTER = "register.ftl";
        public final static String LOGIN = "login.ftl";
        public final static String UPLOAD = "upload.ftl";
//        public final static String BOOKS_ALL = "/velocity/book/all.vm";
//        public static final String BOOKS_ONE = "/velocity/book/one.vm";
        public static final String NOT_FOUND = "notFound.ftl";
        
        public static class Project {
            private static final String PACKAGE = Project.class.getSimpleName().toLowerCase();
            public static final String CREATE = PACKAGE + "/create.ftl";
            public static final String LIST = PACKAGE + "/list.ftl";
            public static final String SEARCH = PACKAGE + "/search.ftl";
            public static final String DETAIL = PACKAGE + "/detail.ftl";
        }
        
        public static class Activity {
            private static final String PACKAGE = Activity.class.getSimpleName().toLowerCase();
            public static final String CREATE = PACKAGE + "/create.ftl";
            public static final String LIST = PACKAGE + "/list.ftl";
//            public static final String CREATE_DETAIL = PACKAGE + "/createDetail.ftl";
        }
        
        public static class Report {
            private static final String PACKAGE = Report.class.getSimpleName().toLowerCase();
            public static final String EDIT = PACKAGE + "/edit.ftl";
            public static final String LIST = PACKAGE + "/list.ftl";
            public static final String VIEW = PACKAGE + "/view.ftl";
        }
        
        public static class Indicator {
            private static final String PACKAGE = Indicator.class.getSimpleName().toLowerCase();
            public static final String CREATE = PACKAGE + "/create.ftl";
            public static final String LIST = PACKAGE + "/list.ftl";
            public static final String EDIT = PACKAGE + "/edit.ftl";
        }
    }

}
