import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import static java.util.Collections.swap;

/**
 * clasa heapului necesar prioriryQueue
 * @author Busuioc Gabriel-Razvan 324 CB
 */
public class Heap {
    /*heap de persoane/grupuri si un heap auxiliar de punctaje aferent heapului
    de persoane.Se fac aceleasi modificari asupra ambelor heapuri*/
    ArrayList<Persoana> grupHeap;
    ArrayList<Integer> punctajHeap;
    /*idx - numarul de persoane din heap
    listIdx - numarul de comenzi "list" din queue.in*/
    private int idx;
    private int listIdx;
    /*lastList - verifica daca o comanda list a fost executata anterior*/
    private boolean lastList;
    /*nrElementeListate - numara elementele listate pentru a nu afisa " "(whitespace)
    la sfarsitul liniei*/
    private static int nrElementeListate = 0;

    /**
     *
     * @throws IOException
     */
    public Heap() throws IOException{
        grupHeap = new ArrayList<>();
        punctajHeap = new ArrayList<>();
        this.idx = 0;
        this.listIdx = 0;
        File file = new File("queue.out");
        try (FileWriter fr = new FileWriter(file, false); 
                BufferedWriter br = new BufferedWriter(fr)) {
            br.write("");
        }
        catch(IOException ex){
            ex.getMessage();
        }
    }

    /**
     *
     * @param persoana persoana ce trebuie introdusa in heap
     * @param prioritate prioritatea aferenta acesteia
     */
    public void insert(Persoana persoana, int prioritate){ 
        this.grupHeap.add(persoana);
        this.punctajHeap.add(prioritate);
        int idxCurent = this.idx;
        
        /*urcarea in arbore a persoanei/grupului in functie de punctajul
        de prioritate*/
        int idxParinte = ((idxCurent + idxCurent%2) / 2) - 1;
        while( idxCurent != 0 && idxParinte >=0 && 
                (punctajHeap.get(idxParinte)) < (punctajHeap.get(idxCurent))){
            swap(grupHeap,idxCurent,idxParinte);
            swap(punctajHeap,idxCurent,idxParinte);
            idxCurent = idxParinte;
            idxParinte = ((idxCurent + idxCurent%2) / 2) - 1;   
        }
        this.idx++;
    }
    
    /**
     * functia cauta mai intai in fiecare grup numele persoanei date ca parametru
     * (pentru cazul in care se doreste stergerea numai a unei persoane din grup),
     * si o sterge pe aceasta din grupul respectiv, recalculandu-se punctajul.
     * Apoi se cauta in heap grupul ce se doreste a fi sters(cazul in care se
     * doreste stergerea unui grup) si dupa ce se gaseste, acesta este eliminat 
     * din heap si se sorteaza heapul
     * @param persoana persoana sau grupul ce urmeaza a fi sterse din heap
     */
    public void delete(Persoana persoana){
        int i;
        for(i = 0; i < this.idx; i++){
            int j;
            for(j = 0; j < ((Grup)grupHeap.get(i)).getIdx(); j++){
                if(((Grup)grupHeap.get(i)).get(j).getNumePersoana().equals(persoana.getNumePersoana())){
                    ((Grup)grupHeap.get(i)).deletePersoana(persoana);
                    punctajHeap.add(i,punctajHeap.get(i) - persoana.getPunctajPersoana());
                    if(((Grup)grupHeap.get(i)).getIdx() == 0){
                        swap(grupHeap, i, idx - 1);
                        swap(punctajHeap, i, idx - 1);
                        grupHeap.remove(idx - 1);
                        punctajHeap.remove(idx - 1);
                        idx--;
                        sortHeap(i);
                        return;
                    }
                    sortHeap(i);
                    return;
                }
            }   
        }
         for(i = 0; i < this.idx; i++){
           // System.out.println(persoana.getNumeGrup());
            if(grupHeap.get(i).getNumeGrup().equals(persoana.getNumeGrup())){
                System.out.println(";;;;");
                swap(grupHeap, i, idx - 1);
                swap(punctajHeap, i, idx - 1);
                grupHeap.remove(idx - 1);
                punctajHeap.remove(idx - 1);
                this.idx--;
                sortHeap(i);
                return;
            }
        }
    }

    /**
     *se inlocuieste rootul cu ultimul element adaugat, apelandu-se apoi functia
     * de sortare a heapului
     */
    public void embark(){
        if(grupHeap.isEmpty()){
            return;
        }
        swap(grupHeap, 0, idx - 1);
        swap(punctajHeap, 0, idx - 1);
        grupHeap.remove(idx - 1);
        punctajHeap.remove(idx - 1);
        this.idx--;
        int idxCurent = 0;
        sortHeap(idxCurent);
        
    }

    /**
     * se compara parintele cu cei 2 fii si se fac schimbarile necesare,
     * functia apelandu-se recursiv apoi
     * @param idxCurent indexul de la care porneste sortarea
     */
    public void sortHeap(int idxCurent){
        int idxLeft = (idxCurent * 2) + 1;
        int idxRight = (idxCurent * 2) + 2;
        int idxLargest;
        if((idxLeft < this.idx) && (punctajHeap.get(idxLeft)) > 
                (punctajHeap.get(idxCurent))){
            idxLargest = idxLeft;
        }
        else{
            idxLargest = idxCurent;
        }
        if((idxRight < this.idx) && (punctajHeap.get(idxRight)) > 
                (punctajHeap.get(idxLargest))){
            idxLargest = idxRight;
        }
        if(idxLargest != idxCurent){
            swap(grupHeap, idxCurent, idxLargest);
            swap(punctajHeap, idxCurent, idxLargest);
            sortHeap(idxLargest);
        }
    }
    /**
     *adauga in queue.out numele grupurilor din heap
     * functia apeleaza o functie auxiliara pentru a ii transmite un 
     * parametru de index si fisierul in care trebuie sa scrie
     */
    public void list(){
        File file = new File("queue.out");
        if(listIdx == 0){
            /*cazul in care nu s-a mai dat niciun list si trebuie 
            golit queue.out*/
            try (FileWriter fr = new FileWriter(file, false); 
                    BufferedWriter br = new BufferedWriter(fr)) {
                br.write("");
            }
            catch(IOException ex){
                ex.getMessage();
            }
        }
        try (FileWriter fileWriter = new FileWriter(file, true); 
                BufferedWriter bufferWriter = new BufferedWriter(fileWriter)) {
            if(this.lastList){
                /*cazul in care a mai fost dat un list anterior,
                se trece pe o linie noua pentru listul actual*/
                bufferWriter.write("\r\n");
            }
            nrElementeListate = 0;
            auxList(0, bufferWriter);
            this.lastList = true;
        }
        catch(IOException ex){
            ex.getMessage();
        }
        this.listIdx++;
        
    }

    /**
     *
     * @param idxCurent indexul heapuluibuferWriterde la care se face listarea
     * @param bufferWriter
     * @throws java.io.IOException
     */
    public void auxList(int idxCurent, BufferedWriter bufferWriter) throws IOException{
        if(idxCurent >= idx){
            /*cazul in care s-a trecut de finalul heapului cu listarea*/
            return;
        }
        
        int idxStanga = (idxCurent * 2) + 1;
        int idxDreapta = (idxCurent * 2) + 2;
        bufferWriter.write((grupHeap.get(idxCurent)).getNumeGrup() );
        if(nrElementeListate != idx - 1){
            bufferWriter.write(" ");
        }
        nrElementeListate++;/*listarea pe fii*/
        
        auxList(idxStanga, bufferWriter);
        auxList(idxDreapta, bufferWriter);
       
    }
}