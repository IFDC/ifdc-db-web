package org.ifdc.web.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.eclipse.jetty.util.ConcurrentHashSet;
import org.ifdc.web.dao.bean.Activity;
import org.ifdc.web.dao.bean.Indicator;
import org.ifdc.web.util.DBUtil;
import org.ifdc.web.util.JsonUtil;

/**
 *
 * @author Meng Zhang
 */
public class IndicatorDAO {

    private static final ConcurrentHashMap<String, Indicator> indicators = initalize();
    private static final ConcurrentHashSet<String> units = syncRecords("unit");

    public static ConcurrentHashMap<String, Indicator> initalize() {
        ConcurrentHashMap<String, Indicator> ret = new ConcurrentHashMap();
        ArrayList<Indicator> indiArr = list();
        for (Indicator indi : indiArr) {
            ret.put(indi.getName(), indi);
        }
        return ret;
    }
    
    public static ArrayList<Indicator> list() {
        ArrayList<Indicator> ret = new ArrayList();

        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("indicator").path("list").request().get();
            if (response.getStatus() == 200) {
                String resJson = response.readEntity(String.class);
                if (!resJson.trim().equals("")) {
                    ArrayList<HashMap> arr = JsonUtil.toList(resJson);
                    for (HashMap m : arr) {
                        ret.add(Indicator.toIndicator(m));
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static ConcurrentHashSet<String> syncRecords(String type) {
        ConcurrentHashSet<String> ret = new ConcurrentHashSet();

        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("indicator").path("list" + type).request().get();
            if (response.getStatus() == 200) {
                String resJson = response.readEntity(String.class);
                if (!resJson.trim().equals("")) {
                    ret.addAll(JsonUtil.toObject(resJson, new TypeReference<ArrayList<String>>() {
                    }));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static Indicator find(String key, String value) {
        Indicator ret = null;
        if (key == null || key.isEmpty() || value == null || value.isEmpty()) {
            return ret;
        } else if (!key.equals("name") && !key.equals("title")) {
            return ret;
        }
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("indicator").path("find")
                    .queryParam(key, value)
                    .request().get();
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                ret = JsonUtil.toIndicator(json);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static boolean add(Indicator indicator) {
        String name = indicator.getName();
        String code1 = indicator.getEgCode1();
        String code2 = indicator.getEgCode2();
        String title = indicator.getTitle();
        String unit = indicator.getUnit();
        if (name == null || name.isEmpty() || title == null || title.isEmpty()) {
            return false;
        } else {
            try {
                Client client = ClientBuilder.newClient();
                WebTarget service = client.target(DBUtil.getDBBaseURI());
                Response response = service.path("me").path("indicator").path("add")
                        .queryParam("name", name)
                        .queryParam("title", title)
                        .queryParam("eg_code1", code1)
                        .queryParam("eg_code2", code2)
                        .queryParam("unit", unit)
                        .request().get();
                if (response.getStatus() == 200) {
                    indicators.put(name, indicator);
                    units.add(unit);
                    String id = response.readEntity(String.class);
                    if (id != null && !id.equals("-1") && !id.equals("-2")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public static boolean update(Indicator indicator) {
        String name = indicator.getName();
        String code1 = indicator.getEgCode1();
        String code2 = indicator.getEgCode2();
        String title = indicator.getTitle();
        String unit = indicator.getUnit();
        if (name == null || name.isEmpty() || title == null || title.isEmpty()) {
            return false;
        } else {
            try {
                Client client = ClientBuilder.newClient();
                WebTarget service = client.target(DBUtil.getDBBaseURI());
                Response response = service.path("me").path("indicator").path("update")
                        .queryParam("name", name)
                        .queryParam("title", title)
                        .queryParam("eg_code1", code1)
                        .queryParam("eg_code2", code2)
                        .queryParam("unit", unit)
                        .request().get();
                if (response.getStatus() == 200) {
                    indicators.put(name, indicator);
                    units.add(unit);
                    String id = response.readEntity(String.class);
                    if (id != null && !id.equals("-1")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
    }

    public static List<String> getUnitList() {
        return Arrays.asList(units.toArray(new String[]{}));
    }

    public static List<Indicator> getIndicatorList(String... names) {
        ArrayList<Indicator> ret = new ArrayList();
        if (names == null || names.length == 0) {
            ret.addAll(indicators.values());
        } else {
            for (String name : names) {
                if (indicators.containsKey(name)) {
                    ret.add(indicators.get(name));
                }
            }
        }
        return ret;
    }

//    public static boolean updateList(Map<String, Object> attributes) {
//
//        String project = (String) attributes.get("project");
//        String crop = (String) attributes.get("crop");
//        String technology = (String) attributes.get("technology");
//        if (project == null || crop == null || technology == null) {
//            return false;
//        }
//        String key = project + "_" + crop + "_" + technology;
//        if (activities.contains(key)) {
//            return false;
//        } else {
//            try {
//                Client client = ClientBuilder.newClient();
//                WebTarget service = client.target(DBUtil.getDBBaseURI());
//                Response response = service.path("data").path("me").path("activity").path("check")
//                        .queryParam("project", project)
//                        .queryParam("crop", crop)
//                        .queryParam("technology", technology).request().get();
//                if (response.getStatus() == 200) {
//                    activities.add(key);
////                    projects.add(project);
//                    crops.add(crop);
//                    techs.add(technology);
//                    return !response.readEntity(Boolean.class);
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            return false;
//        }
//    }
//    public static boolean addIndicatorValue(Map<String, Object> attributes) {
//
//        String project = (String) attributes.get("project");
//        String crop = (String) attributes.get("crop");
//        String technology = (String) attributes.get("technology");
//        String year = (String) attributes.get("year");
//        String indicator = (String) attributes.get("indicator");
//        String value = (String) attributes.get("value");
//        if (project == null || crop == null || technology == null || year == null || indicator == null || value == null) {
//            return false;
//        }
//        try {
//            Client client = ClientBuilder.newClient();
//            WebTarget service = client.target(DBUtil.getDBBaseURI()).path("data").path("me");
//            Response response = service.path("update2")
//                    .queryParam("project", project)
//                    .queryParam("crop", crop)
//                    .queryParam("technology", technology)
//                    .queryParam("year", year)
//                    .queryParam("indicator", indicator)
//                    .queryParam("value", value)
////                    .queryParam("indicatorData", "{\"" + indicator + "\":\"" + value + "\"}")
//                    .request().get();
//            if (response.getStatus() == 200) {
////                HashMap<String, HashMap<String, String>> records = response.readEntity(HashMap.class);
//                return response.readEntity(Boolean.class);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }

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
