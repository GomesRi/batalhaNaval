package batalhanaval;

import java.util.Random;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BotBasico extends Jogador {


    public BotBasico(String nickname) {
        super(nickname);
    }  //OK!

    @Override
    public void pressButton() {

        Random rand = new Random();

        int linha = rand.nextInt(11);
        int coluna = rand.nextInt(11);

        while (true) {
            if (10 * linha + coluna < mapa.getChildren().size()) {
                if (mapa.getChildren().get(10 * linha + coluna) instanceof Button) {
                    break;
                }
            }

            linha = rand.nextInt(11);
            coluna = rand.nextInt(11);

        }

        Button press = (Button) mapa.getChildren().get(10*linha + coluna);

        press.fire();
    } //OK!
}