package Services;

public class ImpostoBrasil {
    public double imposto(double quantia){
        if (quantia <= 100){
            return quantia * 0.20;
        }
        else {
            return quantia * 0.15;
        }
    }
}
