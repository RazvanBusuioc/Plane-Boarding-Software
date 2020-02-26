import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * clasa main
 * @author Busuioc Gabriel-Razvan 324 CB
 */
public class Tema1 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws IOException {
        /*priorityQ - coada de prioritate
        vectAux - vector auxiliar cu fiecare grup de pasageri
        comandaLinie - string care retine comenzile din queue.in*/
        Heap priorityQ = new Heap();
        auxArray vectAux = new auxArray();
        String comandaLinie;
        String inputFile = "queue.in";
        
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            int nrPasageri = scanner.nextInt();
            ArrayList<Persoana> vectorPersoane= new ArrayList<>();
            Persoana persoana;
            
            /*citirea datelor pasagerilor si stocarea lor intr-un vector de pasageri*/
            for(int i = 0; i < nrPasageri; i++){
                String numeGrup = scanner.next();
                String numePersoana = scanner.next();
                int varsta = scanner.nextInt();
                String tipBilet = scanner.next();
                boolean imbarcarePrioritara = scanner.nextBoolean();
                boolean nevoiSpeciale = scanner.nextBoolean();
                
                persoana = new Persoana(numeGrup, numePersoana, varsta, tipBilet,
                        imbarcarePrioritara, nevoiSpeciale);
                vectorPersoane.add(persoana);
            }
            
            
            /*iterarea prin vectorul de persoane si creearea vectorului
            auxiliar cu fiecare grup de pasageri*/
            int i;
            for(i = 0; i < nrPasageri; i++){
                int idx = vectAux.getGrupIdxbyNume((vectorPersoane.get(i)).getNumeGrup());
                if(idx == -1){
                    /*cazul in care grupul nu exista in vectorul auxiliar*/
                    String primaLitera;
                    primaLitera = String.valueOf(((vectorPersoane.get(i)).getNumeGrup()).charAt(0));
                    Grup grup = new Grup(primaLitera, (vectorPersoane.get(i)).getNumeGrup());
                    grup.addPersoana(vectorPersoane.get(i));
                    vectAux.addGrup(grup);
                }
                else{
                    /*cazul in care grupul exista in vectorul de persoane, adaugam
                    persoana respectiva in grup*/
                    vectAux.getGrupByIdx(idx).addPersoana(vectorPersoane.get(i));
                }
            }
            
            /*executarea comenzilor: insert, list, embark, delete*/
            
            while(scanner.hasNextLine()){
                comandaLinie = scanner.nextLine();
                String[] parts = comandaLinie.split(" ");                
                
                if(parts[0].equals("insert")){
                    Grup grup = vectAux.getGrupByNume(parts[1]);
                  priorityQ.insert(grup,grup.getPunctajGrup());
                }
                else if(parts[0].equals("list")){
                    priorityQ.list();
                }
                else if(parts[0].equals("embark")){
                    priorityQ.embark();
                    System.out.println();
                }
                else if(parts[0].equals("delete")){
                    if(parts.length >2 &&  parts[2] != null){
                        /*cazul in care se doreste stergerea unei persoane*/
                        int j;
                        for(j = 0; j < vectorPersoane.size(); j++){
                            /*cautarea persoanei in vectorul de persoane/pasageri*/
                            if(parts[2].equals(vectorPersoane.get(j).getNumePersoana())){
                                priorityQ.delete(vectorPersoane.get(j));
                                break;
                            }
                        }
                    }else{
                        /*cazul in care se doreste stergerea unui grup intreg*/
                        priorityQ.delete(vectAux.getGrupByNume(parts[1]));
                    }
                }
            }
            System.out.println(vectAux.getIdx());
        }
        catch(FileNotFoundException ex){
            ex.getMessage();
        }
    }
        
}

