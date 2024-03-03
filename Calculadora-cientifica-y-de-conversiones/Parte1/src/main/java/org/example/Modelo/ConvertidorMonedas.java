package org.example.Modelo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConvertidorMonedas {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    private  String monedaBase;
    private  String monedaTarget;

    private  String valorDelCambio;
    private final String [] targetCurrencies = {"EUR","USD", "GBP", "AUD", "CAD", "CHF", "CNY", "SEK", "NZD", "NOK", "SGD", "MXN", "BRL", "ZAR", "HKD", "DKK"};

    public String getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = String.format("https://api.frankfurter.app/latest?from=%s&to=%s", baseCurrency, targetCurrency);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();

            JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
            JsonObject rates = jsonResponse.getAsJsonObject("rates");
            String exchangeRate = rates.get(targetCurrency).getAsString();
            return exchangeRate;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error al obtener la tasa de cambio";
        }
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaTarget() {
        return monedaTarget;
    }

    public void setMonedaTarget(String monedaTarget) {
        this.monedaTarget = monedaTarget;
    }

    public String getValorDelCambio() {
        return valorDelCambio;
    }

    public void setValorDelCambio(String valorDelCambio) {
        this.valorDelCambio = valorDelCambio;
    }

    public String[] getTargetCurrencies() {
        return targetCurrencies;
    }
}
