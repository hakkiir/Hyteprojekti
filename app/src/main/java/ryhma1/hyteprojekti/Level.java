package ryhma1.hyteprojekti;

/**
 * Created by Iiro on 22.11.2016.
 */

public class Level {

    int _id;
    int _limit;

    Level() {
    }

    Level(int id, int  limit){
        this._id = id;
        this._limit = limit;
    }

    //getting levelID
    int getLevelID(){
        return this._id;
    }
    //setting levelID
    public void setLevelID(int id, int limit){
        this._id = id;
        this._limit = limit;
    }
    //getting level limit
    int getLevelLimit(){
        return this._limit;
    }
    //setting level limit
    public void setLevelLimit(int id, int limit){
        this._id = id;
        this._limit = limit;
    }
}
