package org.example.Controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.Modelo.ConvertidorMonedas;

import java.net.URL;
import java.util.ResourceBundle;

public class PanelConversion implements Initializable {


    @FXML
    private TextField TF_Valor;
    private ConvertidorMonedas convertidor;
    @FXML
    private Button BTN_ACEPTARySALIR;
    @FXML
    private Button BTN_SALIR;
    private CalculadoraConversionesController controladorPadre;
    @FXML
    private Label label_Cambio;
    @FXML
    private Label label_Base;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        convertidor =new ConvertidorMonedas();
        cargarComboBox();
    }



    public void setParentController(CalculadoraConversionesController controlPadre) {
        this.controladorPadre = controlPadre;
    }
    public void setConvertidor(ConvertidorMonedas modelo) {
        label_Base.setText(modelo.getMonedaBase());
        label_Cambio.setText(modelo.getMonedaTarget());
        actualizarValor();
    }




    @FXML
    public void BTN_AceptarySalir(ActionEvent actionEvent) {
        convertidor.setValorDelCambio(TF_Valor.getText().toString());
        if(controladorPadre != null) {
            controladorPadre.setConvertidor(convertidor);
        }
        Stage miStage = (Stage) this.BTN_SALIR.getScene().getWindow();
        miStage.close();
    }

    @FXML
    public void BTN_Salir(ActionEvent actionEvent) {
        Stage miStage = (Stage) this.BTN_SALIR.getScene().getWindow();
        miStage.close();
    }

    private void cargarComboBox(){
        label_Base.setText(convertidor.getMonedaBase());
        label_Cambio.setText(convertidor.getMonedaTarget());
        //CB_Base.setOnAction(event -> actualizarValor());
        //CB_Cambio.setOnAction(event -> actualizarValor());

    }
    private void actualizarValor(){
        if(label_Base.getText()!=null && label_Cambio.getText()!=null){
            TF_Valor.setText(convertidor.getExchangeRate(label_Base.getText(),label_Cambio.getText()));
        }
    }
}
