package com.sharp.clinica_solar.interceptors;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.HandlerInterceptor;

import com.sharp.clinica_solar.models.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SesionInterceptor implements HandlerInterceptor {
	private static final Map<String, Integer> rutaRolMap = new HashMap<>();

	static {
		rutaRolMap.put("/", 1); // JefeCompras/home
		rutaRolMap.put("/inventario", 1);
		rutaRolMap.put("/pedidos", 1);
		rutaRolMap.put("/compra", 1);
		rutaRolMap.put("/proveedores", 1);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect("/login");
			return false;
		}

		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if (usuario == null) {
			response.sendRedirect("/login");
			return false;
		}

		String path = request.getRequestURI();

		Integer rolRequerido = rutaRolMap.get(path);
		if (rolRequerido != null && usuario.getIdRol() != rolRequerido) {
			response.sendRedirect("/login");
			return false;
		}

		return true;
	}
}
