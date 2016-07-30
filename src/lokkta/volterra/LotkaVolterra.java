package lokkta.volterra;

public class LotkaVolterra {

    private double _growingRatePrey = 2;
    // Growing rate of prey when there's no predators
    private double _dyingRatePreyPerPredator = 0.1;// Dying rate of prey due to predation
    private double _dyingRatePredator = 1.0; // Dying rate of predators when there's no prey
    private double _growingRatePredatorPerPrey = 0.075;
    // Reproduction rate of predators per 1 prey eaten
    private double _timeDelta = 0.02;
    private double _anzahlBeute = 10;
    private double _anzahlRauber = 5;

    /**
     * start with standard Values
     */
    public LotkaVolterra() {

    }

    public double getAnzahlBeute() {
        return _anzahlBeute;
    }

    public double getAnzahlRauber() {
        return _anzahlRauber;
    }

    public void next() {
        double xk_1 = deltaBeute(_anzahlBeute, _anzahlRauber);
        double yk_1 = deltaRauber(_anzahlBeute, _anzahlRauber);
        double xk_2 = deltaBeute(_anzahlBeute + xk_1, _anzahlRauber + yk_1);
        double yk_2 = deltaRauber(_anzahlBeute + xk_1, _anzahlRauber + yk_1);

        _anzahlBeute = _anzahlBeute + (xk_1 + xk_2) / 2;
        _anzahlRauber = _anzahlRauber + (yk_1 + yk_2) / 2;
    }

    private double deltaBeute(double anzahlBeute, double anzahlRauber) {
        // Calculate the rate of population change
        double beuteVeranderungsRate = anzahlBeute * (_growingRatePrey - _dyingRatePreyPerPredator * anzahlRauber);

        // Calculate the prey population change
        return beuteVeranderungsRate * _timeDelta;
    }

    private double deltaRauber(double anzahlBeute, double anzahlRauber) {
        // Calculate the rate of population change
        double rauberVeranderungsRate = anzahlRauber * (_growingRatePredatorPerPrey * anzahlBeute - _dyingRatePredator);

        // Calculate the prey population change
        return rauberVeranderungsRate * _timeDelta;
    }

    public double getMaxPop() {
        return Math.max(_anzahlRauber, _anzahlBeute);
    }

    public double getGrowingRatePrey() {
        return _growingRatePrey;
    }

    public void setGrowingRatePrey(double alpha) {
        _growingRatePrey = alpha;
    }

    public double getDyingRatePreyPerPredator() {
        return _dyingRatePreyPerPredator;
    }

    public void setDyingRatePreyPerPredator(double beta) {
        _dyingRatePreyPerPredator = beta;
    }

    public double getDyingRatePredator() {
        return _dyingRatePredator;
    }

    public void setDyingRatePredator(double gamma) {
        _dyingRatePredator = gamma;
    }

    public double getGrowingRatePredatorPerPrey() {
        return _growingRatePredatorPerPrey;
    }

    public void setGrowingRatePredatorPerPrey(double delta) {
        _growingRatePredatorPerPrey = delta;
    }

    public double getTimeDelta() {
        return _timeDelta;
    }

    public void setTimeDelta(double timeDelta) {
        _timeDelta = timeDelta;
    }

}
