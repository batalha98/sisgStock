/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author aderito
 */
@Entity
@Table(name = "aquisicao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aquisicao.findAll", query = "SELECT a FROM Aquisicao a")
    , @NamedQuery(name = "Aquisicao.findByIdaquisicao", query = "SELECT a FROM Aquisicao a WHERE a.idaquisicao = :idaquisicao")
    , @NamedQuery(name = "Aquisicao.findByQtdAdquirida", query = "SELECT a FROM Aquisicao a WHERE a.qtdAdquirida = :qtdAdquirida")
    , @NamedQuery(name = "Aquisicao.findByDataAquisicao", query = "SELECT a FROM Aquisicao a WHERE a.dataAquisicao = :dataAquisicao")})
public class Aquisicao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idaquisicao")
    private Integer idaquisicao;
    @Column(name = "qtdAdquirida")
    private Integer qtdAdquirida;
    @Column(name = "dataAquisicao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAquisicao;
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne
    private Fornecedor idfornecedor;
    @JoinColumn(name = "idproduto", referencedColumnName = "idproduto")
    @ManyToOne
    private Produto idproduto;

    public Aquisicao() {
    }

    public Aquisicao(Integer idaquisicao) {
        this.idaquisicao = idaquisicao;
    }

    public Integer getIdaquisicao() {
        return idaquisicao;
    }

    public void setIdaquisicao(Integer idaquisicao) {
        this.idaquisicao = idaquisicao;
    }

    public Integer getQtdAdquirida() {
        return qtdAdquirida;
    }

    public void setQtdAdquirida(Integer qtdAdquirida) {
        this.qtdAdquirida = qtdAdquirida;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Fornecedor getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Fornecedor idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public Produto getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Produto idproduto) {
        this.idproduto = idproduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idaquisicao != null ? idaquisicao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aquisicao)) {
            return false;
        }
        Aquisicao other = (Aquisicao) object;
        if ((this.idaquisicao == null && other.idaquisicao != null) || (this.idaquisicao != null && !this.idaquisicao.equals(other.idaquisicao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Aquisicao[ idaquisicao=" + idaquisicao + " ]";
    }
    
}
