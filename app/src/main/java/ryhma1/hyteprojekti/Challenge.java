package ryhma1.hyteprojekti;

/**
 * Created by Iiro on 17.11.2016.
 */

public class Challenge {

    int _id;
    String _name;
    int _activity;
    float _bound1;
    float _bound2;
    int _points;

    //empty constructor
    public Challenge(){

    }

    //constructor
    public Challenge(int id, String name, int activity, float bound1, float bound2, int points){
        this._id = id;
        this._name = name;
        this._activity = activity;
        this._bound1 = bound1;
        this._bound2 = bound2;
        this._points = points;
    }
    //constructor
    public Challenge(String name, int activity, float bound1, float bound2, int points){
        this._name = name;
        this._activity = activity;
        this._bound1 = bound1;
        this._bound2 = bound2;
        this._points = points;
    }

    //getting id
    public int getID(){
        return this._id;
    }
    //setting id
    public void setID(int id){
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
    //getting bound1
    public float getBound1(){
        return this._bound1;
    }
    //setting bound1
    public void getBound1(float bound1){
        this._bound1 = bound1;
    }
    //getting bound2
    public float getBound2(){
        return this._bound2;
    }
    //setting bound2
    public void getBound2(float bound2){
        this._bound2 = bound2;
    }
    //getting challenge points
    public int getChallengePoints(){
        return this._points;
    }
    //setting challenge points
    public void setChallengePoints(int points){
        this._points = points;
    }


}
