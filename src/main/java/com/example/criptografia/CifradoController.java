package com.example.criptografia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CifradoController {

    @GetMapping("/")
    public String mostrarFormulario() {
        return "index";
    }

    @PostMapping("/procesar")
    public String procesarTexto(@RequestParam("texto") String texto,
                                @RequestParam("corrimiento") int corrimiento,
                                @RequestParam("accion") String accion,
                                Model modelo) {
        String resultado;
        if ("encriptar".equals(accion)) {
            resultado = encriptarCesar(texto, corrimiento);
        } else {
            resultado = desencriptarCesar(texto, corrimiento);
        }
        modelo.addAttribute("resultado", resultado);
        modelo.addAttribute("texto", texto);
        modelo.addAttribute("corrimiento", corrimiento);
        return "index";
    }

    private String encriptarCesar(String texto, int n) {
        String alfabeto = "abcdefghijklmnopqrstuvwxyzñ";
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toLowerCase().toCharArray()) {
            if (alfabeto.indexOf(c) != -1) {
                int nuevoIndice = (alfabeto.indexOf(c) + n) % alfabeto.length();
                resultado.append(alfabeto.charAt(nuevoIndice));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    private String desencriptarCesar(String texto, int n) {
        String alfabeto = "abcdefghijklmnopqrstuvwxyzñ";
        StringBuilder resultado = new StringBuilder();
        for (char c : texto.toLowerCase().toCharArray()) {
            if (alfabeto.indexOf(c) != -1) {
                int nuevoIndice = (alfabeto.indexOf(c) - n + alfabeto.length()) % alfabeto.length();
                resultado.append(alfabeto.charAt(nuevoIndice));
            } else {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }
}
