/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import entitys.Loja;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitys.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author aderito
 */
public class LojaJpaController implements Serializable {

    public LojaJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaGestaoStockPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Loja loja) {
        if (loja.getProdutoList() == null) {
            loja.setProdutoList(new ArrayList<Produto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Produto> attachedProdutoList = new ArrayList<Produto>();
            for (Produto produtoListProdutoToAttach : loja.getProdutoList()) {
                produtoListProdutoToAttach = em.getReference(produtoListProdutoToAttach.getClass(), produtoListProdutoToAttach.getIdproduto());
                attachedProdutoList.add(produtoListProdutoToAttach);
            }
            loja.setProdutoList(attachedProdutoList);
            em.persist(loja);
            for (Produto produtoListProduto : loja.getProdutoList()) {
                Loja oldIdlojaOfProdutoListProduto = produtoListProduto.getIdloja();
                produtoListProduto.setIdloja(loja);
                produtoListProduto = em.merge(produtoListProduto);
                if (oldIdlojaOfProdutoListProduto != null) {
                    oldIdlojaOfProdutoListProduto.getProdutoList().remove(produtoListProduto);
                    oldIdlojaOfProdutoListProduto = em.merge(oldIdlojaOfProdutoListProduto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Loja loja) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Loja persistentLoja = em.find(Loja.class, loja.getIdloja());
            List<Produto> produtoListOld = persistentLoja.getProdutoList();
            List<Produto> produtoListNew = loja.getProdutoList();
            List<Produto> attachedProdutoListNew = new ArrayList<Produto>();
            for (Produto produtoListNewProdutoToAttach : produtoListNew) {
                produtoListNewProdutoToAttach = em.getReference(produtoListNewProdutoToAttach.getClass(), produtoListNewProdutoToAttach.getIdproduto());
                attachedProdutoListNew.add(produtoListNewProdutoToAttach);
            }
            produtoListNew = attachedProdutoListNew;
            loja.setProdutoList(produtoListNew);
            loja = em.merge(loja);
            for (Produto produtoListOldProduto : produtoListOld) {
                if (!produtoListNew.contains(produtoListOldProduto)) {
                    produtoListOldProduto.setIdloja(null);
                    produtoListOldProduto = em.merge(produtoListOldProduto);
                }
            }
            for (Produto produtoListNewProduto : produtoListNew) {
                if (!produtoListOld.contains(produtoListNewProduto)) {
                    Loja oldIdlojaOfProdutoListNewProduto = produtoListNewProduto.getIdloja();
                    produtoListNewProduto.setIdloja(loja);
                    produtoListNewProduto = em.merge(produtoListNewProduto);
                    if (oldIdlojaOfProdutoListNewProduto != null && !oldIdlojaOfProdutoListNewProduto.equals(loja)) {
                        oldIdlojaOfProdutoListNewProduto.getProdutoList().remove(produtoListNewProduto);
                        oldIdlojaOfProdutoListNewProduto = em.merge(oldIdlojaOfProdutoListNewProduto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = loja.getIdloja();
                if (findLoja(id) == null) {
                    throw new NonexistentEntityException("The loja with id " + id + " no longer exists.");
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
            Loja loja;
            try {
                loja = em.getReference(Loja.class, id);
                loja.getIdloja();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The loja with id " + id + " no longer exists.", enfe);
            }
            List<Produto> produtoList = loja.getProdutoList();
            for (Produto produtoListProduto : produtoList) {
                produtoListProduto.setIdloja(null);
                produtoListProduto = em.merge(produtoListProduto);
            }
            em.remove(loja);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Loja> findLojaEntities() {
        return findLojaEntities(true, -1, -1);
    }

    public List<Loja> findLojaEntities(int maxResults, int firstResult) {
        return findLojaEntities(false, maxResults, firstResult);
    }

    private List<Loja> findLojaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Loja.class));
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

    public Loja findLoja(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Loja.class, id);
        } finally {
            em.close();
        }
    }

    public int getLojaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Loja> rt = cq.from(Loja.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
