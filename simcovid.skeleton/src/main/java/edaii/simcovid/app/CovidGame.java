package edaii.simcovid.app;

import edaii.simcovid.game.CovidLogic;
import edaii.simcovid.game.VirusParameters;
import edaii.simcovid.ui.CovidGameWindow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CovidGame {

    public static final int ROWS = 20;
    public static final int COLUMNS = 20;
    public static final int MSECONDS_PER_DAY = 300;
    public static final int VIRUS_TRANSMISSION_PERCENT = 5;
    public static final int VIRUS_TRANSMISSION_PERCENT_MASK = 2;
    public static final int VIRUS_TIMELIFE_DAYS = 14;
    public static final int PORCENT_TO_DEAD = 1;

    public static void main(String[] args) throws InterruptedException {

        final CovidGameWindow game = new CovidGameWindow();
        game.setRowsAndColumns(ROWS, COLUMNS);

        final VirusParameters virusParameters = new VirusParameters(
                VIRUS_TRANSMISSION_PERCENT,
                VIRUS_TIMELIFE_DAYS,
                VIRUS_TRANSMISSION_PERCENT_MASK,
                PORCENT_TO_DEAD
        );

        final CovidLogic covidLogic = new CovidLogic(virusParameters);

        List<Integer> population = initializePopulation(ROWS, COLUMNS);

        do{
            population = covidLogic.advanceDay(population, MSECONDS_PER_DAY , ROWS,COLUMNS);

            final List<Integer> cellStates = population
                    .stream()
                    .collect(Collectors.toUnmodifiableList());
            game.setCellStates(cellStates);

            Thread.sleep(MSECONDS_PER_DAY);
        }while (CovidGame.timeToEnd(population));

    }
    private static List<Integer> initializePopulation(int rows, int columns) {
        return IntStream.range(0, rows*columns)
                .mapToObj(n ->{
                        if(n==10 ||n==14){
                            return n=1;
                        }else if(n==20 || n==30 || n==15|| n==18){
                            return n=4;
                        }else{
                            return n=0;
                        }
                }).toList();
    }
    private static boolean timeToEnd(List<Integer> grid){
        if(grid.contains(1)){
            return true;
        }else{
            System.out.println("Termino el programa");
            return false;
        }
    }
}
