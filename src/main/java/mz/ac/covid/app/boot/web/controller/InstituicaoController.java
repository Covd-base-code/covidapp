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

import mz.ac.covid.app.boot.domain.Instituicao;
import mz.ac.covid.app.boot.domain.Departamento;
import mz.ac.covid.app.boot.domain.Funcionario;
import mz.ac.covid.app.boot.service.InstituicaoService;
import mz.ac.covid.app.boot.service.DepartamentoService;
import mz.ac.covid.app.boot.service.FuncionarioService;

@Controller
@RequestMapping("/instituicoes")
public class InstituicaoController {

	@Autowired
	private InstituicaoService instituicaoService;

	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping("cadastrar")
	public String cadastrar(Instituicao instituicao) {

		return "admin/pages/instituicoes/add-instituicao";
	}

	@GetMapping("listar")
	public String listar(ModelMap model) {
		model.addAttribute("instituicoes", instituicaoService.pesquisarTodos());
		return "admin/pages/instituicoes/list-instituicao";
	}

	/**
	 * Metodo de pre actualizacao de registo de instituicoes com recurso ao metodo
	 * actualizar que faz o redirecionamento
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("editar/{id}")
	public String preActualizar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("instituicao", instituicaoService.pesquisarPorId(id));

		return "admin/pages/instituicoes/add-instituicao";
	}

	@PostMapping("editar")
	public String actualizar(Instituicao instituicao, RedirectAttributes atrr) {
		instituicaoService.editar(instituicao);
		atrr.addFlashAttribute("success", "instituicao actualizada com sucesso.");
		return "redirect:/instituicoes/listar";
	}

	/**
	 * Metodo que permite excluir um instituicoes com base no seu id e somente se
	 * ele nao tiver cargos vinculados a ele
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("apagar/{id}")
	public String apagar(@PathVariable("id") Long id, ModelMap model) {

		instituicaoService.apagar(id);
		model.addAttribute("success", "Instituicao removida com sucesso.");

		return listar(model);
	}

	/**
	 * metodo para fazer o registo de instituicoes com recurso ao formulario de
	 * cadastro no redir
	 * 
	 * @param departamento
	 * @return
	 */
	@PostMapping("gravar")
	public String gravar(Instituicao instituicao, RedirectAttributes atrr) {
		instituicaoService.registar(instituicao);
		atrr.addFlashAttribute("success", "Instituicao cadastrada com sucesso.");
		return "redirect:/instituicoes/cadastrar";
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

	/**
	 * Metodo para listar todos os funcionarios e mostrar na combobox presente no
	 * formulario
	 * 
	 * @return
	 */
	@ModelAttribute("funcionarios")
	public List<Funcionario> listaFuncionarios() {
		return funcionarioService.pesquisarTodos();
	}
}
