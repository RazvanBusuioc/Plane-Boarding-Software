import java.util.ArrayList;

/**
 * clasa auxiliara ce contine un vector cu toate grupurile de pasageri
 * @author Busuioc Gabriel-Razvan 324   CB
 */
public class auxArray{
    ArrayList<Grup> vectorAux;
    /*idx - numarul de grupui din vector*/
    private int idx;

    /**
     *initializeaza vectorul de Grupuri
     */
    public auxArray(){
        vectorAux = new ArrayList<>();
        this.idx = 0;
    }

    /**
     *
     * @param grup grupul ce trebuie adaugat in vector
     */
    public void addGrup(Grup grup){
        this.vectorAux.add(grup);
        this.idx++;
    }

    /**
     *
     * @param nume numele grupului cautat in vector
     * @return indexului grupului de nume ""
     */
    public int getGrupIdxbyNume(String nume){
        int i;
        for(i = 0; i < idx; i++){
            if((vectorAux.get(i)).getNumeGrup().equals(nume)){
                return i;
            }
        }
        return -1;
    }

    /**
     *
     * @param idx indexul grupului cautat in vector
     * @return grupul aferent indexului dat ca parametru
     */
    public Grup getGrupByIdx(int idx){
        return vectorAux.get(idx);
    }

    /**
     *
     * @return numarul de grupuri din vector
     */
    public int getIdx(){
        return this.idx;
    }

    /**
     *
     * @param nume numele grupului cautat in vector
     * @return grupul aferent numelui dat ca parametru
     */
    public Grup getGrupByNume(String nume){
        if(vectorAux.isEmpty()){
            return null;
        }
        Grup grup = new Grup();
        int i;
        for(i = 0; i < this.idx; i++){
            if((vectorAux.get(i)).getNumeGrup().equals(nume)){
                grup = vectorAux.get(i);
                //vectorAux.remove(i);
               // this.idx--;
            }
        }   
        return grup;
    }
}
