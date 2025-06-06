package com.sharp.clinica_solar.services;

import org.springframework.stereotype.Service;

import com.sharp.clinica_solar.models.Usuario;
import com.sharp.clinica_solar.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public boolean iniciarSesion(String nombreUsuario, String contrasena) {
		Usuario usuarioEncontrado = usuarioRepository.findByNomUsuario(nombreUsuario);

		if (usuarioEncontrado != null) {
			if (usuarioEncontrado.getContraUsuario().equals(contrasena)) {
				System.out.println("Inicio de sesión exitoso para el usuario: " + nombreUsuario);
				return true; // Inicio de sesión exitoso
			} else {
				System.out.println("Contraseña incorrecta para el usuario: " + nombreUsuario);
				return false; // Contraseña incorrecta
			}
		} else {
			System.out.println("Usuario no encontrado: " + nombreUsuario);
			return false; // Usuario no encontrado
		}
	}

	public Usuario buscarPorNombre(String nombreUsuario) {
		return usuarioRepository.findByNomUsuario(nombreUsuario);
	}
}
