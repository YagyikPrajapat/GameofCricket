package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class Team {
    private String _id;

    private String teamName;
    private ArrayList<String> players = new ArrayList<>();

    public Team(String teamName){
        this.players = new ArrayList<>();
       this.teamName = teamName;
    }

}
