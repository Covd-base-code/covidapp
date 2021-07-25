package mz.ac.covid.app.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mz.ac.covid.app.boot.service.InstituicaoSalaService;

@Controller
@RequestMapping("/salas-requisitadas")
public class InstituicaoSalaController {

    @Autowired
    InstituicaoSalaService instituicaoSalaService;

    @GetMapping("listar")
    public String listar(Model model) {
        model.addAttribute("requisitadas", instituicaoSalaService.pesquisarTodos());
        return "/admin/pages/lista-vacinacoes/list-salas-requisitadas";
    }

}
