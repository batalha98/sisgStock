/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Produto;
import model.Venda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Loja;

/**
 *
 * @author aderito
 */
public class VendaJpaController implements Serializable {
    private Venda venda;
    
    public VendaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaGestaoStockPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Venda venda) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto idproduto = venda.getIdproduto();
            if (idproduto != null) {
                idproduto = em.getReference(idproduto.getClass(), idproduto.getIdproduto());
                venda.setIdproduto(idproduto);
            }
            em.persist(venda);
            if (idproduto != null) {
                idproduto.getVendaList().add(venda);
                idproduto = em.merge(idproduto);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venda venda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda persistentVenda = em.find(Venda.class, venda.getIdvenda());
            Produto idprodutoOld = persistentVenda.getIdproduto();
            Produto idprodutoNew = venda.getIdproduto();
            if (idprodutoNew != null) {
                idprodutoNew = em.getReference(idprodutoNew.getClass(), idprodutoNew.getIdproduto());
                venda.setIdproduto(idprodutoNew);
            }
            venda = em.merge(venda);
            if (idprodutoOld != null && !idprodutoOld.equals(idprodutoNew)) {
                idprodutoOld.getVendaList().remove(venda);
                idprodutoOld = em.merge(idprodutoOld);
            }
            if (idprodutoNew != null && !idprodutoNew.equals(idprodutoOld)) {
                idprodutoNew.getVendaList().add(venda);
                idprodutoNew = em.merge(idprodutoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venda.getIdvenda();
                if (findVenda(id) == null) {
                    throw new NonexistentEntityException("The venda with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getIdvenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venda with id " + id + " no longer exists.", enfe);
            }
            Produto idproduto = venda.getIdproduto();
            if (idproduto != null) {
                idproduto.getVendaList().remove(venda);
                idproduto = em.merge(idproduto);
            }
            em.remove(venda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Venda findVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venda> rt = cq.from(Venda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Venda> buscarVendasPorNomeProd(String nomeProduto){ 
        List<Venda> vendas = new ArrayList<>();
        
        for(Venda v: findVendaEntities()){
            
            if(v.getIdproduto().getNome().contains(nomeProduto)){
                vendas.add(v);
            }
           
        }
        
        return vendas;
    }
    
     public List<Venda> buscarVendasDaLoja(int idloja){
        List<Venda> vendas = new ArrayList<>();
         
        for(Venda v: findVendaEntities()){

            if(v.getIdproduto().getIdloja().getIdloja().equals(idloja)){
                vendas.add(v);
            }
           
        }
        
        return vendas;
    }
     
    public List<Venda> buscarVendasPorData(Date from, Date to, List<Venda> list){
        List<Venda> vendas = new ArrayList<>();
        
        if(from != null && to!=null){
            list.forEach((v) -> {
                if((v.getDataVenda().after(from)) 
                        && (v.getDataVenda().before(to))){

                    vendas.add(v);
                    
                }else if((v.getDataVenda().equals(from)) 
                        && (v.getDataVenda().before(to))){
                    vendas.add(v);
                }else if((v.getDataVenda().equals(from)) 
                        && (v.getDataVenda().equals(to))){
                    vendas.add(v);
                }else if((v.getDataVenda().after(from)) 
                        && (v.getDataVenda().equals(to))){
                    vendas.add(v);
                }
            });
            return vendas;
        }
        return null;
    }
}
