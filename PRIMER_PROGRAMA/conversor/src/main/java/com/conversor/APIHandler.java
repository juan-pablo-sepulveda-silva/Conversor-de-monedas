package com.conversor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Level;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.java.Log;
import okhttp3.Request;

@Log
public class APIHandler {

    private String API_KEY = "c2199dba55a7026763f68da8dfabcf0a";
    private String URL = "http://api.exchangeratesapi.io/v1";

    // Método para hacer una solicitud HTTP y obtener la tasa de cambio
    public double getExchangeRate(String from, String to, double amount) {
        //OkHttpClient client = new OkHttpClient();
        String endpoint;
        try {
            endpoint = String.format("%s/convert?access_key=%s&from=%s&to=%s&amount=%s", URL, API_KEY,
                    URLEncoder.encode(from, "UTF-8"), URLEncoder.encode(to, "UTF-8"),
                    URLEncoder.encode(String.valueOf(amount), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.log(Level.SEVERE, e.getMessage());
            throw new IllegalAccessError("no se pudo construir la ruta");
        }
        Request request = new Request.Builder().url(endpoint).get().build();
        String json = null;
        /*
        try {
            response = call(request)
            json = response.getBody();
        }catch
        */
        // fingimos que obtubimos algo
        json = "{\"from\":\"BRL\",\"to\":\"VES\",\"original_amount\":\"80.0\",\"convert_amount\":\"80000000000\"}";
        ObjectMapper convertidor = new ObjectMapper();
        Conversor conversion = new Conversor();
        try {
            conversion = convertidor.readValue(json, Conversor.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return conversion.getConvertAmount();
    }

    // Método para convertir la cantidad de una moneda a otra
    public double convert(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }
}
