package model;

import control.Operaciones;

public class Calculadora {

    private double num1;
    private double num2;
    private double resultado;
    public Operaciones operacion = new Operaciones();

    public Calculadora(double num1, double num2, double resultado, Operaciones operacion) {
        this.num1 = num1;
        this.num2 = num2;
        this.resultado = resultado;
        this.operacion = operacion;
    }

    public Calculadora() {
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getResultado() {
        return resultado;
    }

    public void setResultado(double resultado) {
        this.resultado = resultado;
    }

    public Double calcularResultado(String operador) {
        switch (operador) {
            case "+":
                resultado = operacion.sumar(num1, num2);
                return resultado;
            case "-":
                resultado = operacion.restar(num1, num2);
                return resultado;
            case "*":
                resultado = operacion.multiplicar(num1, num2);
                return resultado;
            case "/":
                resultado = operacion.dividir(num1, num2);
                return resultado;
            default:
                return resultado = 0;
        }
    }    
}
