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

    //empty constructor
    public User(){

    }
    //constructor
    public User(int id, String name, String lastname, int height, int weight, int points){
        this._id = id;
        this._name = name;
        this._lastname = lastname;
        this._height = height;
        this._weight = weight;
        this._points = points;
    }
    //constructor
    public User(String name, String lastname, int height, int weight, int points){
        this._name = name;
        this._lastname = lastname;
        this._height = height;
        this._weight = weight;
        this._points = points;
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

}
