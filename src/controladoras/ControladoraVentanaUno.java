package controladoras;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Persona;
import ventanas.VentanaDos;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ControladoraVentanaUno implements Initializable {

    DropShadow sombra = new DropShadow();

    @FXML
    Button btn_normal, btn_imagen;

    @FXML
    Tab tab_botones, tab_textos, tab_tablas;

    @FXML
    CheckBox check;

    @FXML
    RadioButton radio1, radio2, radio3;

    ToggleGroup grupoRadios;

    @FXML
    TextField texto_normal;

    @FXML
    JFXTextField texto_material;

    @FXML
    TextArea text_area;

    @FXML
    Button boton_pantalla;

    @FXML
    ComboBox combo_box;

    @FXML
    ChoiceBox choice_box;

    @FXML
    ListView list_view;

    @FXML
    Button boton_seleccion;

    @FXML
    Button boton_informacion, boton_confirmacion, boton_botones, boton_input, boton_choice, boton_perso;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        text_area.setWrapText(true);
        instancias();
        personalizarBotones();
        personalizarCombo();
        personalizarLista();
        acciones();
    }

    private void personalizarLista() {
        ObservableList lista_elementos = FXCollections.observableArrayList();
        lista_elementos.addAll(new Persona("asd", "asd"),
                new Persona("dsdfs", "gergerg"),
                new Persona("dsdfs", "gergerg"),
                new Persona("dsdfs", "gergerg"),
                new Persona("dsdfs", "gergerg"));
        list_view.setItems(lista_elementos);
    }

    private void personalizarCombo() {
        combo_box.setPromptText("Selecciona un valor del combo");
        combo_box.setVisibleRowCount(3);
        ObservableList item_combo = FXCollections.observableArrayList();
        item_combo.addAll(1, 2, 3, 4);
        ObservableList item_choice = FXCollections.observableArrayList();
        item_choice.addAll(1, 2, 3, 4);
        choice_box.setTooltip(new Tooltip("Selecciona un valor del choice"));
        combo_box.setItems(item_combo);
        choice_box.setItems(item_choice);
    }

    private void instancias() {
        grupoRadios = new ToggleGroup();
        radio1.setUserData(new Persona("Julio", "soltero"));
        radio2.setUserData(new Persona("Pepe", "casado"));
        radio3.setUserData(new Persona("Luis", "soltero"));
        grupoRadios.getToggles().addAll(radio1, radio2, radio3);
    }

    private void personalizarBotones() {
        btn_imagen.setBackground(null);
        ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/resources/button_ok.png")));
        btn_imagen.setGraphic(imageView);
    }

    private void acciones() {
        boton_pantalla.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                VentanaDos ventanaDos = new VentanaDos(texto_material.getText());
            }
        });
        btn_normal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(check.isSelected());
                //System.out.println("El boton seleccionado es: "+grupoRadios.getSelectedToggle());
            }
        });
        btn_normal.setOnMouseEntered(new ManejoRaton());
        btn_normal.setOnMouseExited(new ManejoRaton());
        btn_imagen.setOnMouseClicked(new ManejoRaton());
        btn_imagen.setOnMouseReleased(new ManejoRaton());
        check.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    btn_normal.setDisable(true);
                } else {
                    btn_normal.setDisable(false);
                }
            }
        });
        grupoRadios.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                //RadioButton radioButtonSeleccionado = (RadioButton) newValue;
                //System.out.println(radioButtonSeleccionado.getText());
                //System.out.println(radioButtonSeleccionado.getUserData());
                Persona seleccionado = (Persona) newValue.getUserData();
                System.out.println(seleccionado.getNombre());
            }
        });
        boton_seleccion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Persona p = (Persona) list_view.getSelectionModel().getSelectedItem();
                System.out.println(p);
            }
        });
        list_view.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                Persona p = (Persona) newValue;
                System.out.println(p.getEstado());
            }
        });
        boton_informacion.setOnAction(new ManejoPulsaciones());
        boton_confirmacion.setOnAction(new ManejoPulsaciones());
        boton_botones.setOnAction(new ManejoPulsaciones());
        boton_input.setOnAction(new ManejoPulsaciones());
        boton_choice.setOnAction(new ManejoPulsaciones());
        boton_perso.setOnAction(new ManejoPulsaciones());
    }

    class ManejoRaton implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (event.getSource() == btn_normal) {
                if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
                    btn_normal.setEffect(sombra);
                } else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
                    btn_normal.setEffect(null);
                }
            } else if (event.getSource() == btn_imagen) {
                if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                    btn_imagen.setEffect(sombra);
                } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                    System.out.println("Hola");
                    btn_imagen.setEffect(null);
                }
            }
        }
    }

    class ManejoPulsaciones implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == boton_informacion) {
                Alert alert_informacion = new Alert(Alert.AlertType.INFORMATION);
                alert_informacion.setTitle("Titulo información");
                alert_informacion.setHeaderText("Header informacion");
                alert_informacion.setContentText("Contenido informacion");
                alert_informacion.show();
            } else if (event.getSource() == boton_confirmacion) {
                Alert alert_confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                alert_confirmacion.setTitle("Titulo confirmacion");
                alert_confirmacion.setHeaderText("Header confirmacion");
                alert_confirmacion.setContentText("Contenido confirmacion");
                Optional<ButtonType> resultado = alert_confirmacion.showAndWait();

                if (resultado.get() == ButtonType.OK) {
                    System.out.println("OK");
                } else if (resultado.get() == ButtonType.CANCEL) {
                    System.out.println("CANCEL");
                }
            } else if (event.getSource() == boton_botones) {
                ButtonType ejemplo = new ButtonType("ejemplo");
                ButtonType ejemplo2 = new ButtonType("ejemplo 2");

                Alert alert_botones = new Alert(Alert.AlertType.CONFIRMATION);
                alert_botones.setTitle("Titulo confirmacion");
                alert_botones.setHeaderText("Header confirmacion");
                alert_botones.setContentText("Contenido confirmacion");
                alert_botones.getButtonTypes().setAll(ejemplo, ejemplo2);
                Optional<ButtonType> resultado = alert_botones.showAndWait();

                if (resultado.get() == ejemplo) {
                    System.out.println("ejemplo");
                } else if (resultado.get() == ejemplo2) {
                    System.out.println("ejemplo2");
                }
            } else if (event.getSource() == boton_input) {

            } else if (event.getSource() == boton_choice) {

            } else if (event.getSource() == boton_perso) {

            }
        }
    }
}
