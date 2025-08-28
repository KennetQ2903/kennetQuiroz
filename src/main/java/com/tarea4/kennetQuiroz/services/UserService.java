package com.tarea4.kennetQuiroz.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final EmailService emailService;

    @Autowired
    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Registra un nuevo usuario y le envía un correo de confirmación
     * 
     * @param email email del usuario a registrar
     * @return true si el registro fue exitoso
     */
    public boolean registrarUsuario(String email) {
        // Validamos que el email no sea nulo o vacío
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email no puede ser nulo o vacío");
        }

        // Simulamos la lógica de registro del usuario
        System.out.println("Registrando usuario con email: " + email);

        // Enviamos el correo de confirmación
        boolean correoEnviado = emailService.enviarCorreo(email);

        if (correoEnviado) {
            System.out.println("Usuario registrado exitosamente: " + email);
            return true;
        } else {
            System.out.println("Error al enviar correo de confirmación");
            return false;
        }
    }

    /**
     * Crea un nuevo usuario con nombre y envía correo de bienvenida
     * 
     * @param email  email del usuario
     * @param nombre nombre del usuario
     * @return true si el proceso fue exitoso
     */
    public boolean crearUsuario(String email, String nombre) {
        if (email == null || email.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Email y nombre son requeridos");
        }

        System.out.println("Creando usuario: " + nombre + " con email: " + email);

        // Enviamos correo de bienvenida
        boolean correoEnviado = emailService.enviarCorreoBienvenida(email, nombre);

        return correoEnviado;
    }

    /**
     * Procesa el olvido de contraseña enviando un correo
     * 
     * @param email email del usuario
     * @return true si el correo fue enviado
     */
    public boolean olvidoContrasena(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        System.out.println("Procesando olvido de contraseña para: " + email);
        return emailService.enviarCorreo(email);
    }
}
