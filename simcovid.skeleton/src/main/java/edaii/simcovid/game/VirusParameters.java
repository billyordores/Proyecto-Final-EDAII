package edaii.simcovid.game;

public class VirusParameters {
    final int transmissionPercent;
    final int lifetimeInDays;
    final int transmissionPercentMask;
    final int porcentToDead;

    /**
     * Virus parameters
     *
     * @param transmissionPercent Percentage of transmissibility
     * @param lifetimeInDays      Life-time in a host until get immunity
     */
    public VirusParameters(int transmissionPercent, int lifetimeInDays, int transmissionPercentMask, int porcentToDead) {
        this.transmissionPercent = transmissionPercent;
        this.lifetimeInDays = lifetimeInDays;
        this.transmissionPercentMask = transmissionPercentMask;
        this.porcentToDead = porcentToDead;
    }

}
