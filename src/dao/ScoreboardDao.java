package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import model.Scoreboard;
import org.bson.Document;
import org.bson.conversions.Bson;
import services.InningService;

import static db.MongoJDBCConnection.database;

import java.io.IOException;
import java.util.*;

public class ScoreboardDao {
   private ObjectMapper mapper;

   private MongoCollection<Document> scoreboardCollection;

   public ScoreboardDao() {
      mapper = new ObjectMapper();
   }

   public Scoreboard getScoreboardById(String scoreboardId) {
      scoreboardCollection = database.getCollection("scoreboard");
      Bson filter1 = Filters.eq("_id", scoreboardId);
      Document doc = scoreboardCollection.find(filter1).first();
      Scoreboard scoreboard = new Scoreboard();
      try {
         scoreboard = mapper.readValue(doc.toJson(), Scoreboard.class);
      } catch (MongoException | IOException me) {
         System.err.println("Unable to insert due to an error: " + me);
      }
      return scoreboard;
   }

   public String saveInningsData(InningService firstInnings, InningService secondInnings) {
      Document demo = new Document()
              .append("_id", UUID.randomUUID().toString())
              .append("firstInningTotalRuns", firstInnings.getTotalRuns())
              .append("firstInningWicketsLoss", firstInnings.getTotalWickets())
              .append("secondInningTotalRuns", secondInnings.getTotalRuns())
              .append("secondInningWicketsLoss", secondInnings.getTotalWickets())
              .append("firstInningTeamName", firstInnings.getBattingTeam().getTeamName())
              .append("secondInningTeamName", secondInnings.getBattingTeam().getTeamName())
              .append("playerStatsFirstInning", Arrays.asList(new String[]{}))
              .append("playerStatsSecondInning", Arrays.asList(new String[]{}));

      try {
         scoreboardCollection = database.getCollection("scoreboard");
         InsertOneResult result = scoreboardCollection.insertOne(demo);
         return result.getInsertedId().asString().getValue();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return "Null";
   }

   public void updateInningsData(List playerStatsFirstInning, List playerStatsSecondInning, String scoreboardId) {
      scoreboardCollection = database.getCollection("scoreboard");
      Bson filter1 = Filters.eq("_id", scoreboardId);
      scoreboardCollection.updateMany(filter1, Updates.combine(
              Updates.set("playerStatsFirstInning", playerStatsFirstInning),
              Updates.set("playerStatsSecondInning", playerStatsSecondInning)
      ));
   }
}
