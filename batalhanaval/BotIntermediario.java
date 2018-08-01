package batalhanaval;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BotIntermediario extends Jogador {

    private Campo campoAdversario;
    private ArrayList naviosInimigos;

    public BotIntermediario(String nickname) {
        super(nickname);
        campoAdversario = Construtor.criarCampo();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                campoAdversario.setCelConteudo(i, j, '~');
            }
        }

        naviosInimigos = Construtor.criarNavios();
    }  //OK!

    private void atualizarCampoArdversario() {

        TextField tf = new TextField();

        for (Node o : mapa.getChildren()) {
            if (o instanceof TextField) {

                int linha = GridPane.getRowIndex(o) - 1;
                int coluna = GridPane.getColumnIndex(o) - 1;

                tf = (TextField) mapa.getChildren().get(mapa.getChildren().indexOf(o));

                String conteudo = tf.getCharacters().toString();

                switch (conteudo) {

                    case "-": {
                        campoAdversario.setCelConteudo(linha, coluna, '-');
                        break;
                    }

                    case "P":
                        campoAdversario.setCelConteudo(linha, coluna, 'P');
                        break;

                    case "N":
                        campoAdversario.setCelConteudo(linha, coluna, 'N');
                        break;

                    case "T":
                        campoAdversario.setCelConteudo(linha, coluna, 'T');
                        break;

                    case "S":
                        campoAdversario.setCelConteudo(linha, coluna, 'S');
                        break;

                    case "X":
                        campoAdversario.setCelConteudo(linha, coluna, 'X');
                        break;
                }
            }
        }
    }

    private void atualizarMalha(int[][] malha) {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                switch (campoAdversario.getCelConteudo(i, j)) {

                    case '-':
                        malha[i][j] = -6;
                        break;
                    case 'P':
                        malha[i][j] = -5;
                        break;
                    case 'N':
                        malha[i][j] = -4;
                        break;
                    case 'T':
                        malha[i][j] = -3;
                        break;
                    case 'S':
                        malha[i][j] = -2;
                        break;
                    case 'X':
                        malha[i][j] = -1;
                        break;
                }
            }
        }
    }

    private void atualizarNaviosInimigos(int[][] malha) {

        ArrayList naviosInimigosDestruidos = new ArrayList();

        Navio nid;
        Navio ni;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (naviosInimigosDestruidos.isEmpty()) {
                    return;
                }

                boolean exist = false;

                switch (malha[i][j]) {
                    case -2:
                        for (Object a : naviosInimigosDestruidos) {
                            nid = (Navio) a;
                            if (nid.getType() == 'S') {
                                if (nid.procurarCord(i, j)) {
                                    exist = true;
                                    break;
                                }
                            }
                        }

                        if (exist) {
                            break;
                        }

                        for (Object b : naviosInimigos) {

                            ni = (Navio) b;

                            if (ni.procurarCord(i, j)) {
                                naviosInimigosDestruidos.add(ni);
                                naviosInimigos.remove(ni);
                            }
                        }
                        break;

                    case -3:
                        for (Object a : naviosInimigosDestruidos) {
                            nid = (Navio) a;
                            if (nid.getType() == 'T') {
                                if (nid.procurarCord(i, j)) {
                                    exist = true;
                                    break;
                                }
                            }
                        }

                        if (exist) {
                            break;
                        }

                        for (Object b : naviosInimigos) {

                            ni = (Navio) b;

                            if (ni.procurarCord(i, j)) {
                                naviosInimigosDestruidos.add(ni);
                                naviosInimigos.remove(ni);
                            }
                        }
                        break;

                    case -4:
                        for (Object a : naviosInimigosDestruidos) {
                            nid = (Navio) a;
                            if (nid.getType() == 'N') {
                                if (nid.procurarCord(i, j)) {
                                    exist = true;
                                    break;
                                }
                            }
                        }

                        if (exist) {
                            break;
                        }

                        for (Object b : naviosInimigos) {

                            ni = (Navio) b;

                            if (ni.procurarCord(i, j)) {
                                naviosInimigosDestruidos.add(ni);
                                naviosInimigos.remove(ni);
                            }
                        }
                        break;

                    case -5:
                        for (Object a : naviosInimigosDestruidos) {
                            nid = (Navio) a;
                            if (nid.getType() == 'P') {
                                if (nid.procurarCord(i, j)) {
                                    exist = true;
                                    break;
                                }
                            }
                        }

                        if (exist) {
                            break;
                        }

                        for (Object b : naviosInimigos) {

                            ni = (Navio) b;

                            if (ni.procurarCord(i, j)) {
                                naviosInimigosDestruidos.add(ni);
                                naviosInimigos.remove(ni);
                            }
                        }
                        break;

                }
            }
        }
    }

    private int[][] malhaSearch() {

        int[][] malha = new int[10][10];

        atualizarMalha(malha);
        atualizarNaviosInimigos(malha);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 4; k++) {
                    for (Object o : naviosInimigos) {
                        Navio n = (Navio) o;
                        boolean fit = true;

                        switch (k) {

                            case 0:
                                for (int a = 0; a < n.getSize(); a++) {
                                    if (j + a < 10) {
                                        if (campoAdversario.getCelConteudo(i, j + a) != '~') {
                                            fit = false;
                                            break;
                                        }
                                    } else {
                                        fit = false;
                                        break;
                                    }
                                }

                                if (!fit) {
                                    break;
                                }

                                malha[i][j]++;
                                break;

                            case 1:
                                for (int a = 0; a < n.getSize(); a++) {
                                    if (j - a >= 0) {
                                        if (campoAdversario.getCelConteudo(i, j - a) != '~') {
                                            fit = false;
                                            break;
                                        }
                                    } else {
                                        fit = false;
                                        break;
                                    }
                                }

                                if (!fit) {
                                    break;
                                }

                                malha[i][j]++;
                                break;

                            case 2:
                                for (int a = 0; a < n.getSize(); a++) {
                                    if (i + a < 10) {
                                        if (campoAdversario.getCelConteudo(i + a, j) != '~') {
                                            fit = false;
                                            break;
                                        }
                                    } else {
                                        fit = false;
                                        break;
                                    }
                                }

                                if (!fit) {
                                    break;
                                }

                                malha[i][j]++;
                                break;
                            case 3:
                                for (int a = 0; a < n.getSize(); a++) {
                                    if (i - a >= 0) {
                                        if (campoAdversario.getCelConteudo(i - a, j) != '~') {
                                            fit = false;
                                            break;
                                        }
                                    } else {
                                        fit = false;
                                        break;
                                    }
                                }

                                if (!fit) {
                                    break;
                                }

                                malha[i][j]++;
                                break;
                        }
                    }
                }
            }
        }

        return malha;
    }

    private int[][] malhaHunt() {
        int[][] malha = new int[10][10];

        atualizarMalha(malha);
        atualizarNaviosInimigos(malha);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (campoAdversario.getCelConteudo(i, j) == 'X') {

                    for (Object o : naviosInimigos) {
                        for (int k = 0; k < 4; k++) {

                            Navio n = (Navio) o;
                            boolean fit = true;

                            switch (k) {

                                case 0:
                                    for (int a = 0; a < n.getSize(); a++) {
                                        if (j + a < 10) {
                                            if (campoAdversario.getCelConteudo(i, j+a) != '~' && campoAdversario.getCelConteudo(i, j+a) != 'X') {
                                                fit = false;
                                                break;
                                            }
                                        } else {
                                            fit = false;
                                            break;
                                        }
                                    }

                                    if (!fit) {
                                        break;
                                    }

                                    for (int a = 0; a < n.getSize(); a++) {
                                        if (j + a < 10) {
                                            if (campoAdversario.getCelConteudo(i, j+a) != 'X') {
                                                malha[i][j+a]++;
                                            }
                                        }
                                    }
                                    break;

                                case 1:
                                    for (int a = 0; a < n.getSize(); a++) {
                                        if (j - a >= 0) {
                                            if (campoAdversario.getCelConteudo(i, j-a) != '~' && campoAdversario.getCelConteudo(i, j-a) != 'X') {
                                                fit = false;
                                                break;
                                            }
                                        } else {
                                            fit = false;
                                            break;
                                        }
                                    }

                                    if (!fit) {
                                        break;
                                    }

                                    for (int a = 0; a < n.getSize(); a++) {
                                        if (j - a >= 0) {
                                            if (campoAdversario.getCelConteudo(i, j-a) != 'X') {
                                                malha[i][j-a]++;
                                            }
                                        }
                                    }
                                    break;

                                case 2:
                                    for (int a = 1; a < n.getSize(); a++) {
                                        if (i + a < 10) {
                                            if (campoAdversario.getCelConteudo(i + a, j) != '~' && campoAdversario.getCelConteudo(i + a, j) != 'X') {
                                                fit = false;
                                                break;
                                            }
                                        } else {
                                            fit = false;
                                            break;
                                        }
                                    }

                                    if (!fit) {
                                        break;
                                    }

                                    for (int a = 0; a < n.getSize(); a++) {
                                        if (i + a < 10) {
                                            if (campoAdversario.getCelConteudo(i + a, j) != 'X') {
                                                malha[i + a][j]++;
                                            }
                                        }
                                    }
                                    break;
                                case 3:
                                    for (int a = 1; a < n.getSize(); a++) {
                                        if (i - a >= 0) {
                                            if (campoAdversario.getCelConteudo(i - a, j) != '~' && campoAdversario.getCelConteudo(i - a, j) != 'X') {
                                                fit = false;
                                                break;
                                            }
                                        } else {
                                            fit = false;
                                            break;
                                        }
                                    }

                                    if (!fit) {
                                        break;
                                    }

                                    for (int a = 1; a < n.getSize(); a++) {
                                        if (i - a >= 0) {
                                            if (campoAdversario.getCelConteudo(i - a, j) != 'X') {
                                                malha[i - a][j]++;
                                            }
                                        }
                                    }
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return malha;
    }

    @Override
    public void pressButton() {

        int linha = 0, coluna = 0, hunt = 0, max = 0;
        int[][] malhaProbabilidade = new int[10][10];

        atualizarCampoArdversario();

        for (linha = 0; linha < 10; linha++) {
            for (coluna = 0; coluna < 10; coluna++) {
                if (campoAdversario.getCelConteudo(linha, coluna) == 'X') {
                    hunt = 1;
                    break;
                } else {
                    hunt = 0;
                }
            }

            if (hunt == 1) {
                break;
            }
        }

        if (hunt == 1) {
            malhaProbabilidade = malhaHunt();
        } else {
            malhaProbabilidade = malhaSearch();
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (malhaProbabilidade[i][j] > max) {
                    max = malhaProbabilidade[i][j];
                    linha = i;
                    coluna = j;
                }
            }
        }

        Button press = new Button();

        for (Node node : mapa.getChildren()) {
            if ((GridPane.getRowIndex(node) == linha + 1) && (GridPane.getColumnIndex(node) == coluna+1)) {
                press = (Button) node;
                break;
            }
        }

        press.fire();
    }
}
    //OK!
