package mike.app.mongodbtest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Projections.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import org.slf4j.LoggerFactory;
import spark.ModelAndView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;

/**
 *
 * @author mike
 */
public class TestDB {

    public static void main(String[] args) {

        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.WARN);

        port(Integer.valueOf(System.getenv("PORT")));
        staticFileLocation("/public");

        get("/hello", (req, res) -> "Hello World");

        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(System.getenv("JDBC_DATABASE_URL"));
        final HikariDataSource dataSource = (config.getJdbcUrl() != null)
                ? new HikariDataSource(config) : new HikariDataSource();

        get("/db", (req, res) -> {
            Map<String, Object> attributes = new HashMap<>();
            MongoClientURI uri = new MongoClientURI(
                    "mongodb://mikecomic:Mike0105@cluster0-shard-00-00-upixo.mongodb.net:27017,cluster0-shard-00-01-upixo.mongodb.net:27017,cluster0-shard-00-02-upixo.mongodb.net:27017/mydb?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
            try (MongoClient mongoClient = new MongoClient(uri);) {

//        try (MongoClient mongoClient = new MongoClient("localhost", 27017)) {
                MongoDatabase database = mongoClient.getDatabase("mydb");
                MongoCollection<Document> collection = database.getCollection("test");

            // Prepare Data
//            Document doc1 = new Document("name", "MongoDB")
//                    .append("type", "database")
//                    .append("count", 1)
//                    .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
//                    .append("info", new Document("x", 203).append("y", 102));
//            List tags = Arrays.asList(new String[]{"abc", "kkk"});
//            doc1.put("tags", tags);
//            Document doc2 = Document.parse("{"
//                    + "'name':'Riak' "
//                    + "'type':'database' "
//                    + "'versions':['v1.0','v1.1'] "
//                    + "}");
                // Insert Data
//            collection.drop();
//            collection.insertOne(doc1);
//            collection.insertOne(doc2);
//            ArrayList<Document> documents = new ArrayList<Document>();
//            for (int i = 0; i < 100; i++) {
//                documents.add(new Document("i", i));
//            }
//            collection.insertMany(documents);
//            System.out.println(tags.getClass().toString());
//            System.out.println(tags instanceof ArrayList);
//            System.out.println(collection.count());
                
                // Search Data
                ArrayList<String> output = new ArrayList<String>();
                // method 1
                output.add(collection.find(Document.parse("{'name':'MongoDB'}")).projection(fields(include("name", "versions"), excludeId())).first().toString());
                // method 2
//            System.out.println(collection.find(new Document("name", "Riak")).first());
                // method 3
                MongoCursor<Document> cursor = collection.find().filter(new Document("type", "database")).iterator();
                while (cursor.hasNext()) {
                    output.add(cursor.next().toString());
                }
                attributes.put("results", output);
                return new ModelAndView(attributes, "db.ftl");
            } catch (Exception e) {
                attributes.put("message", "There was an error: " + e);
                return new ModelAndView(attributes, "error.ftl");
            }
            
        }, new FreeMarkerEngine());
    }
}
