package edaii.simcovid.app;

import edaii.simcovid.game.CovidLogic;

import java.util.ArrayList;
import java.util.List;

public class CovidLife {
    final int index;
    final ArrayList<Integer> lifeC = new ArrayList<>(1);

    public CovidLife(int index, int covidLifeTime){
        lifeC.add(0, covidLifeTime);
        this.index = index;
    }
    public int getCovidLifeTime(){
        return lifeC.get(0);
    }
    public void setCovidLifeTime(int newCovidLifeTime){
        lifeC.set(0, newCovidLifeTime);
    }
    public int getIndex (){
        return index;
    }
}
