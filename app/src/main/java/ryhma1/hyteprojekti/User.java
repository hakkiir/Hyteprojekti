package ryhma1.hyteprojekti;

/**
 * Created by Iiro on 17.11.2016.
 */

public class User {

    int _id;
    String _name;
    String _lastname;
    int _height;
    int _weight;
    int _points;
    int _level;
    double _bound1;
    double _bound2;

    //empty constructor
    public User(){

    }
    //constructor
    public User(int id, String name, String lastname, int height, int weight, int points, int level, double bound1, double bound2){
        this._id = id;
        this._name = name;
        this._lastname = lastname;
        this._height = height;
        this._weight = weight;
        this._points = points;
        this._level = level;
        this._bound1 = bound1;
        this._bound2 = bound2;
    }
    //constructor
    public User(String name, String lastname, int height, int weight, int points, int level, double bound1, double bound2){
        this._name = name;
        this._lastname = lastname;
        this._height = height;
        this._weight = weight;
        this._points = points;
        this._level = level;
        this._bound1 = bound1;
        this._bound2 = bound2;
    }



    //getting id
    public int getUserID(){
        return this._id;
    }
    //setting id
    public void setUserID(int id){
        this._id = id;
    }
    //getting firstname
    public String getFirstName(){
        return this._name;
    }
    //setting firstname
    public void setFirstName(String name){
        this._name = name;
    }
    //getting lastname
    public String getLastName(){
        return this._lastname;
    }
    //setting lastname
    public void setLastName(String lastname){
        this._lastname = lastname;
    }
    //getting height
    public int getHeight(){
        return this._height;
    }
    //setting height
    public void setHeight(int height){
        this._height = height;
    }
    //getting weight
    public int getWeight(){
        return this._weight;
    }
    //setting weight
    public void setWeight(int weight){
        this._weight = weight;
    }
    //getting user points
    public int getUserPoints(){
        return this._points;
    }
    //setting user points
    public void setUserPoints(int points){
        this._points = points;
    }
    //get level
    public int getLevel(){
        return this._level;
    }
    //set level
    public void setUserLevel(int level){
        this._level = level;
    }
    //get bound1
    public double getBound1(){
        return this._bound1;
    }
    //setting bound1
    public void setBound1(double bound1){
        this._bound1 = bound1;
    }
    //getting bound2
    public double getBound2(){
        return this._bound2;
    }
    //setting bound2
    public void setBound2(double bound2){
        this._bound2 = bound2;
    }

}
