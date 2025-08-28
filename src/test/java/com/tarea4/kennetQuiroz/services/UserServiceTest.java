package com.tarea4.kennetQuiroz.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceTest {
    @Mock
    private EmailService emailMock;

    private UserService userService;

    @BeforeEach
    void setUp() {
        // Inicializa los mocks
        MockitoAnnotations.openMocks(this);
        // Inyecta el mock en el servicio
        userService = new UserService(emailMock);
    }

    @Test
    @DisplayName("Test básico - Verificar que se llama al servicio de email")
    void testEnviarCorreo() {
        // Configuramos el comportamiento del mock
        when(emailMock.enviarCorreo("juan@example.com")).thenReturn(true);

        // Ejecutamos el método a probar
        boolean resultado = userService.registrarUsuario("juan@example.com");

        // Verificamos que se llamó al método y el resultado
        verify(emailMock).enviarCorreo("juan@example.com");
        assertTrue(resultado);
    }

    @Test
    @DisplayName("Test con mock manual (sin anotaciones)")
    void testConMockManual() {
        // Creamos el mock manualmente (como en el ejemplo del ejercicio)
        EmailService emailMock = mock(EmailService.class);
        UserService userService = new UserService(emailMock);

        // Configuramos el comportamiento del mock
        when(emailMock.enviarCorreo("juan@example.com")).thenReturn(true);

        // Ejecutamos el método
        userService.registrarUsuario("juan@example.com");

        // Verificamos que fue llamado correctamente
        verify(emailMock).enviarCorreo("juan@example.com");
    }

    @Test
    @DisplayName("Test verificando número de llamadas")
    void testVerificarNumeroDeLlamadas() {
        when(emailMock.enviarCorreo(anyString())).thenReturn(true);

        userService.registrarUsuario("user1@example.com");
        userService.registrarUsuario("user2@example.com");

        // Verificamos que se llamó exactamente 2 veces
        verify(emailMock, times(2)).enviarCorreo(anyString());
        verify(emailMock).enviarCorreo("user1@example.com");
        verify(emailMock).enviarCorreo("user2@example.com");
    }

    @Test
    @DisplayName("Test cuando el email service falla")
    void testCuandoEmailServiceFalla() {
        // Configuramos que el mock retorne false (fallo)
        when(emailMock.enviarCorreo("usuario@example.com")).thenReturn(false);

        boolean resultado = userService.registrarUsuario("usuario@example.com");

        assertFalse(resultado);
        verify(emailMock).enviarCorreo("usuario@example.com");
    }

    @Test
    @DisplayName("Test con argumentos inválidos")
    void testConArgumentosInvalidos() {
        // Verificamos que lanza excepción y no se llama al mock
        assertThrows(IllegalArgumentException.class, () -> {
            userService.registrarUsuario(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registrarUsuario("");
        });

        // Verificamos que nunca se llamó al emailService
        verify(emailMock, never()).enviarCorreo(anyString());
    }

    @Test
    @DisplayName("Test crear usuario con correo de bienvenida")
    void testCrearUsuarioConBienvenida() {
        when(emailMock.enviarCorreoBienvenida("maria@example.com", "Maria")).thenReturn(true);

        boolean resultado = userService.crearUsuario("maria@example.com", "Maria");

        assertTrue(resultado);
        verify(emailMock).enviarCorreoBienvenida("maria@example.com", "Maria");
        // Verificamos que NO se llamó al método enviarCorreo simple
        verify(emailMock, never()).enviarCorreo(anyString());
    }

    @Test
    @DisplayName("Test olvido de contraseña")
    void testOlvidoContrasena() {
        when(emailMock.enviarCorreo("forgot@example.com")).thenReturn(true);

        boolean resultado = userService.olvidoContrasena("forgot@example.com");

        assertTrue(resultado);
        verify(emailMock).enviarCorreo("forgot@example.com");
    }

    @Test
    @DisplayName("Test usando argumentMatchers")
    void testConArgumentMatchers() {
        // Usamos matchers para cualquier string que contenga @
        when(emailMock.enviarCorreo(contains("@"))).thenReturn(true);

        userService.registrarUsuario("test@gmail.com");
        userService.registrarUsuario("otro@yahoo.com");

        verify(emailMock, times(2)).enviarCorreo(contains("@"));
    }
}
