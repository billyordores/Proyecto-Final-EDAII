package edaii.simcovid.game;

public class VirusParameters {
    final int transmissionPercent;
    final int lifetimeInDays;

    /**
     * Virus parameters
     *
     * @param transmissionPercent Percentage of transmissibility
     * @param lifetimeInDays      Life-time in a host until get immunity
     */
    public VirusParameters(int transmissionPercent, int lifetimeInDays) {
        this.transmissionPercent = transmissionPercent;
        this.lifetimeInDays = lifetimeInDays;
    }

}
