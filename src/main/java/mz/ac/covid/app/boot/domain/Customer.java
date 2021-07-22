package mz.ac.covid.app.boot.domain;

// import java.sql.Time;
// import java.util.Date;

// import org.springframework.format.annotation.DateTimeFormat;
// import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer extends AbstractEntity<Long> {

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "empresa")
    private String empresa;

    @Column(name = "email")
    private String email;

    // @DateTimeFormat(iso = ISO.DATE)
    // @Column(name = "data_vacinacao", nullable = false, columnDefinition = "DATE")
    // private String dataVacinacao;

    @Column(name = "data_vacinacao")
    private String dataVacinacao;

    @Column(name = "sala_vacinacao")
    private String salaVacinacao;

    @Column(name = "hora_vacinacao")
    private String horaVacinacao;

    @Column(name = "telefone_gestor")
    private String telefoneGestor;


    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean notificar;

    @Column(columnDefinition="BOOLEAN DEFAULT false")
    private boolean estadoVacinacao;

    public Customer() {
    }

    public Customer(String nome, String telefone, String empresa, String email, String dataVacinacao,
            String salaVacinacao, String horaVacinacao, String telefoneGestor) {
        this.nome = nome;
        this.telefone = telefone;
        this.empresa = empresa;
        this.email = email;
        this.dataVacinacao = dataVacinacao;
        this.salaVacinacao = salaVacinacao;
        this.horaVacinacao = horaVacinacao;
        this.telefoneGestor = telefoneGestor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataVacinacao() {
        return dataVacinacao;
    }

    public void setDataVacinacao(String dataVacinacao) {
        this.dataVacinacao = dataVacinacao;
    }

    public String getHoraVacinacao() {
        return horaVacinacao;
    }

    public void setHoraVacinacao(String horaVacinacao) {
        this.horaVacinacao = horaVacinacao;
    }

    public String getSalaVacinacao() {
        return salaVacinacao;
    }

    public void setSalaVacinacao(String salaVacinacao) {
        this.salaVacinacao = salaVacinacao;
    }

    public String getTelefoneGestor() {
        return telefoneGestor;
    }

    public void setTelefoneGestor(String telefoneGestor) {
        this.telefoneGestor = telefoneGestor;
    }

    public boolean getNotificar() {
        return notificar;
    }

    public void setNotificar(boolean notificar) {
        this.notificar = notificar;
    }

    public boolean getEstadoVacinacao() {
        return estadoVacinacao;
    }

    public void setEstadoVacinacao(boolean estadoVacinacao) {
        this.estadoVacinacao = estadoVacinacao;
    }

    @Override
    public String toString() {
        return "Customer [dataVacinacao=" + dataVacinacao + ", email=" + email + ", empresa=" + empresa
                + ", estadoVacinacao=" + estadoVacinacao + ", horaVacinacao=" + horaVacinacao + ", nome=" + nome
                + ", notificar=" + notificar + ", salaVacinacao=" + salaVacinacao + ", telefone=" + telefone
                + ", telefoneGestor=" + telefoneGestor + "]";
    }

}
