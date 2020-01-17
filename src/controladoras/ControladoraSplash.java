package controladoras;

import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import ventanas.VentanaUno;

import java.net.URL;
import java.util.ResourceBundle;

public class ControladoraSplash implements Initializable {

    @FXML
    ImageView imagen_fondo;

    @FXML
    ProgressBar barra_progreso;

    Task tareaSecundaria;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        instancias();

        barra_progreso.progressProperty().bind(tareaSecundaria.progressProperty());

        FadeTransition transition = new FadeTransition(Duration.seconds(3), imagen_fondo);
        transition.setFromValue(0.0);
        transition.setToValue(1.0);
        transition.play();

        transition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new Thread(tareaSecundaria).start();
            }
        });
        tareaSecundaria.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                VentanaUno ventanaUno = new VentanaUno();
                Stage stage = (Stage) imagen_fondo.getScene().getWindow();
                stage.close();
            }
        });
    }

    private void instancias() {
        tareaSecundaria = new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    updateProgress(i, 100);
                    Thread.sleep(10);
                }


                return null;
            }
        };
    }
}
