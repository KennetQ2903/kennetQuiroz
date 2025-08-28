package com.tarea4.kennetQuiroz.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests para la clase Calculadora")
public class CalculadoraTest {
    private Calculadora calculadora;

    @BeforeEach
    public void setUp() {
        this.calculadora = new Calculadora();
    }

    // Tests para el método sumar
    @Test
    @DisplayName("Prueba suma de números positivos")
    public void testSumarNumerosPositivos() {
        int resultado = calculadora.sumar(5, 3);
        assertEquals(8, resultado, "5 + 3 debería ser 8");
    }

    @Test
    @DisplayName("Prueba suma con números negativos")
    public void testSumarNumerosNegativos() {
        int resultado = calculadora.sumar(-5, -3);
        assertEquals(-8, resultado, "-5 + (-3) debería ser -8");
    }

    // Tests para el método restar
    @Test
    @DisplayName("Prueba resta de números positivos")
    public void testRestarNumerosPositivos() {
        int resultado = calculadora.restar(10, 4);
        assertEquals(6, resultado, "10 - 4 debería ser 6");
    }

    @Test
    @DisplayName("Prueba resta con resultado negativo")
    public void testRestarResultadoNegativo() {
        int resultado = calculadora.restar(3, 7);
        assertEquals(-4, resultado, "3 - 7 debería ser -4");
    }

    // Tests para el método multiplicar
    @Test
    @DisplayName("Prueba multiplicación de números positivos")
    public void testMultiplicarNumerosPositivos() {
        int resultado = calculadora.multiplicar(6, 7);
        assertEquals(42, resultado, "6 * 7 debería ser 42");
    }

    @Test
    @DisplayName("Prueba multiplicación por cero")
    public void testMultiplicarPorCero() {
        int resultado = calculadora.multiplicar(5, 0);
        assertEquals(0, resultado, "5 * 0 debería ser 0");
    }

    // Tests para el método dividir
    @Test
    @DisplayName("Prueba división normal")
    public void testDividirNormal() {
        int resultado = calculadora.dividir(15, 3);
        assertEquals(5, resultado, "15 / 3 debería ser 5");
    }

    @Test
    @DisplayName("Prueba división por cero - debe lanzar excepción")
    public void testDividirPorCero() {
        ArithmeticException exception = assertThrows(
                ArithmeticException.class,
                () -> calculadora.dividir(10, 0),
                "Dividir por cero debería lanzar ArithmeticException");
        assertEquals("No se puede dividir por cero", exception.getMessage());
    }

    // Tests adicionales
    @Test
    @DisplayName("Prueba suma con cero")
    public void testSumarConCero() {
        int resultado = calculadora.sumar(7, 0);
        assertEquals(7, resultado, "7 + 0 debería ser 7");
    }

    @Test
    @DisplayName("Prueba multiplicación con números negativos")
    public void testMultiplicarConNegativos() {
        int resultado = calculadora.multiplicar(-4, 3);
        assertEquals(-12, resultado, "-4 * 3 debería ser -12");
    }
}
