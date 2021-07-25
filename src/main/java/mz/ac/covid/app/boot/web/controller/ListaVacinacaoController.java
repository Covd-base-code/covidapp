package mz.ac.covid.app.boot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sendgrid.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import mz.ac.covid.app.boot.domain.Customer;
import mz.ac.covid.app.boot.domain.Funcionario;
import mz.ac.covid.app.boot.domain.Instituicao;
import mz.ac.covid.app.boot.domain.InstituicaoSala;
import mz.ac.covid.app.boot.domain.ListaVacinacao;
import mz.ac.covid.app.boot.domain.Sala;
import mz.ac.covid.app.boot.repository.CustomerRepository;
import mz.ac.covid.app.boot.repository.IFuncionarioRepository;
import mz.ac.covid.app.boot.service.CustomerService;
import mz.ac.covid.app.boot.service.FuncionarioService;
import mz.ac.covid.app.boot.service.InstituicaoSalaService;
import mz.ac.covid.app.boot.service.InstituicaoService;
import mz.ac.covid.app.boot.service.ListaVacinacaoService;
import mz.ac.covid.app.boot.service.MailService;
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

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerService customerService;

  @Autowired
  MailService mailService;

  public List<Customer> filterdCustomers = new ArrayList<Customer>();

  @GetMapping("cadastrar")
  public String cadastrar(ListaVacinacao listaVacinacao) {

    return "/admin/pages/lista-vacinacoes/add-lista";
  }

  @GetMapping("submeter")
  public String submeter() {
    return "/admin/pages/lista-vacinacoes/add-excel";
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
   * Metodo para listar todas as isntituiceos e mostrar na combobox presente no
   * formulario dos dados carregados por excel
   * 
   * @return
   */
  @ModelAttribute("customers")
  public List<Customer> getAllInstitutions() {
    return customerRepository.getAllInstitutions();
  }

  /**
   * Metodo para listar todas as isntituiceos e mostrar na combobox presente no
   * formulario dos dados carregados por excel
   * 
   * @return
   */

  @RequestMapping(value = "/getSalaByEmpresa", method = RequestMethod.GET)
  public @ResponseBody List<Customer> getAllSalas(@RequestParam("empresaName") String empresaName) {

    List<Customer> salasByEmpresa = new ArrayList<Customer>();

    salasByEmpresa = customerRepository.getAllSalas(empresaName);

    if (salasByEmpresa.size() != 0)
      return salasByEmpresa;
    return null;
  }

  @RequestMapping(value = "/getAllCustomers/{empresaName}/{SalaName}", method = RequestMethod.GET)
  public @ResponseBody List<Customer> getAllCustomers(@PathVariable("empresaName") String empresaName,
      @PathVariable("SalaName") String SalaName) {

    List<Customer> Customers = new ArrayList<Customer>();

    Customers = customerRepository.getAllCustomers(empresaName, SalaName);

    if (Customers.size() != 0)
      return Customers;
    return null;
  }

  @ModelAttribute("salaNomes")
  public List<Customer> _getAllSala() {
    List<Customer> salasByEmpresa = new ArrayList<Customer>();
    return salasByEmpresa;
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
    model.addAttribute("funcionariosDaInstituicao", funcionarioRepository.listaInstituicaoSalas(id));
    return "redirect:/vacinacoes/cadastrar";

  }

  // @RequestMapping("pesquisar")
  // public String envioInstituicao(ListaVacinacao listaVacinacao, ModelMap model)
  // {
  // listaVacinacaoService.registar(listaVacinacao);
  // model.addAttribute("success", "Lista Cadastrada com sucesso.");
  // return "redirect:/vacinacoes/requisitar";
  // }

  /** LISTA DE VACINADOS - EXTRAIDA E PERSISTIDA POR FICHEIROS CSV */

  @GetMapping("listar-vacinados")
  public String vacinados(ModelMap model) {
    model.addAttribute("listavacinados", customerService.buscarTodos());
    return "/admin/pages/lista-vacinacoes/list-vacinados";
  }

  @GetMapping("estado/{id}")
  public String estados(@PathVariable("id") Long id, ModelMap model, RedirectAttributes atrr) {

    Customer customer = customerRepository.getCustomerById(id);

    customer.setEstadoVacinacao(true);

    // customerRepository.save(customer);
    customerService.editar(customer);
    model.addAttribute("listavacinados", customerService.buscarTodos());
    atrr.addFlashAttribute("success", "Estado alterado com sucesso!.");
    return "/admin/pages/lista-vacinacoes/list-vacinados";
  }

  @GetMapping("/send/{id}")
  public String send(ModelMap model, @PathVariable("id") Long id, RedirectAttributes atrr) throws IOException {

    Customer customer = customerRepository.getCustomerById(id);

    Email to = new Email(customer.getEmail());

    mailService.sendTextEmail(to);

    this.vacinados(model);

    atrr.addFlashAttribute("success", "Notificacao enviada com sucesso!.");
    return "redirect:/lista-vacinacoes/list-vacinados";
  }

  /** FASE DE TESTE DE NOTIFICACAO EM MASSA */

  @GetMapping("notificar")
  public String notificar(Customer customer) {

    // instituicaoSalaService.registar(instituicaoSala);
    // atrr.addFlashAttribute("success", "Requisição feita com sucesso.");
    return "/admin/pages/lista-vacinacoes/notificar-massa";
  }

  @PostMapping("filtrar")
  public String tentar(Customer customer, ModelMap model) {
    boolean estado = customer.getEstadoVacinacao();
    String instituicao = customer.getEmpresa();
    String sala = customer.getSalaVacinacao();

    if (estado == false) {

      filterdCustomers = customerRepository.search(0, instituicao, sala);

      model.addAttribute("notificados", filterdCustomers);
      return "/admin/pages/lista-vacinacoes/notificar-massa";
    } else {

      filterdCustomers = customerRepository.search(0, instituicao, sala);

      model.addAttribute("notificados", filterdCustomers);
      return "/admin/pages/lista-vacinacoes/notificar-massa";
    }
  }

  @GetMapping("/sendAllEmails")
  public String sendForAllEmails(RedirectAttributes atrr) throws IOException {

    filterdCustomers.stream().forEach(customer -> {

      // Email to = new Email(customer.getEmail());
      Email to = new Email("daltonharvey.manusse@icloud.com");

      try {

        mailService.sendTextEmail(to);

      } catch (IOException e) {

        e.printStackTrace();
      }

    });

    atrr.addFlashAttribute("success", "Notificação enviada com sucesso!.");
    return "redirect:/vacinacoes/notificar";
  }

  // @RequestMapping("tentar")
  // public String viewHomePage(Customer customer, Model model, @Param("keyword")
  // Integer keyword) {
  // List<Customer> listVacinados = customerRepository.search(keyword);

  // model.addAttribute("notificados", customerRepository.search(keyword));
  // // model.addAttribute("keyword", keyword);

  // return "/admin/pages/lista-vacinacoes/notificar-massa";
  // }

}
