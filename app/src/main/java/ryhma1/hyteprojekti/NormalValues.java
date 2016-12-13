package ryhma1.hyteprojekti;

/**
 * Created by gibo on 12/13/16.
 */

public class NormalValues {

    double _norm1;
    double _norm2;

    NormalValues(){
    }

    NormalValues(double norm1, double norm2){
        this._norm1 = norm1;
        this._norm2 = norm2;
    }

    double getNorm1(){
        return this._norm1;
    }

    public void setNorm1(double norm1){
        this._norm1 = norm1;
    }

    double getNorm2(){
        return this._norm2;
    }
    public void setNorm2(double norm2){
        this._norm2 = norm2;
    }

}
