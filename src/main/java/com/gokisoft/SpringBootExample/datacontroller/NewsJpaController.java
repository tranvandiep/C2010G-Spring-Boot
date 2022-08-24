/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.datacontroller;

import com.gokisoft.SpringBootExample.datacontroller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.gokisoft.SpringBootExample.entities.Category;
import com.gokisoft.SpringBootExample.entities.News;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrator
 */
public class NewsJpaController implements Serializable {

    public NewsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(News news) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category idCategory = news.getIdCategory();
            if (idCategory != null) {
                idCategory = em.getReference(idCategory.getClass(), idCategory.getId());
                news.setIdCategory(idCategory);
            }
            em.persist(news);
            if (idCategory != null) {
                idCategory.getNewsCollection().add(news);
                idCategory = em.merge(idCategory);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(News news) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            News persistentNews = em.find(News.class, news.getId());
            Category idCategoryOld = persistentNews.getIdCategory();
            Category idCategoryNew = news.getIdCategory();
            if (idCategoryNew != null) {
                idCategoryNew = em.getReference(idCategoryNew.getClass(), idCategoryNew.getId());
                news.setIdCategory(idCategoryNew);
            }
            news = em.merge(news);
            if (idCategoryOld != null && !idCategoryOld.equals(idCategoryNew)) {
                idCategoryOld.getNewsCollection().remove(news);
                idCategoryOld = em.merge(idCategoryOld);
            }
            if (idCategoryNew != null && !idCategoryNew.equals(idCategoryOld)) {
                idCategoryNew.getNewsCollection().add(news);
                idCategoryNew = em.merge(idCategoryNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = news.getId();
                if (findNews(id) == null) {
                    throw new NonexistentEntityException("The news with id " + id + " no longer exists.");
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
            News news;
            try {
                news = em.getReference(News.class, id);
                news.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The news with id " + id + " no longer exists.", enfe);
            }
            Category idCategory = news.getIdCategory();
            if (idCategory != null) {
                idCategory.getNewsCollection().remove(news);
                idCategory = em.merge(idCategory);
            }
            em.remove(news);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<News> findNewsEntities() {
        return findNewsEntities(true, -1, -1);
    }

    public List<News> findNewsEntities(int maxResults, int firstResult) {
        return findNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(News.class));
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

    public News findNews(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(News.class, id);
        } finally {
            em.close();
        }
    }

    public int getNewsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<News> rt = cq.from(News.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
