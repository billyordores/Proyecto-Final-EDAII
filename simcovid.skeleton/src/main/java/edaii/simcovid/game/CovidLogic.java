package edaii.simcovid.game;

import com.sun.source.util.TaskEvent;
import edaii.simcovid.app.CovidLife;
import jdk.jfr.Enabled;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CovidLogic {

    private final VirusParameters parameters;
    final List<Integer> newList = new ArrayList<>(1000);
    final List<CovidLife> covidList = new ArrayList<>(1000);

    public CovidLogic(VirusParameters parameters) {
        this.parameters = parameters;
    }
//
    public List<Integer> advanceDay(List<Integer> grid, int dayTime, int rows, int columns) {
            if(newList.isEmpty()){
                IntStream.range(0, grid.size()).forEach(n -> newList.add(n, 0));
                IntStream.range(0, grid.size()).forEach(n -> covidList.add( null));
            }

            IntStream.range(0, grid.size()).forEach(n -> {
                if(grid.get(n) == 1){
                    newList.set(n, 1);
                    if(covidList.isEmpty()){
                        covidList.set(n, new CovidLife(n,parameters.lifetimeInDays));
                    }else{
                        if(existElement(n)){
                            if (covidList.get(n).getCovidLifeTime() == 0){
                                newList.set(n, 2);
                            }else{
                                covidList.get(n).setCovidLifeTime(covidList.get(n).getCovidLifeTime()-1);
                            }
                        }else{
                            covidList.set(n, new CovidLife(n,parameters.lifetimeInDays));
                        }
                    }

                    if( (int) (Math.random()*10+1) <= parameters.transmissionPercent ){

                        if((int) (Math.random()*4+1) == 1){//derecha
                            if(n+1>0 && n+1<grid.size() && newList.get(n+1)!=2){
                                newList.set(n+1, 1);
                            }
                        }else if((int) (Math.random()*4+1) ==2){//izquierda
                            if(n-1>0 && n-1<grid.size() && newList.get(n-1)!=2){
                                newList.set(n-1, 1);
                            }
                        }else if((int) (Math.random()*4+1) == 3){//abajo
                            if(n+columns>0 && n+columns<grid.size() && newList.get(n+columns)!=2){
                                newList.set(n+columns, 1);
                            }
                        }
                        else if((int) (Math.random()*4+1) == 4){//arriba
                            if(n-columns>0 && n-columns<grid.size() && newList.get(n-columns)!=2){
                                newList.set(n-columns, 1);
                            }
                        }
                    }
                }

            });
            newList.stream().forEach(n -> System.out.println(n));
            return newList;
    }
    public void changeAdvance(int value){
        System.out.println();

    }
    public CovidLife foundByIndex(int index){
        final List<CovidLife> covidList2 = new ArrayList<>();
        covidList.forEach(element -> {
            if(element.getIndex() == index){
                covidList2.add(index, element);
            }
        });
        return covidList2.get(index);
    }
    public boolean existElement(int index){
        return covidList.get(index)!=null;
        }
}
