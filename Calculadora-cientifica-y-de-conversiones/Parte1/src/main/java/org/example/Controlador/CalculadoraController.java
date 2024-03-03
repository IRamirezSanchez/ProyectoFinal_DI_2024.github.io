package org.example.Controlador;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.Modelo.Operaciones_Matematicas;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CalculadoraController implements Initializable
{

    @FXML
    private Button c_multiplicar;
    @FXML
    private Button b_tres;
    @FXML
    private Button b_siete;
    @FXML
    private Button b_uno;
    @FXML
    private Button c_dividir;
    @FXML
    private Button b_cuatro;
    @FXML
    private Label label_control;
    @FXML
    private Button c_sumar;
    @FXML
    private Button b_ocho;
    @FXML
    private Button b_seis;
    @FXML
    private Button c_restar;
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
    private Button c_igual;
    private String operacion;
    private String enMemoria;
    private String enMemoria2;
    @FXML
    private Button b_nueve;
    @FXML
    private Button c_memoriaMas;
    @FXML
    private Button c_punto;
    @FXML
    private Button c_signoMasMenos;
    @FXML
    private Button c_memoriaMenos;
    boolean flag;
    @FXML
    private Label l_memoria;
    @FXML
    private MenuItem cientifica;
    @FXML
    private AnchorPane ventanaNormal;
    private HistorialCalculadoraController historial;
    private List<String> listaOperaciones;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        operacion= "";
        enMemoria= "";
        flag=false;
        controlOperador(true);
        label_Resultado.requestFocus();
        listaOperaciones=new ArrayList<>();



    }
    private void mostrarMensajeError(String mensaje) {
        label_control.setText(mensaje);
        label_control.setVisible(true);
        label_Resultado.setText("");
    }

    @FXML
    public void click_cero(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "0");controlOperador(false);}

    @FXML
    public void click_uno(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "1");controlOperador(false);}

    @FXML
    public void click_dos(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "2");controlOperador(false);}

    @FXML
    public void click_tres(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "3");controlOperador(false);}

    @FXML
    public void click_cuatro(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "4");controlOperador(false);}

    @FXML
    public void click_cinco(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "5");controlOperador(false);}

    @FXML
    public void click_seis(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "6");controlOperador(false);}

    @FXML
    public void click_siete(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "7");controlOperador(false);}

    @FXML
    public void click_ocho(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "8");controlOperador(false);}

    @FXML
    public void click_nueve(ActionEvent actionEvent) {label_Resultado.setText(operacion+= "9");controlOperador(false);}

    @FXML
    public void click_sumar(ActionEvent actionEvent) {
        label_Resultado.setText(operacion += "+");
        if(operacion.equals("+")){
            operacion="";
        }else {
            controlBotones(false);
        }
        controlOperador(true);
    }

    @FXML
    public void click_dividir(ActionEvent actionEvent) {
        label_Resultado.setText(operacion+= "/");
        if(operacion.equals("/")){
            operacion="";
        }else {
            controlBotones(false);
        }
        controlOperador(true);
    }

    @FXML
    public void click_restar(ActionEvent actionEvent) {
        label_Resultado.setText(operacion+= "-");
        if(operacion.equals("-")){
            operacion="";
        }else {
            controlBotones(false);
        }
        controlOperador(true);
    }

    @FXML
    public void click_multiplicar(ActionEvent actionEvent) {
        label_Resultado.setText(operacion+= "*");
        if(operacion.equals("*")){
            operacion="";
        }else {
            controlBotones(false);
        }
        controlOperador(true);
    }

    @FXML
    public void click_punto(ActionEvent actionEvent) {
        if (!operacion.isEmpty() && !operacion.endsWith(".") && !operacion.matches(".*[+\\-*/]\\d*\\.\\d*$")) {
            operacion += ".";
            label_Resultado.setText(operacion);
        }
    }

    @FXML
    public void click_memoriaGuardar(ActionEvent actionEvent) {
        enMemoria2=operacion;
        double resultadoAux=Double.parseDouble(enMemoria2);
        if (resultadoAux % 1 == 0) {
            l_memoria.setText(String.valueOf((int) resultadoAux));
        } else {
            l_memoria.setText(String.valueOf(resultadoAux));
        }
    }

    @FXML
    public void click_signoMasMenos(ActionEvent actionEvent) {
        if (!operacion.startsWith("+") && !operacion.startsWith("-")) {
            if (flag) {
                operacion = "-" + operacion;
                flag = false;
            } else {
                operacion = "+" + operacion;
                flag = true;
            }
        } else {
            if (operacion.startsWith("+")) {
                operacion = operacion.replace("+", "-");
            } else if (operacion.startsWith("-")) {
                operacion = operacion.replace("-", "+");
            }
        }

        label_Resultado.setText(operacion);
    }

    @FXML
    public void click_memoriaRecuperar(ActionEvent actionEvent) {
        try {
            if (enMemoria2 != null) {
                label_Resultado.setText(operacion = operacion + enMemoria2);
                l_memoria.setText("");
                enMemoria2="";
                controlOperador(false);
            }
        }catch(NullPointerException e){
            e.getMessage();
        }
    }

    @FXML
    public void click_resultado(ActionEvent actionEvent) {
        listaOperaciones.add(operacion);
        String [] partes = operacion.split("[/*\\-+]");
        if (partes.length >= 2) {
            double resultado = Double.parseDouble(partes[0]);
            // Iterar sobre los operadores y números restantes
            for (int i = 1; i < partes.length; i++) {
                    // recorro parte  para saber cuanto ocupa para saber si el siguiente valor es operador
                    char operador = operacion.charAt(partes[i - 1].length());

                        double numero = Double.parseDouble(partes[i]);

                        switch (operador) {
                            case '+':
                                resultado += numero;
                                break;
                            case '-':
                                resultado -= numero;
                                break;
                            case '*':
                                resultado *= numero;
                                break;
                            case '/':
                                // Manejar la división por cero
                                if (numero != 0) {
                                    resultado /= numero;
                                } else {
                                    mostrarMensajeError("Error: División por cero.");
                                    controlBotones(true);
                                    return;
                                }
                                break;
                        }

            }
            if (resultado % 1 == 0) {
                label_Resultado.setText(String.valueOf((int) resultado));
                enMemoria=String.valueOf((int)resultado);
            } else {
                label_Resultado.setText(Operaciones_Matematicas.redondear(resultado));
                enMemoria=Operaciones_Matematicas.redondear(resultado);
            }
            controlBotones(true);
            label_control.setVisible(false);
            enMemoria=String.valueOf(resultado);
            operacion=enMemoria;
            listaOperaciones.add(operacion);
            enMemoria="";
        } else {
            mostrarMensajeError("Error! Formato incorrecto.");
        }


    }

    @FXML
    public void click_resetear(ActionEvent actionEvent) {
        label_control.setVisible(false);
        label_Resultado.setText(operacion="");
        controlBotones(false);
        controlOperador(true);
    }

    private void controlBotones(boolean aux){
        b_cero.setDisable(aux);
        b_uno.setDisable(aux);
        b_dos.setDisable(aux);
        b_tres.setDisable(aux);
        b_cuatro.setDisable(aux);
        b_cinco.setDisable(aux);
        b_seis.setDisable(aux);
        b_siete.setDisable(aux);
        b_ocho.setDisable(aux);
        b_nueve.setDisable(aux);
    }

    private void controlOperador(boolean aux){
        c_dividir.setDisable(aux);
        c_igual.setDisable(aux);
        c_multiplicar.setDisable(aux);
        c_sumar.setDisable(aux);
        c_restar.setDisable(aux);
        c_signoMasMenos.setDisable(aux);
    }

    public double getTamañoPreferidoAncho() {
        return ventanaNormal.getPrefWidth();
    }

    public double getTamañoPreferidoAlto() {
        return ventanaNormal.getPrefHeight();
    }
    @FXML
    public void abrirCientifica(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/calculadoraCientificaController.fxml"));
        AnchorPane panel = loader.load();

        CalculadoraCientificaController cientificaController = loader.getController();

        panel.setPrefWidth(cientificaController.getTamañoPreferidoAncho());
        panel.setPrefHeight(cientificaController.getTamañoPreferidoAlto());

        Scene nuevaScene = new Scene(panel);

        Stage stage = (Stage) ventanaNormal.getScene().getWindow();
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

        Stage stage = (Stage) ventanaNormal.getScene().getWindow();
        stage.setScene(nuevaScene);

        stage.show();
    }
    @FXML
    public void pressedTeclado(Event event) {
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
        controlOperador(false);
    }

    @FXML
    public void CventanaHistorial(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/Vista/historialCalculadoraController.fxml"));
        Pane root = (Pane) loader.load();

        //Actualizar información del modelo

        historial = loader.getController();
        historial.setListaElementos(listaOperaciones,this);
        Scene scene = new Scene(root);
        Stage stageWindow = new Stage();
        stageWindow.setTitle("Ventana Secundaria");
        stageWindow.setScene(scene);
        stageWindow.initModality(Modality.APPLICATION_MODAL);
        stageWindow.showAndWait();

        //Regenerar información
    }
}