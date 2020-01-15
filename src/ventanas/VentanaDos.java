package ventanas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.application.Application.launch;

public class VentanaDos extends Stage {
    String titulo;

    public VentanaDos(String titulo) {
        initGui();
    }

    private void initGui() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../layouts/layout_ventana_dos.fxml"));
            Scene scene = new Scene(root, 300, 300);
            this.setScene(scene);
            this.setTitle("Adicional");
            this.initStyle(StageStyle.DECORATED);
            this.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}



