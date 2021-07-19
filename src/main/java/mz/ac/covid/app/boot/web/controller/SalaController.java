package mz.ac.covid.app.boot.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ac.covid.app.boot.domain.Sala;
import mz.ac.covid.app.boot.service.SalaService;

@Controller
@RequestMapping("/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping("cadastrar")
    public String cadastrar(Sala sala) {

        return "admin/pages/salas/add-sala";
    }

    @GetMapping("listar")
    public String listar(ModelMap model) {
        model.addAttribute("salas", salaService.buscarTodos());
        return "admin/pages/salas/list-sala";
    }

    /**
     * Metodo de pre actualizacao de registo de salas com recurso ao metodo
     * actualizar que faz o redirecionamento
     * 
     * @param id
     * @param model
     * @return
     */
    @GetMapping("editar/{id}")
    public String preActualizar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("sala", salaService.buscarPorId(id));

        return "admin/pages/salas/add-sala";
    }

    @PostMapping("editar")
    public String actualizar(Sala sala, RedirectAttributes atrr) {
        salaService.editar(sala);
        atrr.addFlashAttribute("success", "Sala actualizada com sucesso.");
        return "redirect:/salas/listar";
    }

    /**
     * Metodo que permite excluir uma Sala com base no seu id
     * 
     * @param id
     * @param model
     * @return
     */
    @GetMapping("apagar/{id}")
    public String apagar(@PathVariable("id") Long id, ModelMap model) {

        salaService.excluir(id);
        model.addAttribute("success", "Sala removida com sucesso.");

        return listar(model);
    }

    /**
     * metodo para fazer o registo de salas com recurso ao formulario de cadastro no
     * redir
     * 
     * @param departamento
     * @return
     */
    @PostMapping("gravar")
    public String gravar(Sala sala, RedirectAttributes atrr) {
        salaService.salvar(sala);
        atrr.addFlashAttribute("success", "Sala cadastrada com sucesso.");
        return "redirect:/salas/cadastrar";
    }

}
