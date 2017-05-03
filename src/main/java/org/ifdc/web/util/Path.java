package org.ifdc.web.util;

import lombok.*;

public class Path {

    // The @Getter methods are needed in order to access
    public static class Web {
        @Getter public static final String INDEX = "/index/";
        @Getter public static final String LOGIN = "/login/";
        @Getter public static final String LOGOUT = "/logout/";
        @Getter public static final String UPLOAD = "/upload/";
//        @Getter public static final String ONE_BOOK = "/books/:isbn/";
    }

    public static class Template {
        public final static String INDEX = "index.ftl";
        public final static String LOGIN = "login.ftl";
        public final static String UPLOAD = "upload.ftl";
//        public final static String BOOKS_ALL = "/velocity/book/all.vm";
//        public static final String BOOKS_ONE = "/velocity/book/one.vm";
        public static final String NOT_FOUND = "notFound.ftl";
    }

}
