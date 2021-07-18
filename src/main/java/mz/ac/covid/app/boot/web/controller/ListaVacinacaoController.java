package mz.ac.covid.app.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ac.covid.app.boot.domain.Funcionario;
import mz.ac.covid.app.boot.domain.Instituicao;
import mz.ac.covid.app.boot.domain.InstituicaoSala;
import mz.ac.covid.app.boot.domain.ListaVacinacao;
import mz.ac.covid.app.boot.domain.Sala;
import mz.ac.covid.app.boot.repository.IFuncionarioRepository;
import mz.ac.covid.app.boot.service.FuncionarioService;
import mz.ac.covid.app.boot.service.InstituicaoSalaService;
import mz.ac.covid.app.boot.service.InstituicaoService;
import mz.ac.covid.app.boot.service.ListaVacinacaoService;
import mz.ac.covid.app.boot.service.SalaService;

@Controller
@RequestMapping("/vacinacoes")
public class ListaVacinacaoController {

  @Autowired
  private ListaVacinacaoService listaVacinacaoService;

  @Autowired
  private InstituicaoService instituicaoService;

  @Autowired
  private FuncionarioService funcionarioService;

  @Autowired
  private SalaService salaService;

  @Autowired
  private InstituicaoSalaService instituicaoSalaService;

  @Autowired
  private IFuncionarioRepository funcionarioRepository;

  @GetMapping("cadastrar")
  public String cadastrar(ListaVacinacao listaVacinacao) {

    return "/admin/pages/lista-vacinacoes/add-lista";
  }

  @GetMapping("listar")
  public String listar(ModelMap model) {
    model.addAttribute("listavacinacoes", listaVacinacaoService.pesquisarTodos());
    return "/admin/pages/lista-vacinacoes/list-vacinacoes";
  }

  /**
   * Metodo de pre actualizacao de registo de listas com recurso ao metodo
   * actualizar que faz o redirecionamento
   * 
   * @param id
   * @param model
   * @return
   */
  @GetMapping("editar/{id}")
  public String preActualizar(@PathVariable("id") Long id, ModelMap model) {
    model.addAttribute("lista", listaVacinacaoService.pesquisarPorId(id));
    return "/admin/pages/lista-vacinacoes/add-lista";
  }

  @PostMapping("editar")
  public String actualizar(ListaVacinacao listaVacinacao, RedirectAttributes atrr) {
    listaVacinacaoService.editar(listaVacinacao);
    atrr.addFlashAttribute("success", "Lista actualizada com sucesso.");
    return "redirect:/lista-vacinacoes/listar";
  }

  /**
   * Metodo que permite excluir um listas de vacinacao com base no seu id e
   * somente se ele nao tiver cargos vinculados a ele
   * 
   * @param id
   * @param model
   * @return
   */
  @GetMapping("apagar/{id}")
  public String apagar(@PathVariable("id") Long id, ModelMap model) {

    listaVacinacaoService.apagar(id);
    model.addAttribute("success", "Lista removida com sucesso.");

    return listar(model);
  }

  /**
   * metodo para fazer o registo de listas de vacinacao com recurso ao formulario
   * de cadastro no redir
   * 
   * @param listaVacinacao
   * @param atrr
   * @return
   */
  @PostMapping("gravar")
  public String gravar(ListaVacinacao listaVacinacao, RedirectAttributes atrr) {
    listaVacinacaoService.registar(listaVacinacao);
    atrr.addFlashAttribute("success", "Lista cadastrada com sucesso.");
    return "redirect:/vacinacoes/cadastrar";
  }

  /**
   * Metodo para listar todos os instituicoes e mostrar na combobox presente no
   * formulario
   * 
   * @return
   */
  @ModelAttribute("instituicoess")
  public List<InstituicaoSala> listaInstituicoes() {
    // return instituicaoService.pesquisarTodos();
    return instituicaoSalaService.pesquisarTodos();

  }

  @ModelAttribute("instituicoes")
  public List<Instituicao> listarInstituicoes() {
    return instituicaoService.pesquisarTodos();
  }

  /**
   * Metodo para listar todas as salas e mostrar na combobox presente no
   * formulario
   * 
   * @return
   */
  @ModelAttribute("salas")
  public List<Sala> listaSalas() {
    return salaService.buscarTodos();
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

  // SECCAO DE MARCACAO DE SALAS

  @GetMapping("requisitar")
  public String requisitar(InstituicaoSala instituicaoSala) {

    return "/admin/pages/lista-vacinacoes/add-requisitar-sala";
  }

  /**
   * metodo para fazer o registo de Requisicao de salas com recurso ao formulario
   * de cadastro no redir
   * 
   * @param instituicaoSala
   * @param atrr
   * @return
   */
  @PostMapping("requisicoes")
  public String requisitar(InstituicaoSala instituicaoSala, RedirectAttributes atrr) {
    instituicaoSalaService.registar(instituicaoSala);
    atrr.addFlashAttribute("success", "Requisição feita com sucesso.");
    return "redirect:/vacinacoes/requisitar";
  }

  // SECCAO DE CRIACAO DE LISTAS DE VACINACAO

  /// instituicoes/editar/{id} (id=${i.id}

  /**
   * Metodo que permite pesquisar um listas de vacinacao com base no seu id e
   * somente se ele nao tiver cargos vinculados a ele
   * 
   * @param id
   * @param model
   * @return
   */
  @GetMapping("pesquisar/{id}")
  public String pesquisarInstituicao(@PathVariable("id") Long id, ModelMap model) {
    model.addAttribute("salaInstituicoes", instituicaoSalaService.pesquisarPorId(id));
    return "redirect:/vacinacoes/cadastrar";
  }

  @RequestMapping("envioInstituicao")
  public String envioInstituicao(@ModelAttribute("instituicaoId") Long instituicaoId) {
    System.out.println(instituicaoId);

    List<Funcionario> funcionarios = funcionarioRepository.listaFuncionarios(instituicaoId);

    System.out.println(funcionarios);

    return "redirect:/vacinacoes/requisitar";
  }
}
