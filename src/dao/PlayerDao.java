package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import static db.MongoJDBCConnection.database;
import java.util.UUID;

public class PlayerDao {

   private ObjectMapper mapper ;

   private MongoCollection<Document> playerCollection;

   public PlayerDao() {

   }

   public void insertPlayers(String playerName,String teamId){
      Document demo = new Document()
              .append("_id", UUID.randomUUID().toString())
              .append("name", playerName)
              .append("teamId", teamId);
      try {
         playerCollection = database.getCollection("player");
         InsertOneResult result = playerCollection.insertOne(demo);
         System.out.println("Success! Inserted document id: " + result.getInsertedId());
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
