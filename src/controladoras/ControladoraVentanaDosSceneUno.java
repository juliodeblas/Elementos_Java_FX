package controladoras;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControladoraVentanaDosSceneUno implements Initializable {

    @FXML
    Button boton_dos, boton_uno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        boton_dos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene sceneActual = boton_dos.getScene();
                Stage stage = (Stage) boton_dos.getScene().getWindow();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("..//layouts//layout_ventana_dos_scene_dos.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(root, sceneActual.getWidth(), sceneActual.getHeight());
                stage.setScene(scene);
            }
        });
    }
}
