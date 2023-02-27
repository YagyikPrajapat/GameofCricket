package services;

import enums.TossChoice;

public  class TossService {

    public static boolean toss(TossChoice tossChoice){
         // TODO Add logic to choose random
        int tossResult=(int)(Math.random()*2);
        TossChoice randomTossChoice = (tossResult == 0) ? (TossChoice.HEADS) : (TossChoice.TAILS);
        return tossChoice.equals(randomTossChoice);
    }
}
