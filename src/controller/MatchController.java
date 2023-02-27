package controller;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import db.MongoJDBCConnection;
import format.CricketFormat;
import enums.Format;
import lombok.NoArgsConstructor;

public class MatchController {

   private MongoJDBCConnection mongoJDBCConnection;
   public MatchController(){
      mongoJDBCConnection = new MongoJDBCConnection();
   }
   public CricketFormat matchFormatChoice(String expectedFormat) {
        Format format = Format.valueOf(expectedFormat);
        switch(format){
            case T20:
                return new T20Controller();
            case ODI:
                System.out.println("ODI");
            case TEST:
                System.out.println("TEST");
            default:
                throw new IllegalArgumentException("Unknown enums.Format");
        }
    }
}