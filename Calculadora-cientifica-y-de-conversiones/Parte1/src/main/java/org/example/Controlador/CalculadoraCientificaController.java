package org.example.Controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.Modelo.Operaciones_Matematicas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CalculadoraCientificaController implements Initializable {

    @FXML
    private Label label_control;
    @FXML
    private Label label_Resultado;

    @FXML
    private Button c_memoriaMas;
    @FXML
    private Button c_memoriaMenos;
    @FXML
    private Button c_signoMasMenos;


    @FXML
    private Button c_dividir;
    @FXML
    private Button c_multiplicar;
    @FXML
    private Button c_sumar;
    @FXML
    private Button c_restar;
    @FXML
    private Button c_potencia;
    @FXML
    private Button c_sen;
    @FXML
    private Button c_tan;
    @FXML
    private Button c_cos;


    @FXML
    private Button c_igual;
    @FXML
    private Button c_limpiar;
    @FXML
    private Label l_memoria;


    @FXML
    private Button b_cinco;
    @FXML
    private Button b_cuatro;
    @FXML
    private Button c_punto;
    @FXML
    private Button b_ocho;
    @FXML
    private Button b_cero;
    @FXML
    private Button b_dos;
    @FXML
    private Button b_siete;
    @FXML
    private Button b_seis;
    @FXML
    private Button b_nueve;
    @FXML
    private Button b_tres;
    @FXML
    private Button b_uno;
    private String operacion;
    private String memoria;

    @FXML
    private AnchorPane ventanaCientifica;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operacion = "";
        memoria = "";
        label_Resultado.requestFocus();

    }


    @FXML
    public void click_resultado(ActionEvent actionEvent) {
        String resultadoAux = "";
        operacion.replace(',', '.');
        resultadoAux = Operaciones_Matematicas.operacion(operacion);
        label_Resultado.setText(resultadoAux);
        operacion = resultadoAux;
    }

    @FXML
    public void click_memoriaGuardar(ActionEvent actionEvent) {
        memoria = operacion;
        l_memoria.setText(memoria);
    }

    @FXML
    public void click_signoMasMenos(ActionEvent actionEvent) {
    }

    @FXML
    public void click_memoriaRecuperar(ActionEvent actionEvent) {
        operacion += memoria;
        label_Resultado.setText(operacion);
        memoria = "";
    }

    @FXML
    public void click_resetear(ActionEvent actionEvent) {
        operacion = "";
        label_Resultado.setText(operacion);
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
    public void click_sumar(ActionEvent actionEvent) {
        operacion += '+';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_dividir(ActionEvent actionEvent) {
        operacion += '/';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_restar(ActionEvent actionEvent) {
        operacion += '-';
        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_multiplicar(ActionEvent actionEvent) {
        operacion += '*';
        label_Resultado.setText(operacion);
    }


    @FXML
    public void click_sen(ActionEvent actionEvent) {
        operacion += "-sen";
        memoria = Operaciones_Matematicas.control_trigo(operacion);
        label_Resultado.setText(memoria);
        operacion = memoria;
    }

    @FXML
    public void click_tan(ActionEvent actionEvent) {
        operacion += "-tan";
        memoria = Operaciones_Matematicas.control_trigo(operacion);
        label_Resultado.setText(memoria);
        operacion = memoria;
    }

    @FXML
    public void click_cos(ActionEvent actionEvent) {
        operacion += "-cos";
        memoria = Operaciones_Matematicas.control_trigo(operacion);
        label_Resultado.setText(memoria);
        operacion = memoria;

    }

    @FXML
    public void click_potencia(ActionEvent actionEvent) {
        operacion += "^";
        label_Resultado.setText(operacion);
    }


    public double getTamañoPreferidoAncho() {
        return ventanaCientifica.getPrefWidth();
    }

    public double getTamañoPreferidoAlto() {
        return ventanaCientifica.getPrefHeight();
    }


    @FXML
    public void CventanaNormal(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/calculadoraController.fxml"));
        AnchorPane panel = loader.load();

        CalculadoraController normalController = loader.getController();

        panel.setPrefWidth(normalController.getTamañoPreferidoAncho());
        panel.setPrefHeight(normalController.getTamañoPreferidoAlto());

        Scene nuevaScene = new Scene(panel);

        Stage stage = (Stage) ventanaCientifica.getScene().getWindow();
        stage.setScene(nuevaScene);

        stage.show();
    }

    @FXML
    public void CventanaConversor(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/calculadoraConversionesController.fxml"));
        AnchorPane panel = loader.load();

        CalculadoraConversionesController normalController = loader.getController();

        panel.setPrefWidth(normalController.getTamañoPreferidoAncho());
        panel.setPrefHeight(normalController.getTamañoPreferidoAlto());

        Scene nuevaScene = new Scene(panel);

        Stage stage = (Stage) ventanaCientifica.getScene().getWindow();
        stage.setScene(nuevaScene);

        stage.show();
    }
    @FXML
    public void porTeclado(Event event) {
        if (event instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) event;
            String teclaPresionada = keyEvent.getText();
            if (teclaPresionada.matches("[0-9+\\-/*,.]")) {
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


}