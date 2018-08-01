
package batalhanaval;

public class Campo {//OK!
    
    private Celula[][] cels = null;
    
    public Campo(Celula[][] cel){
        this.cels = cel;
    }
    
    public Celula getCel(int linha, int coluna){
        return cels[linha][coluna];
    }
    
    public char getCelConteudo(int linha, int coluna){
        return cels[linha][coluna].getConteudo();
    }
    
    public void setCelConteudo(int linha, int coluna, char conteudo){
       cels[linha][coluna].setConteudo(conteudo);
    }
}
