package com.uca.capas.tarea3.controller;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Clase10Controller {
	
	@RequestMapping("/ingresar")
	public String index() {
		return "clases/clase10/index";
	}
	
	@RequestMapping("/parametros1")
	public ModelAndView parametros1(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		String usuario = request.getParameter("usuario");
		String apellido = request.getParameter("apellido");
		String fecha = request.getParameter("fecha");
		String lugar = request.getParameter("lugar");
		String colegio = request.getParameter("colegio");
		String tel = request.getParameter("tel");
		String cel = request.getParameter("cel");
		
		List<String> errores = new ArrayList<>();
		String mensaje= "Alumno ingresado con éxito";
		
		// Nombres
		if (usuario.length() < 1) errores.add("- El campo Nombres debe tener mínimo 1 carácter");
		if (usuario.length() > 25) errores.add("- El campo Nombres no puede ser mayor a 25 caracteres");
		
		
		// Apellidos
		if (apellido.length() < 1) errores.add("- El campo Apellido debe tener mínimo 1 carácter");
		if (apellido.length() > 25) errores.add("- El campo Apellido no puede ser mayor a 25 caracteres");
		
		
		// Fecha de Nacimiento
		if (fecha.isEmpty()) fecha = "2002-01-01";
		String limite = "2003-01-01";
				
		Date d1 = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(fecha, new ParsePosition(0));
		Date d2 = (Date) new SimpleDateFormat("yyyy-mm-dd").parse(limite, new ParsePosition(0));
		
		if (d1.compareTo(d2) > 0) errores.add("- El campo Fecha de Nacimiento no puede ser menor al 1 de enero de 2003");
		
		
		// Lugar de Nacimiento
		if (lugar.length() < 1) errores.add("- El campo Lugar de Nacimiento debe tener mínimo 1 carácter");
		if (lugar.length() > 25) errores.add("- El campo Lugar de Nacimiento no puede ser mayor a 25 caracteres");
		
		
		// Instituto o Colegio de procedencia
		if (colegio.length() < 1) errores.add("- El campo Instituto o Colegio de procedencia debe tener mínimo 1 carácter");
		if (colegio.length() > 100) errores.add("- El campo Instituto o Colegio de procedencia no puede ser mayor a 25 caracteres");
		
		// Teléfono fijo 
		if (tel.length() != 8) errores.add("- El campo Teléfono fijo debe tener 8 números");
		

		// Teléfono móvil
		if (cel.length() != 8) errores.add("- El campo Teléfono móvil debe tener 8 numeros");

		
		if (errores.size() == 0) {
			mav.addObject("mensaje", mensaje);
			mav.setViewName("clases/clase10/resultado2");

		} else {
			mav.addObject("errores", errores);
			mav.setViewName("clases/clase10/resultado");
		}
		
		return mav;	
	}
}