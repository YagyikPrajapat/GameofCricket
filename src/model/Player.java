package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
public class Player {
    private String _id;
    private String name;
    private String teamId;

    public Player(){}
    public Player(String id, String name) {
        this._id = id;
        this.name = name;
    }
}
