package com.conversor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Menu menu = new Menu();
            menu.mostrarMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
