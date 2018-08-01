package batalhanaval; //OK!

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage; //OK!

public class Controlador extends Application {

    private static int turno = 1; //OK!
    private static int dif; //OK!
    private static Stage window; //OK!
    private static Scene dificuldade; //OK!
    private static Jogador player1; //OK!
    private static Jogador player2; //OK!
    private static Jogador bot; //OK!
    private static Campo campo1;
    private static Campo campo2;

    public static void main(String[] args) {
        launch(args);
    } //OK!

    private static void fimDeJogo(int vencedor) {

        switch (vencedor) {
            case 1:
                if (dif == -1) {
                    Alerta.endGame(player2.getNome());
                } else {
                    Alerta.endGame(bot.getNome());
                }
                break;

            case 2:
                Alerta.endGame(player1.getNome());
                break;

            case 3:
                Alerta.endGame(player1.getNome());
                break;
        }

        window.close();
    } //OK!

    private static boolean checarTurno(int id) {
        return id == turno;
    } //OK!

    public static void atualizarNavios(int linha, int coluna, int id) {

        switch (id) {
            case 1:
                if (dif == -1) {
                    player2.atualizar(linha, coluna, player1.getGrid());
                    checarStatusJogo();
                } else {
                    bot.atualizar(linha, coluna, player1.getGrid());
                    checarStatusJogo();
                }
                break;
            case 2:
                if (dif == -1) {
                    player1.atualizar(linha, coluna, player2.getGrid());
                    checarStatusJogo();
                } else {
                    player1.atualizar(linha, coluna, bot.getGrid());
                    checarStatusJogo();
                }
                break;
        }
    }

    private static void checarStatusJogo() {

        if (player1.getNavios().isEmpty()) {
            fimDeJogo(1);
        } else if (dif == -1 && player2.getNavios().isEmpty()) {
            fimDeJogo(2);
        } else if (dif != -1 && bot.getNavios().isEmpty()) {
            fimDeJogo(3);
        }
    }

    public static char getPosicao(int linha, int coluna, int id) {

        if (turno == 1) {
            if (id == 1) {
                if (campo2.getCelConteudo(linha, coluna) != '-') {
                    return 'X';
                } else {
                    return campo2.getCelConteudo(linha, coluna);
                }
            } else {
                if (campo1.getCelConteudo(linha, coluna) != '-') {
                    return 'X';
                } else {
                    return campo1.getCelConteudo(linha, coluna);
                }
            }
        } else {
            if (id == 1) {
                if (campo2.getCelConteudo(linha, coluna) != '-') {
                    return 'X';
                } else {
                    return campo2.getCelConteudo(linha, coluna);
                }
            } else {
                if (campo1.getCelConteudo(linha, coluna) != '-') {
                    return 'X';
                } else {
                    return campo1.getCelConteudo(linha, coluna);
                }
            }
        }

    } //OK!

    public static Jogador getBot() {
        return bot;
    }

    public static void setJogador1(String nickname) {
        player1 = new Jogador(nickname);
    } //OK!

    public static void setJogador2(String nickname) {
        player2 = new Jogador(nickname);
    } //OK!

    public static void setBot(String nickname) {
        if (dif == 1) {
            bot = new BotBasico(nickname);
        } else {
            bot = new BotIntermediario(nickname);
        }
    } //OK!

    public static void setDif(int difficult) {
        dif = difficult;
    } //OK!

    public static void setTurno(int value) {
        turno = value;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;

        window.setScene(Construtor.criarMenuInicial());
        window.setTitle("Batalha Naval");
        window.show();
    } //OK!

    public static void iniciarDificuldade() {

        window.close();

        dificuldade = Construtor.criarMenuDificuldades();

        window.setScene(dificuldade);
        window.setTitle("Batalha Naval");
        window.show();
    } //OK!

    public static void iniciarJogo() {

        Construtor.gerarCampoNavios(player1);
        campo1 = player1.getCampo();

        if (dif == -1) {
            Construtor.gerarCampoNavios(player2);
            campo2 = player2.getCampo();
        } else {
            Construtor.gerarCampoNavios(bot);
            campo2 = bot.getCampo();
        }

        window.close();

        if (dif == -1) {
            window.setScene(Construtor.gerarMapa(player1, player2, dif));
        } else {
            window.setScene(Construtor.gerarMapa(player1, bot, dif));
        }

        window.setTitle("Batalha Naval");;
        window.show();
    } //OK!

    public static boolean tiro(int linha, int coluna, int id) {

        if (checarTurno(id)) {

            if (dif != -1 && id != 2) {
                setTurno(turno % 2 + 1);

                bot.pressButton();
            } else {
                setTurno(turno % 2 + 1);
            }

            return true;
        }

        return false;
    }
}
