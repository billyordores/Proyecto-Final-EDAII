package edaii.simcovid.app;

import edaii.simcovid.game.CovidLogic;

import java.util.ArrayList;
import java.util.List;

public class CovidLife {

    //final ArrayList<Integer> lifeC = new ArrayList<>(1);
    final ArrayList<Integer> lifeC;
    final ArrayList<Integer> indexC;
    public CovidLife(int index, int covidLifeTime){
        lifeC =  new ArrayList<Integer>(List.of(index));
        indexC = new ArrayList<>(List.of(covidLifeTime));
    }
    public int getCovidLifeTime(){
        return lifeC.get(0);
    }
    public void setCovidLifeTime(int newCovidLifeTime){
        lifeC.stream().map(n -> newCovidLifeTime).toList();
    }
    public int getIndex (){
        return indexC.get(0);
    }
    public void setIndex (int index){
        indexC.stream().map(n -> index).toList();
    }
}
