/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.Loja;
import model.Venda;
import java.util.ArrayList;
import java.util.List;
import model.Aquisicao;
import model.Produto;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author aderito
 */
public class ProdutoJpaController implements Serializable {
    
    public ProdutoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("SistemaGestaoStockPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Produto produto) {
        if (produto.getVendaList() == null) {
            produto.setVendaList(new ArrayList<Venda>());
        }
        if (produto.getAquisicaoList() == null) {
            produto.setAquisicaoList(new ArrayList<Aquisicao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Loja idloja = produto.getIdloja();
            if (idloja != null) {
                idloja = em.getReference(idloja.getClass(), idloja.getIdloja());
                produto.setIdloja(idloja);
            }
            List<Venda> attachedVendaList = new ArrayList<Venda>();
            for (Venda vendaListVendaToAttach : produto.getVendaList()) {
                vendaListVendaToAttach = em.getReference(vendaListVendaToAttach.getClass(), vendaListVendaToAttach.getIdvenda());
                attachedVendaList.add(vendaListVendaToAttach);
            }
            produto.setVendaList(attachedVendaList);
            List<Aquisicao> attachedAquisicaoList = new ArrayList<Aquisicao>();
            for (Aquisicao aquisicaoListAquisicaoToAttach : produto.getAquisicaoList()) {
                aquisicaoListAquisicaoToAttach = em.getReference(aquisicaoListAquisicaoToAttach.getClass(), aquisicaoListAquisicaoToAttach.getIdaquisicao());
                attachedAquisicaoList.add(aquisicaoListAquisicaoToAttach);
            }
            produto.setAquisicaoList(attachedAquisicaoList);
            em.persist(produto);
            if (idloja != null) {
                idloja.getProdutoList().add(produto);
                idloja = em.merge(idloja);
            }
            for (Venda vendaListVenda : produto.getVendaList()) {
                Produto oldIdprodutoOfVendaListVenda = vendaListVenda.getIdproduto();
                vendaListVenda.setIdproduto(produto);
                vendaListVenda = em.merge(vendaListVenda);
                if (oldIdprodutoOfVendaListVenda != null) {
                    oldIdprodutoOfVendaListVenda.getVendaList().remove(vendaListVenda);
                    oldIdprodutoOfVendaListVenda = em.merge(oldIdprodutoOfVendaListVenda);
                }
            }
            for (Aquisicao aquisicaoListAquisicao : produto.getAquisicaoList()) {
                Produto oldIdprodutoOfAquisicaoListAquisicao = aquisicaoListAquisicao.getIdproduto();
                aquisicaoListAquisicao.setIdproduto(produto);
                aquisicaoListAquisicao = em.merge(aquisicaoListAquisicao);
                if (oldIdprodutoOfAquisicaoListAquisicao != null) {
                    oldIdprodutoOfAquisicaoListAquisicao.getAquisicaoList().remove(aquisicaoListAquisicao);
                    oldIdprodutoOfAquisicaoListAquisicao = em.merge(oldIdprodutoOfAquisicaoListAquisicao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Produto produto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto persistentProduto = em.find(Produto.class, produto.getIdproduto());
            Loja idlojaOld = persistentProduto.getIdloja();
            Loja idlojaNew = produto.getIdloja();
            List<Venda> vendaListOld = persistentProduto.getVendaList();
            List<Venda> vendaListNew = produto.getVendaList();
            List<Aquisicao> aquisicaoListOld = persistentProduto.getAquisicaoList();
            List<Aquisicao> aquisicaoListNew = produto.getAquisicaoList();
            List<String> illegalOrphanMessages = null;
            for (Venda vendaListOldVenda : vendaListOld) {
                if (!vendaListNew.contains(vendaListOldVenda)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Venda " + vendaListOldVenda + " since its idproduto field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idlojaNew != null) {
                idlojaNew = em.getReference(idlojaNew.getClass(), idlojaNew.getIdloja());
                produto.setIdloja(idlojaNew);
            }
            List<Venda> attachedVendaListNew = new ArrayList<Venda>();
            for (Venda vendaListNewVendaToAttach : vendaListNew) {
                vendaListNewVendaToAttach = em.getReference(vendaListNewVendaToAttach.getClass(), vendaListNewVendaToAttach.getIdvenda());
                attachedVendaListNew.add(vendaListNewVendaToAttach);
            }
            vendaListNew = attachedVendaListNew;
            produto.setVendaList(vendaListNew);
            List<Aquisicao> attachedAquisicaoListNew = new ArrayList<Aquisicao>();
            for (Aquisicao aquisicaoListNewAquisicaoToAttach : aquisicaoListNew) {
                aquisicaoListNewAquisicaoToAttach = em.getReference(aquisicaoListNewAquisicaoToAttach.getClass(), aquisicaoListNewAquisicaoToAttach.getIdaquisicao());
                attachedAquisicaoListNew.add(aquisicaoListNewAquisicaoToAttach);
            }
            aquisicaoListNew = attachedAquisicaoListNew;
            produto.setAquisicaoList(aquisicaoListNew);
            produto = em.merge(produto);
            if (idlojaOld != null && !idlojaOld.equals(idlojaNew)) {
                idlojaOld.getProdutoList().remove(produto);
                idlojaOld = em.merge(idlojaOld);
            }
            if (idlojaNew != null && !idlojaNew.equals(idlojaOld)) {
                idlojaNew.getProdutoList().add(produto);
                idlojaNew = em.merge(idlojaNew);
            }
            for (Venda vendaListNewVenda : vendaListNew) {
                if (!vendaListOld.contains(vendaListNewVenda)) {
                    Produto oldIdprodutoOfVendaListNewVenda = vendaListNewVenda.getIdproduto();
                    vendaListNewVenda.setIdproduto(produto);
                    vendaListNewVenda = em.merge(vendaListNewVenda);
                    if (oldIdprodutoOfVendaListNewVenda != null && !oldIdprodutoOfVendaListNewVenda.equals(produto)) {
                        oldIdprodutoOfVendaListNewVenda.getVendaList().remove(vendaListNewVenda);
                        oldIdprodutoOfVendaListNewVenda = em.merge(oldIdprodutoOfVendaListNewVenda);
                    }
                }
            }
            for (Aquisicao aquisicaoListOldAquisicao : aquisicaoListOld) {
                if (!aquisicaoListNew.contains(aquisicaoListOldAquisicao)) {
                    aquisicaoListOldAquisicao.setIdproduto(null);
                    aquisicaoListOldAquisicao = em.merge(aquisicaoListOldAquisicao);
                }
            }
            for (Aquisicao aquisicaoListNewAquisicao : aquisicaoListNew) {
                if (!aquisicaoListOld.contains(aquisicaoListNewAquisicao)) {
                    Produto oldIdprodutoOfAquisicaoListNewAquisicao = aquisicaoListNewAquisicao.getIdproduto();
                    aquisicaoListNewAquisicao.setIdproduto(produto);
                    aquisicaoListNewAquisicao = em.merge(aquisicaoListNewAquisicao);
                    if (oldIdprodutoOfAquisicaoListNewAquisicao != null && !oldIdprodutoOfAquisicaoListNewAquisicao.equals(produto)) {
                        oldIdprodutoOfAquisicaoListNewAquisicao.getAquisicaoList().remove(aquisicaoListNewAquisicao);
                        oldIdprodutoOfAquisicaoListNewAquisicao = em.merge(oldIdprodutoOfAquisicaoListNewAquisicao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = produto.getIdproduto();
                if (findProduto(id) == null) {
                    throw new NonexistentEntityException("The produto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Produto produto;
            try {
                produto = em.getReference(Produto.class, id);
                produto.getIdproduto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The produto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Venda> vendaListOrphanCheck = produto.getVendaList();
            for (Venda vendaListOrphanCheckVenda : vendaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Produto (" + produto + ") cannot be destroyed since the Venda " + vendaListOrphanCheckVenda + " in its vendaList field has a non-nullable idproduto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Loja idloja = produto.getIdloja();
            if (idloja != null) {
                idloja.getProdutoList().remove(produto);
                idloja = em.merge(idloja);
            }
            List<Aquisicao> aquisicaoList = produto.getAquisicaoList();
            for (Aquisicao aquisicaoListAquisicao : aquisicaoList) {
                aquisicaoListAquisicao.setIdproduto(null);
                aquisicaoListAquisicao = em.merge(aquisicaoListAquisicao);
            }
            em.remove(produto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Produto> findProdutoEntities() {
        return findProdutoEntities(true, -1, -1);
    }

    public List<Produto> findProdutoEntities(int maxResults, int firstResult) {
        return findProdutoEntities(false, maxResults, firstResult);
    }

    private List<Produto> findProdutoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Produto.class));
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

    public Produto findProduto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Produto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProdutoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Produto> rt = cq.from(Produto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Produto> buscarProdutosPorLoja(int idloja){
        List<Produto> produtos = new ArrayList<>();
        findProdutoEntities().stream().filter((p) -> (p.getIdloja().getIdloja() == idloja)).forEachOrdered((p) -> {
            produtos.add(p);
        });
        
        return produtos;
    }
    
    public List<Produto> buscarProdutosPorNome(String nome){ 
        List<Produto> produtos = new ArrayList<>();
        for(Produto p: findProdutoEntities()){
            
            if(p.getNome().contains(nome)){
                produtos.add(p);
            }
            
        }
        
        return produtos;
    }
    
    public List<Produto> buscarProdutosPorData(Date from, Date to, List<Produto> list){
        Aquisicao aquisicao = new Aquisicao();
        AquisicaoJpaController ajc = new AquisicaoJpaController();
        List<Produto> produtos = new ArrayList<>();
        
        if(from != null && to!=null){
            for(Produto p: list){
                aquisicao = ajc.findAquisicao(p.getIdproduto());

                if((aquisicao.getDataAquisicao().after(from)) 
                        && (aquisicao.getDataAquisicao().before(to))){

                    produtos.add(p);

                }else if((aquisicao.getDataAquisicao().equals(from)) 
                        && (aquisicao.getDataAquisicao().before(to))){
                    produtos.add(p);
                }else if((aquisicao.getDataAquisicao().equals(from)) 
                        && (aquisicao.getDataAquisicao().equals(to))){
                    produtos.add(p);
                }
            }
            return produtos;
        }
        
        return null;
    }
    
}
