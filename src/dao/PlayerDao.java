package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import model.Player;
import model.Team;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static db.MongoJDBCConnection.database;

import java.io.IOException;
import java.util.UUID;

public class PlayerDao {

   private final ObjectMapper mapper = new ObjectMapper() ;

   private MongoCollection<Document> playerCollection;

   public PlayerDao() {

   }

   public Player getPlayerById(String id){
      playerCollection = database.getCollection("player");
      Bson filter = Filters.eq("_id", id);
      Document doc = playerCollection.find(filter).first();
      Player player = new Player();
      try {
         player = mapper.readValue(doc.toJson(), Player.class);
      } catch (MongoException | IOException me) {
         System.err.println("Unable to insert due to an error: " + me);
      }
      return player;
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
