/**
 * clasa de persoana
 * @author Busuioc Gabriel-Razvan 324 CB
 */
public class Persoana{
    
    private String numePersoana;

    /**
     *
     */
    protected String numeGrup;
    private int varsta;
    private int punctajPersoana;
    Bilet bilet;
    
    /**
     *
     */
    public Persoana(){
    }
    /**
     *
     * @param numeGrup - numele grupului din care face parte persoana
     * @param numePersoana
     * @param varsta
     * @param tipBilet
     * @param imbarcarePrioritara
     * @param nevoiSpeciale
     */
    public Persoana(String numeGrup, String numePersoana, int varsta, String tipBilet,
          boolean imbarcarePrioritara, boolean nevoiSpeciale){
        this.numePersoana = numePersoana;
        this.numeGrup = numeGrup;
        this.varsta = varsta;
        this.punctajPersoana = 0;
        bilet = new Bilet(tipBilet, imbarcarePrioritara, nevoiSpeciale);
        
        /*setarea punctajului de prioritate in functie de varsta*/
        if(this.varsta <= 2){
            this.punctajPersoana += 20;
        }
        else if(this.varsta <= 5){
            this.punctajPersoana += 10;
        }
        else if(this.varsta <= 10){
            this.punctajPersoana += 5;
        }
        else if(this.varsta >= 60){
            this.punctajPersoana += 15;
        }
        
         /*setarea punctajului de prioritate in functie de biletul detinut*/
        punctajPersoana += bilet.getPunctaj();
        
    }

    /**
     *
     * @return returneaza numele grupului din care face parte o persoana
     */
    public String getNumeGrup(){
        return this.numeGrup;
    }
    /**
     *
     * @return punctajul aferent persoanei
     */
    public int getPunctajPersoana(){
        return this.punctajPersoana;
    }
    
    /**
     *
     * @return numele persoanei
     */
    public String getNumePersoana(){
        return this.numePersoana;
    }
}
