package com.xhc.codegen;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        GeneratorManager generatorManager = new GeneratorManager();
        try {
            generatorManager.Start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
