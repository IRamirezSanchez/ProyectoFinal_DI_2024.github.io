package org.example.Controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HistorialCalculadoraController implements Initializable {
    @javafx.fxml.FXML
    private ListView<String> ListaElementos;
    private ObservableList<String> elementosLista;
    @javafx.fxml.FXML
    private Button BTN_volver;
    private CalculadoraController controladorCalculadora;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        elementosLista = FXCollections.observableArrayList();
    }

    @javafx.fxml.FXML
    public void b_Volver(ActionEvent actionEvent) {
        Stage miStage = (Stage) this.BTN_volver.getScene().getWindow();
        miStage.close();
    }

    public void setListaElementos(List<String> operacion, CalculadoraController calculadoraControl) {
        controladorCalculadora= calculadoraControl;
        elementosLista.setAll(operacion);
        ListaElementos.setItems(elementosLista);
    }
}
