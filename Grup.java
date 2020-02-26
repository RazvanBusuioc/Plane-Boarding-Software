import java.util.ArrayList;

/**
 * clasa de grup - single/grup/familie
 * @author Busuioc Gabriel-Razvan 324CB
 */
public class Grup extends Persoana{
    ArrayList<Persoana> vectPersoane;
    private String tipGrup;
    private int punctajGrup;
    private int idx;

    /**
     *
     */
    public Grup(){
        
    }

    /**
     *
     * @param tipGrup familie/grup/singur
     * @param numeGrup
     */
    public Grup( String tipGrup, String numeGrup){
        this.vectPersoane = new ArrayList<>();
        this.punctajGrup = 0;
        this.tipGrup = tipGrup;
        this.numeGrup = numeGrup;
        switch (this.tipGrup) {
            case "f":
                punctajGrup += 10;
                break;
            case "g":
                punctajGrup += 5;
                break;
        }
        this.idx = 0;
    }

    /**
     *
     * @param persoana persoana ce urmeaza sa fie adaugata in vectorul 
     * de persoane grup aferent grupului
     */
    public void addPersoana(Persoana persoana){
        vectPersoane.add(persoana);
        this.punctajGrup += persoana.getPunctajPersoana();
        idx++;
    }
    
    /**
     *
     * @param persoana persoana ce se doreste a fi stearsa din vectorul de persoane
     * al grupului
     */
    public void deletePersoana(Persoana persoana){
        vectPersoane.remove(persoana);
        this.punctajGrup -= persoana.getPunctajPersoana();
        idx--;
    }

    /**
     *
     * @return punctajul total al grupului
     */
    public int getPunctajGrup(){
        return this.punctajGrup;
    }

    /**
     *
     * @return numele grupului
     */
    @Override
    public String getNumeGrup(){
        return this.numeGrup;
    }
    
    /**
     *
     * @return numarul de persoane din vectorul de persoane/pasageri
     */
    public int getIdx(){
        return this.idx;
    }

    /**
     *
     * @param idx indexul din vector al persoanei dorite
     * @return persoana de la indexul dat ca parametru
     */
    public Persoana get(int idx){
        return vectPersoane.get(idx);
    }
}