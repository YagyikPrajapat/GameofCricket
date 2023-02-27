package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoJDBCConnection {
   public static MongoDatabase database;

   public MongoJDBCConnection(){
      String uri = "mongodb://localhost:27017";
      MongoClient mongoClient = MongoClients.create(uri);
      try  {
         database = mongoClient.getDatabase("Cricket");
      }catch (Exception e){
         e.printStackTrace();
      }
   }
}
