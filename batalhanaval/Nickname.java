
package batalhanaval;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Nickname {
    
    public static String display(String message){
        
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Batalha Naval");
        window.setMinWidth(250);
        
        Label label = new Label();
        label.setText(message);
        TextField tf = new TextField("");
        Button confirm = new Button("Confirmar");
        confirm.setDefaultButton(true);
        confirm.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, tf, confirm);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return (tf.getText());
    }
    
}
