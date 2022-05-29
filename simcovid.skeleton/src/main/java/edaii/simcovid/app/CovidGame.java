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
    public static final int MSECONDS_PER_DAY = 1000;
    public static final int VIRUS_TRANSMISSION_PERCENT = 5;
    public static final int VIRUS_TIMELIFE_DAYS = 14;

    public static void main(String[] args) throws InterruptedException {

        final CovidGameWindow game = new CovidGameWindow();
        game.setRowsAndColumns(ROWS, COLUMNS);

        final VirusParameters virusParameters = new VirusParameters(
                VIRUS_TRANSMISSION_PERCENT,
                VIRUS_TIMELIFE_DAYS
        );

        final CovidLogic covidLogic = new CovidLogic(virusParameters);

        List<Integer> population = initializePopulation(ROWS, COLUMNS);

        while (true) {
            final List<Integer> cellStates = population
                    .stream()
                    .collect(Collectors.toUnmodifiableList());
            game.setCellStates(cellStates);
            population = covidLogic.advanceDay(population, MSECONDS_PER_DAY , ROWS,COLUMNS);
            Thread.sleep(MSECONDS_PER_DAY);
        }

    }
    private static List<Integer> initializePopulation(int rows, int columns) {
        return IntStream.range(0, rows*columns)
                .mapToObj(n -> n==6 || n==8?1:0)
                .toList();
    }
}
