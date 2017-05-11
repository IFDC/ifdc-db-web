/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ifdc.web.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.ifdc.web.dao.bean.Indicator;
import org.ifdc.web.dao.bean.Activity;
import org.ifdc.web.dao.bean.User;
import org.ifdc.web.util.DBUtil;

/**
 *
 * @author Meng Zhang
 */
public class ActivityDAO {

    private static final ConcurrentHashSet<String> projects = syncRecords("project");
    private static final ConcurrentHashSet<String> crops = syncRecords("crop");
    private static final ConcurrentHashSet<String> technologies = syncRecords("technology");
    private static final ConcurrentHashSet<String> activities = new ConcurrentHashSet();
    private static final ConcurrentHashMap<String, Indicator> indicators = initalize();

    public static ConcurrentHashMap<String, Indicator> initalize() {
        ConcurrentHashMap<String, Indicator> ret = new ConcurrentHashMap();
        ret.put("AUIT", new Indicator());
        return ret;
    }
    
    public static ConcurrentHashSet<String> syncRecords(String dataType) {
        ConcurrentHashSet<String> ret = new ConcurrentHashSet();

        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("data").path("me").path(dataType).path("list").request().get();
            if (response.getStatus() == 200) {
                String resJson = response.readEntity(String.class);
                if (!resJson.trim().equals("")) {
                    ObjectMapper mapper = new ObjectMapper();
                    ret.addAll(mapper.readValue(resJson, ArrayList.class));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static List<String> getProjectList() {
        return Arrays.asList(projects.toArray(new String[]{}));
    }

    public static List<String> getCropList() {
        return Arrays.asList(crops.toArray(new String[]{}));
    }

    public static List<String> getTechnologyList() {
        return Arrays.asList(technologies.toArray(new String[]{}));
    }

    public static List<String> getIndicatorList() {
        return Arrays.asList(indicators.keySet().toArray(new String[]{}));
    }

    public static boolean updateList(Map<String, Object> attributes) {

        String project = (String) attributes.get("project");
        String crop = (String) attributes.get("crop");
        String technology = (String) attributes.get("technology");
        if (project == null || crop == null || technology == null) {
            return false;
        }
        String key = project + "_" + crop + "_" + technology;
        if (activities.contains(key)) {
            return false;
        } else {
            try {
                Client client = ClientBuilder.newClient();
                WebTarget service = client.target(DBUtil.getDBBaseURI());
                Response response = service.path("data").path("me").path("activity").path("check")
                        .queryParam("project", project)
                        .queryParam("crop", crop)
                        .queryParam("technology", technology).request().get();
                if (response.getStatus() == 200) {
                    activities.add(key);
                    projects.add(project);
                    crops.add(crop);
                    technologies.add(technology);
                    return !response.readEntity(Boolean.class);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public static boolean addIndicatorValue(Map<String, Object> attributes) {

        String project = (String) attributes.get("project");
        String crop = (String) attributes.get("crop");
        String technology = (String) attributes.get("technology");
        String year = (String) attributes.get("year");
        String indicator = (String) attributes.get("indicator");
        String value = (String) attributes.get("value");
        if (project == null || crop == null || technology == null || year == null || indicator == null || value == null) {
            return false;
        }
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI()).path("data").path("me");
            Response response = service.path("update2")
                    .queryParam("project", project)
                    .queryParam("crop", crop)
                    .queryParam("technology", technology)
                    .queryParam("year", year)
                    .queryParam("indicator", indicator)
                    .queryParam("value", value)
//                    .queryParam("indicatorData", "{\"" + indicator + "\":\"" + value + "\"}")
                    .request().get();
            if (response.getStatus() == 200) {
//                HashMap<String, HashMap<String, String>> records = response.readEntity(HashMap.class);
                return response.readEntity(Boolean.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static HashMap<String, HashMap<String, String>> getIndicatorValues(Map<String, Object> attributes) {

        String project = (String) attributes.get("project");
        String crop = (String) attributes.get("crop");
        String technology = (String) attributes.get("technology");
        if (project == null || crop == null || technology == null) {
            return new HashMap();
        }
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI()).path("data").path("me");
            Response response = service.path("find")
                    .queryParam("project", project)
                    .queryParam("crop", crop)
                    .queryParam("technology", technology).request().get();
            if (response.getStatus() == 200) {
                String ret = response.readEntity(String.class);
                System.out.println(new ObjectMapper().readValue(ret, HashMap.class));
                return new ObjectMapper().readValue(ret, HashMap.class);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new HashMap();
    }

//    public static User getUserByUsername(String userName) {
//        if (activities.containsKey(userName)) {
//            return activities.get(userName);
//        } else {
//
//            try {
//                Client client = ClientBuilder.newClient();
//                WebTarget service = client.target(DBUtil.getDBBaseURI());
//                Response response = service.path("data").path("me").path("project").path("list").request().get();
//
//                User ret = null;
//                if (response.getStatus() == 200) {
//                    String resJson = response.readEntity(String.class);
//                    if (!resJson.trim().equals("")) {
//                        ObjectMapper mapper = new ObjectMapper();
//                        ret = mapper.readValue(resJson, User.class);
//                        activities.put(userName, ret);
//                    }
//                }
//                client.close();
//                return ret;
//
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        return null;
//    }
}
