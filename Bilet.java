/**
 * biletul de imbarcare
 * @author Busuioc Gabriel-Razvan 324 CB
 */
public class Bilet {
    private String tipBilet;
    private boolean imbarcarePrioritara;
    private boolean nevoiSpeciale;
    private int punctajBilet;

    /**
     *
     * @param tipBilet economy/business/premium
     * @param imbarcarePrioritara
     * @param nevoiSpeciale
     */
    public Bilet(String tipBilet, boolean imbarcarePrioritara, boolean nevoiSpeciale){
        this.tipBilet = tipBilet;
        this.imbarcarePrioritara = imbarcarePrioritara;
        this. nevoiSpeciale = nevoiSpeciale;
        this.punctajBilet = 0;
        switch (this.tipBilet) {
            case "p":
                this.punctajBilet += 20;
                break;
            case "b":
                this.punctajBilet += 35;
                break;
        }
        if(this.imbarcarePrioritara){
            punctajBilet += 30;
        }
        if(this.nevoiSpeciale){
            punctajBilet += 100;
        }
    }

    /**
     *
     * @return punctajul aferent biletului
     */
    public int getPunctaj(){
        return this.punctajBilet;
    }
}