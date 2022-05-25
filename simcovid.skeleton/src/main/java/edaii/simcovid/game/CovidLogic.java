package edaii.simcovid.game;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class CovidLogic {

    private final VirusParameters parameters;

    public CovidLogic(VirusParameters parameters) {
        this.parameters = parameters;
    }
//
    public List<Integer> advanceDay(List<Integer> grid) {
            final List<Integer> newList = new ArrayList<>(grid.size());
            IntStream.range(0, grid.size()).forEach(n -> newList.add(n, 0));

            IntStream.range(0, grid.size()).forEach(n -> {
                if(grid.get(n) == 1){
                    newList.set(n, 1);
                    newList.set(n-1, 1);
                    newList.set(n+1, 1);
                }

            });
            newList.stream().forEach(n -> System.out.println(n));
            return newList;
    }
    public void changeAdvance(int value){
        System.out.println();

    }

}
