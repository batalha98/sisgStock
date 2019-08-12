/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    , @NamedQuery(name = "Aquisicao.findByIdproduto", query = "SELECT a FROM Aquisicao a WHERE a.idproduto = :idproduto")
    , @NamedQuery(name = "Aquisicao.findByQtdAdquirida", query = "SELECT a FROM Aquisicao a WHERE a.qtdAdquirida = :qtdAdquirida")
    , @NamedQuery(name = "Aquisicao.findByDataAquisicao", query = "SELECT a FROM Aquisicao a WHERE a.dataAquisicao = :dataAquisicao")})
public class Aquisicao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idproduto")
    private Integer idproduto;
    @Column(name = "qtdAdquirida")
    private Integer qtdAdquirida;
    @Column(name = "dataAquisicao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAquisicao;
    @JoinColumn(name = "idfornecedor", referencedColumnName = "idfornecedor")
    @ManyToOne
    private Fornecedor idfornecedor;

    public Aquisicao() {
    }

    public Aquisicao(Integer idproduto) {
        this.idproduto = idproduto;
    }

    public Integer getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Integer idproduto) {
        this.idproduto = idproduto;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproduto != null ? idproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aquisicao)) {
            return false;
        }
        Aquisicao other = (Aquisicao) object;
        if ((this.idproduto == null && other.idproduto != null) || (this.idproduto != null && !this.idproduto.equals(other.idproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entitys.Aquisicao[ idproduto=" + idproduto + " ]";
    }
    
}
