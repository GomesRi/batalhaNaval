package batalhanaval;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Alerta {

    public static void errorWindow(String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ERRO!!!");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        Button confirm = new Button("OK!");
        confirm.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, confirm);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    } //codigo para abrir uma janela

    public static void showEmptyError() { //erro referente a nome vazio
        errorWindow("Nenhum nome inserido, tente novamente");
    }

    public static void showDuplicateError() { //erro referente a nomes duplicados
        errorWindow("Jogadores com mesmo nome, por favor insira nomes diferentes um do outro");
    }

    public static void turnCheckError() { //erro referente a jogador jogando fora do seu turno;
        errorWindow("Jogador errado, aguarde seu turno");
    }

    public static void endGame(String vencedor) {

        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Vencedor");
        window.setMinWidth(250);

        Label label = new Label();
        label.setText("E o vencedor foi: " + vencedor);
        Button confirm = new Button("OK!");
        confirm.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, confirm);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
