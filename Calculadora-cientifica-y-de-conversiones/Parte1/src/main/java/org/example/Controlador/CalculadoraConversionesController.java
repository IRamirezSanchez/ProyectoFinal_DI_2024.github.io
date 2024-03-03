package org.example.Controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Modelo.ConvertidorMonedas;
import org.example.Modelo.Operaciones_Matematicas;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CalculadoraConversionesController implements Initializable {


    @FXML
    private Button c_igual;
    @FXML
    private Label l_memoria;
    @FXML
    private Button b_tres;
    @FXML
    private Button b_siete;
    @FXML
    private Button b_uno;
    @FXML
    private ChoiceBox CB_dos;
    @FXML
    private Button b_cuatro;
    @FXML
    private Button c_punto;
    @FXML
    private Label label_control;
    @FXML
    private Button b_ocho;
    @FXML
    private Button b_seis;
    @FXML
    private Button b_nueve;
    @FXML
    private Button b_cinco;
    @FXML
    private Button c_limpiar;
    @FXML
    private Label label_Resultado;
    @FXML
    private Button b_cero;
    @FXML
    private Button b_dos;
    @FXML
    private ChoiceBox CB_tres;
    @FXML
    private AnchorPane ventanaConversiones;
    private String operacion;
    private String memoria;
    private ConvertidorMonedas convertidor;
    private Operaciones_Matematicas operacionConvertir;
    private List<String> opciones;
    @FXML
    private ComboBox CB_uno;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operacion="";
        memoria="";
        convertidor= new ConvertidorMonedas();
        operacionConvertir = new Operaciones_Matematicas();
        opciones=List.of("Monedas", "Longitud", "Tiempo","Masa");

    }

    public double getTamañoPreferidoAncho() {
        return ventanaConversiones.getPrefWidth();
    }

    public double getTamañoPreferidoAlto() {
        return ventanaConversiones.getPrefHeight();
    }

    @FXML
    public void porTeclado(Event event) {
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            String teclaPresionada = keyEvent.getText();
            if (teclaPresionada.matches("[0-9,.]")) {
                operacion += teclaPresionada;
                label_Resultado.setText(operacion);
            } else if (((KeyEvent) event).getCode() == KeyCode.BACK_SPACE) {
                if (!operacion.isEmpty()) {
                    operacion = operacion.substring(0, operacion.length() - 1);
                    label_Resultado.setText(operacion);
                }
            }
        }
    }

    @FXML
    public void CabrirAyuda(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/infoPanelConversion.fxml"));
        Pane root = (Pane) loader.load();
        Stage stage = new Stage();
        stage.setTitle("Informacion Monetaria");
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    public void CventanaNormal(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/calculadoraController.fxml"));
        AnchorPane panel = loader.load();

        CalculadoraController normalController = loader.getController();

        panel.setPrefWidth(normalController.getTamañoPreferidoAncho());
        panel.setPrefHeight(normalController.getTamañoPreferidoAlto());

        Scene nuevaScene = new Scene(panel);

        Stage stage = (Stage) ventanaConversiones.getScene().getWindow();
        stage.setScene(nuevaScene);

        stage.show();
    }
    @FXML
    public void CventanaCientifica(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/calculadoraCientificaController.fxml"));
        AnchorPane panel = loader.load();

        CalculadoraCientificaController normalController = loader.getController();

        panel.setPrefWidth(normalController.getTamañoPreferidoAncho());
        panel.setPrefHeight(normalController.getTamañoPreferidoAlto());

        Scene nuevaScene = new Scene(panel);

        Stage stage = (Stage) ventanaConversiones.getScene().getWindow();
        stage.setScene(nuevaScene);

        stage.show();
    }
    @FXML
    public void click_resultado(ActionEvent actionEvent) {
        if(operacion.isEmpty()){
            return;
        }
        double numero=Double.parseDouble(operacion);
        double conversor=0;
        if(convertidor.getValorDelCambio()!=null){
            label_control.setText("");
            conversor = Double.parseDouble(convertidor.getValorDelCambio());
            operacion= String.valueOf(Operaciones_Matematicas.comprobarNumeroEntero(numero*conversor));
        }else if(convertidor.getValorDelCambio()==null && CB_uno.getValue().equals("Monedas")){
            label_control.setText("");
            conversor = Double.parseDouble(convertidor.getExchangeRate(String.valueOf(CB_dos.getValue()), String.valueOf(CB_tres.getValue())));
            operacion= String.valueOf(Operaciones_Matematicas.redondear(numero*conversor));
        }else if ("Longitud".equals(CB_uno.getValue())) {
            label_control.setText("");
            operacion=String.valueOf(operacionConvertir.convertir("Longitud",CB_dos.getValue().toString(),CB_tres.getValue().toString(),Double.parseDouble(operacion)));
        } else if (CB_uno.getValue().equals("Tiempo")) {
            label_control.setText("");
            operacion=String.valueOf(operacionConvertir.convertir("Tiempo",CB_dos.getValue().toString(),CB_tres.getValue().toString(),Double.parseDouble(operacion)));
        } else if (CB_uno.getValue().equals("Masa")) {
            label_control.setText("");
            operacion=String.valueOf(operacionConvertir.convertir("Masa",CB_dos.getValue().toString(),CB_tres.getValue().toString(),Double.parseDouble(operacion)));
        }else {
            label_control.setText("Sin Seleccionar (Azul)");
            return;
        }

            label_Resultado.setText(operacion);
    }

    @FXML
    public void click_limpiar(ActionEvent actionEvent) {
        operacion="";
        label_Resultado.setText(operacion);
    }

    @FXML
    public void opcionesDisponibles(Event event) {
        label_Resultado.setText("");
        CB_uno.getItems().clear();
        CB_dos.getItems().clear();
        CB_tres.getItems().clear();
        CB_uno.getItems().addAll(opciones);

        CB_uno.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null) {
                if (newValue.equals("Monedas")) {
                    CB_dos.getItems().clear();
                    CB_tres.getItems().clear();
                    CB_dos.getItems().addAll(convertidor.getTargetCurrencies());
                    CB_tres.getItems().addAll(convertidor.getTargetCurrencies());
                } else if (newValue.equals("Longitud")) {
                    CB_dos.getItems().clear();
                    CB_tres.getItems().clear();
                    CB_dos.getItems().addAll(operacionConvertir.getUnidadesLongitud());
                    CB_tres.getItems().addAll(operacionConvertir.getUnidadesLongitud());
                } else if (newValue.equals("Tiempo")) {
                    CB_dos.getItems().clear();
                    CB_tres.getItems().clear();
                    CB_dos.getItems().addAll(operacionConvertir.getUnidadesTiempo());
                    CB_tres.getItems().addAll(operacionConvertir.getUnidadesTiempo());
                } else if (newValue.equals("Masa")) {
                    CB_dos.getItems().clear();
                    CB_tres.getItems().clear();
                    CB_dos.getItems().addAll(operacionConvertir.getUnidadesMasa());
                    CB_tres.getItems().addAll(operacionConvertir.getUnidadesMasa());
                } else {
                    System.out.println("Selección no reconocida");
                }
            }
        });
    }



    @FXML
    public void click_punto(ActionEvent actionEvent) {
        operacion += '.';
        label_Resultado.setText(operacion);
    }


    @FXML
    public void click_cero(ActionEvent actionEvent) {
        operacion += '0';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_uno(ActionEvent actionEvent) {
        operacion += '1';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_dos(ActionEvent actionEvent) {
        operacion += '2';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_tres(ActionEvent actionEvent) {
        operacion += '3';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_cuatro(ActionEvent actionEvent) {
        operacion += '4';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_cinco(ActionEvent actionEvent) {
        operacion += '5';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_seis(ActionEvent actionEvent) {
        operacion += '6';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_siete(ActionEvent actionEvent) {
        operacion += '7';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_ocho(ActionEvent actionEvent) {
        operacion += '8';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_nueve(ActionEvent actionEvent) {
        operacion += '9';
        label_Resultado.setText(operacion);
    }


    @FXML
    public void CventanaValorMoneda(ActionEvent actionEvent) throws IOException {

        if(CB_uno.getValue()==null||CB_dos.getValue()==null||CB_tres.getValue()==null){
            label_Resultado.setText("Elige Moneda");

        }else {
            label_Resultado.setText("");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/panelConversion.fxml"));
            Pane root = (Pane) loader.load();

            PanelConversion controlador = loader.getController();
            controlador.setParentController(this);

            convertidor.setMonedaBase(CB_dos.getValue().toString());
            convertidor.setMonedaTarget(CB_tres.getValue().toString());
            controlador.setConvertidor(convertidor);

            Stage stage = new Stage();
            stage.setTitle("Panel de Ajustes Conversion");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();
        }

    }

    public void setConvertidor(ConvertidorMonedas modelo){
        convertidor.setValorDelCambio(modelo.getValorDelCambio());
        l_memoria.setText(convertidor.getValorDelCambio());
        memoria= convertidor.getValorDelCambio();

    }

    @FXML
    public void Mem_limpiar(ActionEvent actionEvent) {
        convertidor.setValorDelCambio(null);
        memoria="";
        l_memoria.setText("");
    }
}
