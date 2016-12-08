package ryhma1.hyteprojekti;

/**
 * Created by Iiro on 17.11.2016.
 */

public class Challenge {

    int _id;
    String _name;
    int _activity;
    int _points;
    int _level;
    int _duration;
    String _clock;

    //empty constructor
    public Challenge(){

    }

    //constructor
    public Challenge(int id, String name, int activity, int points, int level, int duration, String clock){
        this._id = id;
        this._name = name;
        this._activity = activity;
        this._points = points;
        this._level = level;
        this._duration = duration;
        this._clock = clock;
    }
    //constructor
    public Challenge(String name, int activity, int points, int level, int duration, String clock){
        this._name = name;
        this._activity = activity;
        this._points = points;
        this._level = level;
        this._duration = duration;
        this._clock = clock;
    }



    //getting id
    public int getChallengeID(){
        return this._id;
    }
    //setting id
    public void setChallengeID(int id){
        this._id = id;
    }
    //getting challenge name
    public String getChallengename(){
        return this._name;
    }
    //setting challenge name
    public void setChallengeName(String name){
        this._name = name;
    }
    //getting activity
    public int getActivity(){
        return this._activity;
    }
    //setting activity
    public void setActivity(int activity){
        this._activity = activity;
    }
    //getting challenge points
    public int getChallengePoints(){
        return this._points;
    }
    //setting challenge points
    public void setChallengePoints(int points){
        this._points = points;
    }
    //getting challenge level
    public int getChallengeLevel() {
        return this._level;
    }
    //Setting challenge level
    public void setChallengeLevel(int level) {
        this._level = level;
    }
    //get duration
    public int getDuration(){
        return this._duration;
    }
    //set duration
    public void setDuration(int duration){
        this._duration = duration;
    }
    //get clock
    public String getClock(){
        return this._clock;
    }
    //set clock
    public void setClock(String clock){
        this._clock = clock;
    }
}
