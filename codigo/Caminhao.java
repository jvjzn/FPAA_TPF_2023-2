package codigo;

import java.util.ArrayList;
import java.util.List;

public class Caminhao {
    
    private int id;
    private List<Integer> rotas;
    private int kmTotal;


    //Constructor
    public Caminhao(int id){
        this.id = id;
        this.rotas = new ArrayList<>();
        this.kmTotal = 0;
    }

    //Methods
    public int somarKMcaminhao(){
        int resultado = 0;

        for(int rota : this.rotas){
            resultado += rota;
        }

        this.kmTotal = resultado;

        return resultado;
    }

    public void imprimirRotas(){
        for(int rota : this.rotas){
            System.out.print(rota + " ");
        }
    }

    //Getters and Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setRotas(List<Integer> rotas) {
        this.rotas = rotas;
    }

    public void setKmTotal(int kmTotal) {
        this.kmTotal = kmTotal;
    }
    
    public int getId() {
        return id;
    }

    public List<Integer> getRotas() {
        return rotas;
    }

    public int getKmTotal() {
        return kmTotal;
    }

    public void addRota(int rota) {
        rotas.add(rota);
    }
    
}
