/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entitys.Aquisicao;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entitys.Fornecedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author aderito
 */
public class AquisicaoJpaController implements Serializable {

    public AquisicaoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaGestaoStockPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aquisicao aquisicao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor idfornecedor = aquisicao.getIdfornecedor();
            if (idfornecedor != null) {
                idfornecedor = em.getReference(idfornecedor.getClass(), idfornecedor.getIdfornecedor());
                aquisicao.setIdfornecedor(idfornecedor);
            }
            em.persist(aquisicao);
            if (idfornecedor != null) {
                idfornecedor.getAquisicaoList().add(aquisicao);
                idfornecedor = em.merge(idfornecedor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAquisicao(aquisicao.getIdproduto()) != null) {
                throw new PreexistingEntityException("Aquisicao " + aquisicao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aquisicao aquisicao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aquisicao persistentAquisicao = em.find(Aquisicao.class, aquisicao.getIdproduto());
            Fornecedor idfornecedorOld = persistentAquisicao.getIdfornecedor();
            Fornecedor idfornecedorNew = aquisicao.getIdfornecedor();
            if (idfornecedorNew != null) {
                idfornecedorNew = em.getReference(idfornecedorNew.getClass(), idfornecedorNew.getIdfornecedor());
                aquisicao.setIdfornecedor(idfornecedorNew);
            }
            aquisicao = em.merge(aquisicao);
            if (idfornecedorOld != null && !idfornecedorOld.equals(idfornecedorNew)) {
                idfornecedorOld.getAquisicaoList().remove(aquisicao);
                idfornecedorOld = em.merge(idfornecedorOld);
            }
            if (idfornecedorNew != null && !idfornecedorNew.equals(idfornecedorOld)) {
                idfornecedorNew.getAquisicaoList().add(aquisicao);
                idfornecedorNew = em.merge(idfornecedorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = aquisicao.getIdproduto();
                if (findAquisicao(id) == null) {
                    throw new NonexistentEntityException("The aquisicao with id " + id + " no longer exists.");
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
            Aquisicao aquisicao;
            try {
                aquisicao = em.getReference(Aquisicao.class, id);
                aquisicao.getIdproduto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aquisicao with id " + id + " no longer exists.", enfe);
            }
            Fornecedor idfornecedor = aquisicao.getIdfornecedor();
            if (idfornecedor != null) {
                idfornecedor.getAquisicaoList().remove(aquisicao);
                idfornecedor = em.merge(idfornecedor);
            }
            em.remove(aquisicao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aquisicao> findAquisicaoEntities() {
        return findAquisicaoEntities(true, -1, -1);
    }

    public List<Aquisicao> findAquisicaoEntities(int maxResults, int firstResult) {
        return findAquisicaoEntities(false, maxResults, firstResult);
    }

    private List<Aquisicao> findAquisicaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aquisicao.class));
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

    public Aquisicao findAquisicao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aquisicao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAquisicaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aquisicao> rt = cq.from(Aquisicao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
