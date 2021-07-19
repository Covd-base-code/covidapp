package mz.ac.covid.app.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ac.covid.app.boot.domain.Departamento;
import mz.ac.covid.app.boot.domain.Funcionario;
import mz.ac.covid.app.boot.domain.Instituicao;
import mz.ac.covid.app.boot.service.InstituicaoService;
import mz.ac.covid.app.boot.service.DepartamentoService;
import mz.ac.covid.app.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private InstituicaoService instituicaoService;

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("cadastrar")
    public String cadastrar(Funcionario funcionario) {

        return "admin/pages/funcionarios/add-func";
    }

    @GetMapping("listar")
    public String listar(ModelMap model) {
        model.addAttribute("funcionarios", funcionarioService.pesquisarTodos());
        return "admin/pages/funcionarios/list-func";
    }

    /**
     * Metodo de pre actualizacao de registo de funcionarios com recurso ao metodo
     * actualizar que faz o redirecionamento
     * 
     * @param id
     * @param model
     * @return
     */
    @GetMapping("editar/{id}")
    public String preActualizar(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("funcionario", funcionarioService.pesquisarPorId(id));
        return "admin/pages/funcionarios/add-func";
    }

    @PostMapping("editar")
    public String actualizar(Funcionario funcionario, RedirectAttributes atrr) {
        funcionarioService.editar(funcionario);
        atrr.addFlashAttribute("success", "Funcionario actualizado com sucesso.");
        return "redirect:/funcionarios/listar";
    }

    /**
     * Metodo que permite excluir um departamento com base no seu id e somente se
     * ele nao tiver cargos vinculados a ele
     * 
     * @param id
     * @param model
     * @return
     */
    @GetMapping("apagar/{id}")
    public String apagar(@PathVariable("id") Long id, ModelMap model) {

        funcionarioService.apagar(id);
        model.addAttribute("success", "Funcionario removido com sucesso.");

        return listar(model);
    }

    /**
     * metodo para fazer o registo de funcionarios com recurso ao formulario de
     * cadastro no redir
     * 
     * @param departamento
     * @return
     */
    @PostMapping("gravar")
    public String gravar(Funcionario funcionario, RedirectAttributes atrr) {
        funcionarioService.registar(funcionario);
        atrr.addFlashAttribute("success", "Funcionario cadastrado com sucesso.");
        return "redirect:/funcionarios/cadastrar";
    }

    /**
     * Metodo para listar todos os instituicoes e mostrar na combobox presente no
     * formulario
     * 
     * @return
     */
    @ModelAttribute("instituicoes")
    public List<Instituicao> listaInstituicoes() {
        return instituicaoService.pesquisarTodos();
    }

    /**
     * Metodo para listar todos os departamentos e mostrar na combobox presente no
     * formulario
     * 
     * @return
     */
    @ModelAttribute("departamentos")
    public List<Departamento> listaDepartamentos() {
        return departamentoService.pesquisarTodos();
    }
}
