/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Aquisicao;
import model.Fornecedor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author aderito
 */
public class FornecedorJpaController implements Serializable {

    public FornecedorJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaGestaoStockPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fornecedor fornecedor) {
        if (fornecedor.getAquisicaoList() == null) {
            fornecedor.setAquisicaoList(new ArrayList<Aquisicao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Aquisicao> attachedAquisicaoList = new ArrayList<Aquisicao>();
            for (Aquisicao aquisicaoListAquisicaoToAttach : fornecedor.getAquisicaoList()) {
                aquisicaoListAquisicaoToAttach = em.getReference(aquisicaoListAquisicaoToAttach.getClass(), aquisicaoListAquisicaoToAttach.getIdaquisicao());
                attachedAquisicaoList.add(aquisicaoListAquisicaoToAttach);
            }
            fornecedor.setAquisicaoList(attachedAquisicaoList);
            em.persist(fornecedor);
            for (Aquisicao aquisicaoListAquisicao : fornecedor.getAquisicaoList()) {
                Fornecedor oldIdfornecedorOfAquisicaoListAquisicao = aquisicaoListAquisicao.getIdfornecedor();
                aquisicaoListAquisicao.setIdfornecedor(fornecedor);
                aquisicaoListAquisicao = em.merge(aquisicaoListAquisicao);
                if (oldIdfornecedorOfAquisicaoListAquisicao != null) {
                    oldIdfornecedorOfAquisicaoListAquisicao.getAquisicaoList().remove(aquisicaoListAquisicao);
                    oldIdfornecedorOfAquisicaoListAquisicao = em.merge(oldIdfornecedorOfAquisicaoListAquisicao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fornecedor fornecedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fornecedor persistentFornecedor = em.find(Fornecedor.class, fornecedor.getIdfornecedor());
            List<Aquisicao> aquisicaoListOld = persistentFornecedor.getAquisicaoList();
            List<Aquisicao> aquisicaoListNew = fornecedor.getAquisicaoList();
            List<Aquisicao> attachedAquisicaoListNew = new ArrayList<Aquisicao>();
            for (Aquisicao aquisicaoListNewAquisicaoToAttach : aquisicaoListNew) {
                aquisicaoListNewAquisicaoToAttach = em.getReference(aquisicaoListNewAquisicaoToAttach.getClass(), aquisicaoListNewAquisicaoToAttach.getIdaquisicao());
                attachedAquisicaoListNew.add(aquisicaoListNewAquisicaoToAttach);
            }
            aquisicaoListNew = attachedAquisicaoListNew;
            fornecedor.setAquisicaoList(aquisicaoListNew);
            fornecedor = em.merge(fornecedor);
            for (Aquisicao aquisicaoListOldAquisicao : aquisicaoListOld) {
                if (!aquisicaoListNew.contains(aquisicaoListOldAquisicao)) {
                    aquisicaoListOldAquisicao.setIdfornecedor(null);
                    aquisicaoListOldAquisicao = em.merge(aquisicaoListOldAquisicao);
                }
            }
            for (Aquisicao aquisicaoListNewAquisicao : aquisicaoListNew) {
                if (!aquisicaoListOld.contains(aquisicaoListNewAquisicao)) {
                    Fornecedor oldIdfornecedorOfAquisicaoListNewAquisicao = aquisicaoListNewAquisicao.getIdfornecedor();
                    aquisicaoListNewAquisicao.setIdfornecedor(fornecedor);
                    aquisicaoListNewAquisicao = em.merge(aquisicaoListNewAquisicao);
                    if (oldIdfornecedorOfAquisicaoListNewAquisicao != null && !oldIdfornecedorOfAquisicaoListNewAquisicao.equals(fornecedor)) {
                        oldIdfornecedorOfAquisicaoListNewAquisicao.getAquisicaoList().remove(aquisicaoListNewAquisicao);
                        oldIdfornecedorOfAquisicaoListNewAquisicao = em.merge(oldIdfornecedorOfAquisicaoListNewAquisicao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fornecedor.getIdfornecedor();
                if (findFornecedor(id) == null) {
                    throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.");
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
            Fornecedor fornecedor;
            try {
                fornecedor = em.getReference(Fornecedor.class, id);
                fornecedor.getIdfornecedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fornecedor with id " + id + " no longer exists.", enfe);
            }
            List<Aquisicao> aquisicaoList = fornecedor.getAquisicaoList();
            for (Aquisicao aquisicaoListAquisicao : aquisicaoList) {
                aquisicaoListAquisicao.setIdfornecedor(null);
                aquisicaoListAquisicao = em.merge(aquisicaoListAquisicao);
            }
            em.remove(fornecedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fornecedor> findFornecedorEntities() {
        return findFornecedorEntities(true, -1, -1);
    }

    public List<Fornecedor> findFornecedorEntities(int maxResults, int firstResult) {
        return findFornecedorEntities(false, maxResults, firstResult);
    }

    private List<Fornecedor> findFornecedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fornecedor.class));
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

    public Fornecedor findFornecedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fornecedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getFornecedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fornecedor> rt = cq.from(Fornecedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
