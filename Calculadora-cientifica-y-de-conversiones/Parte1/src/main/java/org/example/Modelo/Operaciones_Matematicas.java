package org.example.Modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Operaciones_Matematicas {
    static double resultado;
    List<String> unidadesLongitud = List.of("mm", "cm", "dm", "m", "dam", "hm", "km");
    List<String> unidadesMasa = List.of("mg", "cg", "dg", "g", "dag", "hg", "kg", "T");
    List<String> unidadesTiempo = List.of("ms", "s", "m", "h", "d", "s", "m", "a");
    private Map<String, Double> factoresLongitud;
    private Map<String, Double> factoresTiempo;
    private Map<String, Double> factoresMasa;

    public Operaciones_Matematicas() {
        inicializarFactoresConversion();
    }

    public List<String> getUnidadesLongitud() {
        return unidadesLongitud;
    }

    public void setUnidadesLongitud(List<String> unidadesLongitud) {
        this.unidadesLongitud = unidadesLongitud;
    }

    public List<String> getUnidadesMasa() {
        return unidadesMasa;
    }

    public void setUnidadesMasa(List<String> unidadesMasa) {
        this.unidadesMasa = unidadesMasa;
    }

    public List<String> getUnidadesTiempo() {
        return unidadesTiempo;
    }

    public void setUnidadesTiempo(List<String> unidadesTiempo) {
        this.unidadesTiempo = unidadesTiempo;
    }

    public Map<String, Double> getFactoresLongitud() {
        return factoresLongitud;
    }

    public void setFactoresLongitud(Map<String, Double> factoresLongitud) {
        this.factoresLongitud = factoresLongitud;
    }

    public Map<String, Double> getFactoresTiempo() {
        return factoresTiempo;
    }

    public void setFactoresTiempo(Map<String, Double> factoresTiempo) {
        this.factoresTiempo = factoresTiempo;
    }

    public Map<String, Double> getFactoresMasa() {
        return factoresMasa;
    }

    public void setFactoresMasa(Map<String, Double> factoresMasa) {
        this.factoresMasa = factoresMasa;
    }

    public static String operacion(String operacion) {
        String[] partes = operacion.split("[/*\\-+^]");
        if (partes.length >= 2) {
            resultado = Double.parseDouble(partes[0]);
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
                    case '^':
                        resultado = Math.pow(resultado, numero);
                        break;
                    case '/':
                        if (numero != 0) {
                            resultado /= numero;
                        } else {
                            operacion = "Division por Cero";
                            return operacion;

                        }
                        break;
                }

            }

        } else {
            operacion = "Error! Formato incorrecto.";
            return operacion;
        }
        operacion= comprobarNumeroEntero(resultado);
        return operacion;
    }

    public static String control_trigo(String operacion){
        double result=0;
        if(!operacion.isEmpty()){
            String[] numeros= operacion.split("-");

            switch(numeros[1]){
                case "sen":
                    result = Math.sin(Math.toRadians(Integer.parseInt(numeros[0])));
                    operacion=redondear(result);
                    break;
                case "cos":
                    result = Math.cos(Math.toRadians(Integer.parseInt(numeros[0])));
                    operacion=redondear(result);
                    break;
                case "tan":
                    result = Math.tan(Math.toRadians(Integer.parseInt(numeros[0])));
                    operacion=redondear(result);
                    break;
                default:
                    result = 0;
                    operacion=redondear(result);
                    break;
            }
        }else{
            return "Introduce un digito";
        }
        return operacion;
    }
    public double convertir(String tipo, String unidadOrigen, String unidadDestino, double valor) {
        // Verificar el tipo de conversión y usar el mapa correspondiente
        Map<String, Double> factores = null;
        switch (tipo) {
            case "Longitud":
                factores = factoresLongitud;
                break;
            case "Tiempo":
                factores = factoresTiempo;
                break;
            case "Masa":
                factores = factoresMasa;
                break;
            default:
                throw new IllegalArgumentException("Tipo de conversión no soportado");
        }

        // Calcular el valor en la unidad base (ej: metros, segundos, gramos)
        double valorEnBase = valor * factores.get(unidadOrigen);

        // Convertir el valor base a la unidad destino
        double factorConversionDestino = factores.getOrDefault(unidadDestino, 0.0);
        if(factorConversionDestino == 0.0) {
            throw new IllegalArgumentException("Unidad de destino no soportada o desconocida");
        }

        return valorEnBase / factorConversionDestino;
    }
    private void inicializarFactoresConversion() {

        factoresLongitud = new HashMap<>();
        factoresLongitud.put("mm", 0.001);
        factoresLongitud.put("cm", 0.01);
        factoresLongitud.put("dm", 0.1);
        factoresLongitud.put("m", 1.0);
        factoresLongitud.put("dam", 10.0);
        factoresLongitud.put("hm", 100.0);
        factoresLongitud.put("km", 1000.0);


        factoresTiempo = new HashMap<>();
        factoresTiempo.put("ms", 0.001);
        factoresTiempo.put("s", 1.0);
        factoresTiempo.put("m", 60.0);
        factoresTiempo.put("h", 3600.0);
        factoresTiempo.put("d", 86400.0);
        factoresTiempo.put("S", 604800.0);
        factoresTiempo.put("M", 18144000.0);
        factoresTiempo.put("A", 6622560000.0);

        factoresMasa = new HashMap<>();
        factoresMasa.put("mg", 0.001);
        factoresMasa.put("cg", 0.01);
        factoresMasa.put("dg", 0.1);
        factoresMasa.put("g", 1.0);
        factoresMasa.put("dag", 10.0);
        factoresMasa.put("hg", 100.0);
        factoresMasa.put("kg", 1000.0);
        factoresMasa.put("T", 10000.0);
    }

    public static String comprobarNumeroEntero(double numero){
        String aux="";
        if (numero % 1 == 0) {
            aux=String.valueOf((int) numero);
        } else {
            aux=redondear(numero);
        }
        return aux;
    }

    public static String redondear(double numero){
        int decimales = 8;
        double factor = Math.pow(10, decimales);
        double numeroRedondeado = Math.round(numero * factor) / factor;

        return String.format("%.9f", numeroRedondeado);
    }

}


