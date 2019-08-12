/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author aderito
 */
@Entity
@Table(name = "loja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loja.findAll", query = "SELECT l FROM Loja l")
    , @NamedQuery(name = "Loja.findByIdloja", query = "SELECT l FROM Loja l WHERE l.idloja = :idloja")
    , @NamedQuery(name = "Loja.findByNome", query = "SELECT l FROM Loja l WHERE l.nome = :nome")
    , @NamedQuery(name = "Loja.findByLocalizacao", query = "SELECT l FROM Loja l WHERE l.localizacao = :localizacao")
    , @NamedQuery(name = "Loja.findByDescricao", query = "SELECT l FROM Loja l WHERE l.descricao = :descricao")
    , @NamedQuery(name = "Loja.findByContacto", query = "SELECT l FROM Loja l WHERE l.contacto = :contacto")})
public class Loja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idloja")
    private Integer idloja;
    @Column(name = "nome")
    private String nome;
    @Column(name = "localizacao")
    private String localizacao;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "contacto")
    private String contacto;
    @OneToMany(mappedBy = "idloja")
    private List<Produto> produtoList;

    public Loja() {
    }

    public Loja(Integer idloja) {
        this.idloja = idloja;
    }

    public Integer getIdloja() {
        return idloja;
    }

    public void setIdloja(Integer idloja) {
        this.idloja = idloja;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    @XmlTransient
    public List<Produto> getProdutoList() {
        return produtoList;
    }

    public void setProdutoList(List<Produto> produtoList) {
        this.produtoList = produtoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idloja != null ? idloja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loja)) {
            return false;
        }
        Loja other = (Loja) object;
        if ((this.idloja == null && other.idloja != null) || (this.idloja != null && !this.idloja.equals(other.idloja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
