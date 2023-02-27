package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.NoArgsConstructor;
import model.Player;
import model.PlayerStats;
import static db.MongoJDBCConnection.database;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.IOException;
import java.util.UUID;

public class PlayerStatsDao {
   private ObjectMapper mapper ;

   private MongoCollection<Document> playerCollection;
   private MongoCollection<Document> playerStatsCollection;

   public PlayerStatsDao() {
      mapper = new ObjectMapper();
   }

   public PlayerStats getPlayerStatsById(String id){
      playerStatsCollection = database.getCollection("playerStats");
      Bson filter1 = Filters.eq("_id", id);
      Document doc = playerStatsCollection.find(filter1).first();
      PlayerStats playerStats = new PlayerStats();
      try {
         playerStats = mapper.readValue(doc.toJson(), PlayerStats.class);
      } catch (MongoException | IOException me) {
         System.err.println("Unable to insert due to an error: " + me);
      }
      return playerStats;
   }

   public String insertPlayersStats(String playerId, int runsScored, int ballsPlayed, int ballsThrown, int runsGiven, int wicketsTaken, String scoreboardId){
      playerStatsCollection = database.getCollection("playerStats");
      Document demo = new Document()
              .append("_id", UUID.randomUUID().toString())
              .append("scoreboardId", scoreboardId)
              .append("playerId", playerId)
              .append("runsScored", runsScored)
              .append("ballsPlayed", ballsPlayed)
              .append("ballsThrown", ballsThrown)
              .append("runsGiven", runsGiven)
              .append("wicketsTaken", wicketsTaken);
      try {
         InsertOneResult result = playerStatsCollection.insertOne(demo);
         //System.out.println(result);
         return result.getInsertedId().asString().getValue();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return "Null";
   }

   public void updatePlayersBattingStats(String playerId, int runs, int balls, String scoreboardId){
      playerStatsCollection = database.getCollection("playerStats");
      Bson filter1 = Filters.eq("playerId", playerId);
      playerStatsCollection.updateMany(filter1 ,Updates.combine(
              Updates.set("runsScored", runs),
              Updates.set("ballsPlayed", balls)
      ));
   }

   public void updatePlayersBowlingStats(String playerId, int balls, int runs, int wickets, String scoreboardId){
      playerStatsCollection = database.getCollection("playerStats");
      Bson filter1 = Filters.eq("playerId", playerId);
      UpdateResult result, result1, result2;

      playerStatsCollection.updateMany(filter1 ,Updates.combine(
              Updates.set("runsGiven", runs),
              Updates.set("ballsThrown", balls),
              Updates.set("wicketsTaken", wickets)
      ));
   }
}
