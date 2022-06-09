package edaii.simcovid.game;

import com.sun.source.util.TaskEvent;
import edaii.simcovid.app.CovidLife;
import jdk.jfr.Enabled;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class CovidLogic {

    private final VirusParameters parameters;
    final List<Integer> newList = new ArrayList<>(1000);
    final List<CovidLife> covidList = new ArrayList<>(1000);

    public CovidLogic(VirusParameters parameters) {
        this.parameters = parameters;
    }
//
    public List<Integer> advanceDay(List<Integer> grid, int dayTime, int rows, int columns, List<CovidLife> covidLife) {
            if(newList.isEmpty()){
                IntStream.range(0, grid.size()).forEach(n -> newList.add(n, 0));
                IntStream.range(0, grid.size()).forEach(n -> covidList.add( null));
            }

            /**List<Integer> name = grid.stream().map(n -> {
                if(n==0){

                }
            }).collect(Collectors.toList());*/

            return IntStream.range(0, grid.size()).mapToObj( i -> {
                if(grid.get(i) == 0){
                    if(i+1<grid.size()){
                        if(grid.get(i+1) == 1 ){
                            if((int) (Math.random()*10+1) == parameters.transmissionPercent){
                                return 1;
                            }else{
                                return 0;
                            }
                        }
                    }
                    if(i-1>0){
                        if(grid.get(i-1) == 1 ){
                            if((int) (Math.random()*10+1) == parameters.transmissionPercent){
                                return 1;
                            }else{
                                return 0;
                            }
                        }
                    }
                }else if(grid.get(i) == 1){
                    if(covidLife.get(i).getIndex() == -1){
                        covidLife.get(i).setIndex(i);
                        covidLife.get(i).setCovidLifeTime(14);
                    }else{
                        if(covidLife.get(i).getCovidLifeTime() == 0){
                            return 2;
                        }else{
                            covidLife.get(i).setCovidLifeTime(covidLife.get(i).getCovidLifeTime() -1);
                        }

                    }
                }
                return 0;
            }).collect(Collectors.toList());




            /**IntStream.range(0, grid.size()).forEach(n -> {
                if(grid.get(n) == 1){
                    newList.set(n, 1);
                    if(covidList.isEmpty()){
                        covidList.set(n, new CovidLife(n,parameters.lifetimeInDays));
                    }else{
                        if(existElement(n)){
                            if (covidList.get(n).getCovidLifeTime() == 0){
                                newList.set(n, 2);
                            }else{//PROBABILIDAD DE MUERTE
                                if(covidList.get(n).getCovidLifeTime() == 7){
                                    if((int) (Math.random()*10+1) == parameters.porcentToDead ){
                                        newList.set(n, 5);
                                    }else{
                                        covidList.get(n).setCovidLifeTime(covidList.get(n).getCovidLifeTime()-1);
                                    }
                                }
                                else{
                                    covidList.get(n).setCovidLifeTime(covidList.get(n).getCovidLifeTime()-1);
                                }
                            }
                        }else{
                            covidList.set(n, new CovidLife(n,parameters.lifetimeInDays));
                        }
                    }
                    if( (int) (Math.random()*10+1) <= parameters.transmissionPercent ){

                        if((int) (Math.random()*4+1) == 1){//derecha
                            if(n+1>0 && n+1<grid.size() && newList.get(n+1)!=2 && newList.get(n+1)!=5){
                                if(newList.get(n+1) == 4){//revisa si tiene mascarilla
                                    if((int) (Math.random()*10+1) <= parameters.transmissionPercentMask){
                                        newList.set(n+1, 1);
                                    }
                                }else{
                                    newList.set(n+1, 1);
                                }

                            }
                        }else if((int) (Math.random()*4+1) ==2){//izquierda
                            if(n-1>0 && n-1<grid.size() && newList.get(n-1)!=2 && newList.get(n-1)!=5){
                                if(newList.get(n-1) == 4){//revisa si tiene mascarilla
                                    if((int) (Math.random()*10+1) <= parameters.transmissionPercentMask){
                                        newList.set(n-1, 1);
                                    }
                                }else{
                                    newList.set(n-1, 1);
                                }
                            }
                        }else if((int) (Math.random()*4+1) == 3){//abajo
                            if(n+columns>0 && n+columns<grid.size() && newList.get(n+columns)!=2 && newList.get(n+columns)!=5){
                                if(newList.get(n+columns) == 4){//revisa si tiene mascarilla
                                    if((int) (Math.random()*10+1) <= parameters.transmissionPercentMask){
                                        newList.set(n+columns, 1);
                                    }
                                }else{
                                    newList.set(n+columns, 1);
                                }
                            }
                        }
                        else if((int) (Math.random()*4+1) == 4){//arriba
                            if(n-columns>0 && n-columns<grid.size() && newList.get(n-columns)!=2 && newList.get(n-columns)!=5){
                                if(newList.get(n-columns) == 4){//revisa si tiene mascarilla
                                    if((int) (Math.random()*10+1) <= parameters.transmissionPercentMask){
                                        newList.set(n-columns, 1);
                                    }
                                }else{
                                    newList.set(n-columns, 1);
                                }
                            }
                        }
                    }
                }else if(grid.get(n) == 4){
                    newList.set(n, 4);
                }else if(grid.get(n) == 0) {
                    if(n+1<grid.size()){//revisión derecha
                        if(newList.get(n+1)==1 ){
                            newList.set(n, 3);
                        }
                    }
                    if(n-1>0){
                        if(newList.get(n-1)==1){
                            newList.set(n, 3);
                        }
                    }
                    if(n-columns>0){
                        if(newList.get(n-columns)==1){
                            newList.set(n, 3);
                        }
                    }
                    if(n+columns<grid.size()){
                        if(newList.get(n+columns)==1){
                            newList.set(n, 3);
                        }
                    }

                }

            });
            newList.stream().forEach(n -> System.out.println(n));
            return newList;*/
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
