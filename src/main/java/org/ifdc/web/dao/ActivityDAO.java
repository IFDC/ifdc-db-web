package org.ifdc.web.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
public class ActivityDAO {

    private static final ConcurrentHashSet<String> crops = syncRecords("crop");
    private static final ConcurrentHashSet<String> techs = syncRecords("tech");

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
            Response response = service.path("me").path("activity").path("list" + dataType).request().get();
            if (response.getStatus() == 200) {
                String resJson = response.readEntity(String.class);
                if (!resJson.trim().equals("")) {
                    ret.addAll(JsonUtil.toList(resJson));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }
    
    public static ArrayList<HashMap> list() {
        ArrayList<HashMap> ret = new ArrayList();
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("activity").path("list")
                    .request().get();
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                ret.addAll(JsonUtil.toList(json));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }
    
    public static ArrayList<HashMap> search(String projName) {
        ArrayList<HashMap> ret = new ArrayList();
        if (projName == null || projName.isEmpty()) {
            return ret;
        }
        try {
            Client client = ClientBuilder.newClient();
            WebTarget service = client.target(DBUtil.getDBBaseURI());
            Response response = service.path("me").path("activity").path("search")
                    .queryParam("project_name", projName)
                    .request().get();
            if (response.getStatus() == 200) {
                String json = response.readEntity(String.class);
                ret.addAll(JsonUtil.toList(json));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public static boolean add(Activity activity) {
        String name = activity.getProject();
        String crop = activity.getCrop();
        String tech = activity.getTech();
        if (name == null || name.isEmpty()) {
            return false;
        } else {
            try {
                Client client = ClientBuilder.newClient();
                WebTarget service = client.target(DBUtil.getDBBaseURI());
                Response response = service.path("me").path("activity").path("add")
                        .queryParam("project_name", name)
                        .queryParam("crop", crop)
                        .queryParam("tech", tech)
                        .request().get();
                if (response.getStatus() == 200) {
                    crops.add(crop);
                    techs.add(tech);
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

    public static List<String> getCropList() {
        return Arrays.asList(crops.toArray(new String[]{}));
    }

    public static List<String> getTechnologyList() {
        return Arrays.asList(techs.toArray(new String[]{}));
    }
}
