package edaii.simcovid.app;

import edaii.simcovid.game.CovidLogic;

public class CovidLife {
    int index;
    int covidLifeTime;

    public CovidLife(int index, int covidLifeTime){
        this.covidLifeTime = covidLifeTime;
        this.index = index;
    }
    public int getCovidLifeTime(){
        return covidLifeTime;
    }
    public void setCovidLifeTime(int newCovidLifeTime){
        covidLifeTime = newCovidLifeTime;
    }
    public int getIndex (){
        return index;
    }
}
