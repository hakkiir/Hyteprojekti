package ryhma1.hyteprojekti;

/**
 * Created by Iiro on 22.11.2016.
 */

public class Data {

    int _userID;
    double _bloodGlucose;

    Data() {
    }

    Data(int id, double bloodGlucose){
        this._userID = id;
        this._bloodGlucose = bloodGlucose;
    }

    //getting id
    int getDataUserID(){
        return this._userID;
    }
    //setting id
    public void setDataUserID(int userID){
        this._userID = userID;
    }

    //getting blood glucose data
    double getBloodGlucose(){
        return _bloodGlucose;
    }
    //setting blood glucose data
    public void setBloodGlucose(double bloodGlucose){
        this._bloodGlucose = bloodGlucose;
    }

}
