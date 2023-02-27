package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@NoArgsConstructor
public class Player {
    private String _id;
    private String teamId;
    private String name;
    public Player(String id, String name) {
        this._id = id;
        this.name = name;
    }
}
