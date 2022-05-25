package edaii.simcovid.app;

import edaii.simcovid.game.CovidLogic;
import edaii.simcovid.game.VirusParameters;
import edaii.simcovid.ui.CovidGameWindow;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CovidGame {

    public static final int ROWS = 4;
    public static final int COLUMNS = 4;
    public static final int MSECONDS_PER_DAY = 3000;
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

        // Inicializa la población
        List<Integer> population = initializePopulation(ROWS, COLUMNS);

        while (true) {
            // Avanza el día


            // Convierte la población en una lista de enteros
            // representando estados validos para edaii.simcovid.ui.Cell
            final List<Integer> cellStates = population
                    .stream()
                    .collect(Collectors.toUnmodifiableList());

            // Repesenta el estado
            game.setCellStates(cellStates);
            population = covidLogic.advanceDay(population);
            // Pasa el día
            Thread.sleep(MSECONDS_PER_DAY);
        }

    }

    private static List<Integer> initializePopulation(int rows, int columns) {
        return IntStream.range(0, rows*columns)
                .mapToObj(n -> n==6?1:0)
                .toList();
    }
}
