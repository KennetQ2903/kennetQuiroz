package com.tarea4.kennetQuiroz.services;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    /**
     * Simula el envío de un correo electrónico
     * 
     * @param email dirección de correo del destinatario
     * @return true si el envío fue exitoso
     */
    public boolean enviarCorreo(String email) {
        // Simulamos el envío de correo
        System.out.println("Enviando correo a: " + email);

        // En un caso real, aquí iría la lógica para enviar el correo
        // Por ejemplo, usando JavaMail API, SendGrid, etc.

        // Simulamos que siempre es exitoso
        return true;
    }

    /**
     * Envía un correo de bienvenida con un mensaje personalizado
     * 
     * @param email  dirección de correo del destinatario
     * @param nombre nombre del usuario
     * @return true si el envío fue exitoso
     */
    public boolean enviarCorreoBienvenida(String email, String nombre) {
        System.out.println("Enviando correo de bienvenida a: " + email + " para: " + nombre);
        // Simulamos el envío
        return true;
    }
}
