
package batalhanaval;

import java.util.ArrayList;

public class Navio {//OK!
    
    private final char type;
    private ArrayList linha;
    private ArrayList coluna;
    private final int size;
    private int hp;
    
    public Navio (char type, int size){
        this.type = type;
        this.size = hp = size;
    }
    
    public char getType(){
        return type;
    }
    
    public int getSize(){
        return size;
    }
    
    public int getHP(){
        return hp;
    }
    
    public ArrayList getLinha(){
        return linha;
    }
    
    public ArrayList getColuna(){
        return coluna;
    }
    
    public void setHP(){
        hp--;
    }
    
    public void setCord(ArrayList lin, ArrayList col){
        coluna = new ArrayList(col);
        linha = new ArrayList(lin);
    }
    
    public boolean procurarCord(int lin, int col){
        
        for(int i = 0; i < linha.size(); i++){
            for(int j = 0; j < coluna.size(); j++){
                if(((int)linha.get(i) == lin)&&((int)coluna.get(j) == col)){
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
