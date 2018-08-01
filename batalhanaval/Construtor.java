package batalhanaval;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Construtor {

    public static Scene criarMenuInicial() {

        BorderPane menu = new BorderPane();

        HBox center = new HBox();

        Button singleplayer = new Button("Player VS Bot");
        Button multiplayer = new Button("Player VS Player");

        singleplayer.setOnAction(e -> {

            String jogador1 = Nickname.display("Insira o seu nickname");

            if (jogador1.compareTo("") != 0) {
                Controlador.setJogador1(jogador1);
                Controlador.iniciarDificuldade();
            } else {
                Alerta.showEmptyError();
            }
        });

        multiplayer.setOnAction(e -> {

            String jogador1 = Nickname.display("Insira o seu nickname, Jogador 1");
            String jogador2 = Nickname.display("Insira o seu nickname, Jogador 2");

            if (jogador1.compareTo("") == 0 || jogador2.compareTo("") == 0) {
                Alerta.showEmptyError();
            } else if (jogador1.compareTo(jogador2) == 0) {
                Alerta.showDuplicateError();
            } else {
                Controlador.setDif(-1);
                Controlador.setJogador1(jogador1);
                Controlador.setJogador2(jogador2);
                Controlador.iniciarJogo();
            }

        });

        center.getChildren().addAll(singleplayer, multiplayer);
        center.setAlignment(Pos.CENTER);

        center.setSpacing(20);
        menu.setCenter(center);

        HBox top = new HBox();
        Label tituloMenu = new Label("Selecione o modo de jogo");

        top.getChildren().add(tituloMenu);
        top.setAlignment(Pos.CENTER);

        menu.setTop(top);

        Scene menuPrincipal = new Scene(menu, 300, 300);

        return menuPrincipal;
    } //OK!

    public static Scene criarMenuDificuldades() {

        VBox dific = new VBox(20);

        Button basic = new Button("Básico");
        basic.setOnAction(e -> {
            Controlador.setDif(1);
            Controlador.setBot("Thom");
            Controlador.iniciarJogo();
        });

        Button hard = new Button("Intermediario");
        hard.setOnAction(e -> {
            Controlador.setDif(2);
            Controlador.setBot("Celeste");
            Controlador.iniciarJogo();
        });

        dific.getChildren().addAll(basic, hard);
        dific.setAlignment(Pos.CENTER);

        Scene menuDificuldades = new Scene(dific, 300, 300);

        return menuDificuldades;
    } //OK!

    public static GridPane criarMapa(Jogador player, int id, int dif) {

        final int identificador = id;

        GridPane map = new GridPane();
        map.setHgap(10);
        map.setVgap(10);
        map.setPrefSize(430, 430);

        for (int linha = 0; linha < 11; linha++) {
            for (int coluna = 0; coluna < 11; coluna++) {

                TextField tf = new TextField();

                tf.setPrefHeight(30);
                tf.setPrefWidth(30);
                tf.setAlignment(Pos.CENTER);
                tf.setEditable(false);

                if (linha == 0 && coluna == 0) {
                    
                    tf.setText("");

                    GridPane.setRowIndex(tf, linha);
                    GridPane.setColumnIndex(tf, coluna);
                    map.getChildren().add(tf);
                    
                } else if (linha == 0) {

                    char colIndex = 64;
                    colIndex += coluna;
                    tf.setText("" + colIndex);

                    GridPane.setRowIndex(tf, linha);
                    GridPane.setColumnIndex(tf, coluna);
                    map.getChildren().add(tf);

                } else if (coluna == 0) {

                    tf.setText("" + linha);

                    GridPane.setRowIndex(tf, linha);
                    GridPane.setColumnIndex(tf, coluna);
                    map.getChildren().add(tf);

                } else {

                    Button bt = new Button();

                    bt.setPrefHeight(30);
                    bt.setPrefWidth(30);
                    bt.setAlignment(Pos.CENTER);

                    final int lin = linha - 1;
                    final int col = coluna - 1;

                    bt.setOnAction(e -> {
                        if (player.atirar(lin, col, identificador)) {

                            tf.setText(Controlador.getPosicao(lin, col, id) + "");

                            map.getChildren().remove(bt);
                            GridPane.setRowIndex(tf, lin + 1);
                            GridPane.setColumnIndex(tf, col + 1);
                            map.getChildren().add(tf);
                            
                            Controlador.atualizarNavios(lin, col, id);
                            
                        } else {
                            Alerta.turnCheckError();
                        }
                    });

                    GridPane.setRowIndex(bt, linha);
                    GridPane.setColumnIndex(bt, coluna);

                    map.getChildren().add(bt);
                }
            }
        }
        
        player.setMapa(map);
        
        return map;
    }  //OK!

    public static Scene gerarMapa(Jogador player1, Jogador player2, int dif) {

        GridPane mapa1 = new GridPane();
        GridPane mapa2 = new GridPane();

        mapa1 = criarMapa(player1, 1, dif);
        mapa2 = criarMapa(player2, 2, dif);
        
        BorderPane layout = new BorderPane();

        VBox left = new VBox(15);
        VBox right = new VBox(15);

        Label jogador1 = new Label();
        jogador1.setText(player1.getNome());
        Label jogador2 = new Label();
        jogador2.setText(player2.getNome());

        left.setAlignment(Pos.CENTER);
        right.setAlignment(Pos.CENTER);

        left.getChildren().addAll(mapa1, jogador1);
        right.getChildren().addAll(mapa2, jogador2);

        layout.setLeft(left);
        layout.setRight(right);

        Scene mapa = new Scene(layout, 900, 500);

        return mapa;
    }//OK!

    public static Campo criarCampo() {

        Celula[][] cels = new Celula[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cels[i][j] = new Celula(i, j);
            }
        }

        Campo campo = new Campo(cels);

        return campo;
    } //OK!

    public static ArrayList criarNavios() {

        ArrayList navios = new ArrayList();

        Navio pa1 = new Navio('P', 5);
        Navio nt1 = new Navio('N', 4);
        Navio nt2 = new Navio('N', 4);
        Navio ct1 = new Navio('T', 3);
        Navio ct2 = new Navio('T', 3);
        Navio ct3 = new Navio('T', 3);
        Navio sb1 = new Navio('S', 2);
        Navio sb2 = new Navio('S', 2);
        Navio sb3 = new Navio('S', 2);
        Navio sb4 = new Navio('S', 2);

        navios.add(pa1);
        navios.add(nt1);
        navios.add(nt2);
        navios.add(ct1);
        navios.add(ct2);
        navios.add(ct3);
        navios.add(sb1);
        navios.add(sb2);
        navios.add(sb3);
        navios.add(sb4);

        return navios;
    } //OK!

    public static void gerarCampoNavios(Jogador player) {

        Random rand = new Random();

        ArrayList navios = criarNavios();

        player.setNavios(navios);

        while(true) {

            boolean horizontal = rand.nextBoolean();
            int linha = rand.nextInt(10);
            int coluna = rand.nextInt(10);

            Navio colocar = (Navio) navios.get(0);

            if (checarEncaixe(player, colocar, horizontal, linha, coluna)) {
                
                ArrayList lin = new ArrayList();
                ArrayList col = new ArrayList();

                if (!horizontal) {

                    col.add(coluna);
                    
                    for (int j = 0; j < colocar.getSize(); j++) {

                        Celula lugar = player.getCampo().getCel(linha + j, coluna);

                        lugar.setConteudo(colocar.getType());
                        lin.add(linha+j);
                    }
                    
                    colocar.setCord(lin, col);
                    
                } else {
                    
                    lin.add(linha);

                    for (int j = 0; j < colocar.getSize(); j++) {

                        Celula lugar = player.getCampo().getCel(linha, coluna + j);

                        lugar.setConteudo(colocar.getType());
                        col.add(coluna+j);
                    }
                    
                    colocar.setCord(lin, col);
                }

                navios.remove(0);
            }
            
            if(navios.isEmpty()){
                break;
            }
        }
    } //OK!

    public static boolean checarEncaixe(Jogador player, Navio colocar, boolean horizontal, int linha, int coluna) {

        boolean check = false;

        if (!horizontal) {
            for (int i = 0; i < colocar.getSize(); i++) {
                if (linha + i >= 0 && linha + i < 10) {
                    if (player.getCampo().getCelConteudo(linha + i, coluna) == '-') {
                        check = true;
                    } else {
                        check = false;
                        break;
                    }
                } else {
                    check = false;
                    break;
                }
            }
        } else {
            for (int i = 0; i < colocar.getSize(); i++) {
                if (coluna + i >= 0 && coluna + i < 10) {
                    if (player.getCampo().getCelConteudo(linha, coluna + i) == '-') {
                        check = true;
                    } else {
                        check = false;
                        break;
                    }
                } else {
                    check = false;
                    break;
                }
            }
        }

        return check;
    }//OK!
}
