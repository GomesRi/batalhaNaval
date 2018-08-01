package batalhanaval;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Jogador {

    protected String nickname = "";
    protected Campo campoMeu;
    protected ArrayList navios;
    protected GridPane mapa;

    Jogador(String nickname) {
        this.nickname = nickname;
        campoMeu = Construtor.criarCampo();
        mapa = new GridPane();
    }

    public String getNome() {
        return nickname;
    }

    public Campo getCampo() {
        return campoMeu;
    }

    public GridPane getGrid() {
        return mapa;
    }

    public ArrayList getNavios() {
        return navios;
    }

    public void setCampo(Campo campoNavios) {
        campoMeu = campoNavios;
    }

    public void setMapa(GridPane mapa) {
        this.mapa = mapa;
    }

    public void setNavios(ArrayList ships) {
        navios = new ArrayList(ships);
    }

    public boolean atirar(int lin, int col, int identificador) {
        return Controlador.tiro(lin, col, identificador);
    }

    public void atualizar(int lin, int col, GridPane oponente) {

        Navio n;

        for (Object o : navios) {
            n = (Navio) o;

            if (n.procurarCord(lin, col)) {
                n.setHP();

                if (n.getHP() == 0) {

                    char type = n.getType();
                    ArrayList linha = new ArrayList(n.getLinha());
                    ArrayList coluna = new ArrayList(n.getColuna());

                    for (int i = 0; i < linha.size(); i++) {
                        for (int j = 0; j < coluna.size(); j++) {

                            TextField tf = new TextField();
                            tf.setPrefHeight(30);
                            tf.setPrefWidth(30);
                            tf.setAlignment(Pos.CENTER);
                            tf.setEditable(false);
                            tf.setText(type + "");

                            GridPane.setRowIndex(tf, ((int) linha.get(i))+1);
                            GridPane.setColumnIndex(tf, ((int) coluna.get(j))+1);
                            oponente.getChildren().add(tf);

                        }
                    }

                    navios.remove(o);
                }

                return;
            }
        }
    }
    
    public void pressButton(){}
}
