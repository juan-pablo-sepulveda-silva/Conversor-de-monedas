package com.conversor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class Menu {

    public void mostrarMenu() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean salir = false;

        while (!salir) {
            System.out.println("Bienvenido al Conversor de Moneda");
            System.out.println("Seleccione la moneda de origen:");
            System.out.println("1. Pesos Colombianos (COP)");
            System.out.println("2. Dólares Americanos (USD)");
            System.out.println("3. Bolívares Venezolanos (VES)");
            System.out.println("4. Pesos Brasileños (BRL)");
            System.out.println("5. Salir");

            int option = Integer.parseInt(reader.readLine());

            switch (option) {
                case 1:
                    convertirMoneda("COP", reader);
                    break;
                case 2:
                    convertirMoneda("USD", reader);
                    break;
                case 3:
                    convertirMoneda("VES", reader);
                    break;
                case 4:
                    convertirMoneda("BRL", reader);
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void convertirMoneda(String fromCurrency, BufferedReader reader) throws IOException {
        System.out.println("Ingrese la cantidad a convertir:");
        double amount = Double.parseDouble(reader.readLine());

        System.out.println("Seleccione la moneda de destino:");
        System.out.println("1. Pesos Colombianos (COP)");
        System.out.println("2. Dólares Americanos (USD)");
        System.out.println("3. Bolívares Venezolanos (VES)");
        System.out.println("4. Pesos Brasileños (BRL)");

        int option = Integer.parseInt(reader.readLine());
        String toCurrency = "";

        switch (option) {
            case 1:
                toCurrency = "COP";
                break;
            case 2:
                toCurrency = "USD";
                break;
            case 3:
                toCurrency = "VES";
                break;
            case 4:
                toCurrency = "BRL";
                break;
            default:
                System.out.println("Opción no válida");
                return;
        }

        APIHandler apiHandler = new APIHandler();
        double exchangeRate = apiHandler.getExchangeRate(fromCurrency, toCurrency,amount);
        double convertedAmount = apiHandler.convert(amount, exchangeRate);
        DecimalFormat formatter = new DecimalFormat("###,###.00");
        String formattedAmount = formatter.format(convertedAmount);
        String originalAmount = formatter.format(amount);

        System.out.println(originalAmount + " " + fromCurrency + " equivale a " + formattedAmount + " " + toCurrency);
    }
}
