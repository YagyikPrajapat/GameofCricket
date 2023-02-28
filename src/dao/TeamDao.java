package dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import model.Team;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import static db.MongoJDBCConnection.database;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
public class TeamDao {
   private static ObjectMapper mapper ;
   private static MongoCollection<Document> teamCollection;

   public TeamDao(){
      mapper = new ObjectMapper();
   }

   public static Team findByName(String teamName) {
      teamCollection = database.getCollection("team");
      Bson filter1 = Filters.eq("teamName", teamName);
      Document doc = teamCollection.find(filter1).first();
      Team team = new Team();
      try {
         team = mapper.readValue(doc.toJson(), Team.class);
      } catch (MongoException | IOException me) {
         System.err.println("Unable to insert due to an error: " + me);
      }
      return team;
   }

   public void insertTeam(String teamName){
      Document demo = new Document().append("_id", UUID.randomUUID().toString())
              .append("teamName", teamName)
              .append("players", Arrays.asList(new String[]{}));
      try {
         teamCollection = database.getCollection("team");
         InsertOneResult result = teamCollection.insertOne(demo);
         System.out.println("Success! Inserted document id: " + result.getInsertedId());
      } catch (MongoException me) {
         System.err.println("Unable to insert due to an error: " + me);
      }
   }

   public static String getBatsmanId(String teamId, int strike){
      teamCollection = database.getCollection("team");
      Bson filter1 = Filters.eq("_id", teamId);
      Document doc = teamCollection.find(filter1).first();
      List<String> players = doc.getList("players", String.class);
      return players.get(strike);
   }

   public String getBowlerId(String teamId, int bowler){
      teamCollection = database.getCollection("team");
      Bson filter1 = Filters.eq("_id", teamId);
      Document doc = teamCollection.find(filter1).first();
      List<String> players = doc.getList("players", String.class);
      return players.get(bowler);
   }

   public void populatePlayerList(){
      try{
         teamCollection = database.getCollection("team");
         Bson filter1 = Filters.eq("_id", "ce21da8f-fea5-46ce-990e-fbcaf888bacf");
         MongoCollection<Document> playerCollection = database.getCollection("player");

         Bson filter = Filters.eq("teamId", "ce21da8f-fea5-46ce-990e-fbcaf888bacf");
         FindIterable<Document> documents = playerCollection.find(filter);

         MongoCursor<Document> cursor = documents.iterator();
         ArrayList<String> arr = new ArrayList<>();

         while (cursor.hasNext()) {
            arr.add(cursor.next().get("_id").toString());
         }
         UpdateResult result;
         Bson update = Updates.set("players", arr);
         result = teamCollection.updateOne(filter1, update);
      }catch (Exception e){
         e.printStackTrace();
      }
   }


}
