/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.datacontroller;

import com.gokisoft.SpringBootExample.datacontroller.exceptions.NonexistentEntityException;
import com.gokisoft.SpringBootExample.entities.Category;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.gokisoft.SpringBootExample.entities.News;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Administrator
 */
public class CategoryJpaController implements Serializable {

    public CategoryJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Category category) {
        if (category.getNewsCollection() == null) {
            category.setNewsCollection(new ArrayList<News>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<News> attachedNewsCollection = new ArrayList<News>();
            for (News newsCollectionNewsToAttach : category.getNewsCollection()) {
                newsCollectionNewsToAttach = em.getReference(newsCollectionNewsToAttach.getClass(), newsCollectionNewsToAttach.getId());
                attachedNewsCollection.add(newsCollectionNewsToAttach);
            }
            category.setNewsCollection(attachedNewsCollection);
            em.persist(category);
            for (News newsCollectionNews : category.getNewsCollection()) {
                Category oldIdCategoryOfNewsCollectionNews = newsCollectionNews.getIdCategory();
                newsCollectionNews.setIdCategory(category);
                newsCollectionNews = em.merge(newsCollectionNews);
                if (oldIdCategoryOfNewsCollectionNews != null) {
                    oldIdCategoryOfNewsCollectionNews.getNewsCollection().remove(newsCollectionNews);
                    oldIdCategoryOfNewsCollectionNews = em.merge(oldIdCategoryOfNewsCollectionNews);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Category category) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category persistentCategory = em.find(Category.class, category.getId());
            Collection<News> newsCollectionOld = persistentCategory.getNewsCollection();
            Collection<News> newsCollectionNew = category.getNewsCollection();
            Collection<News> attachedNewsCollectionNew = new ArrayList<News>();
            for (News newsCollectionNewNewsToAttach : newsCollectionNew) {
                newsCollectionNewNewsToAttach = em.getReference(newsCollectionNewNewsToAttach.getClass(), newsCollectionNewNewsToAttach.getId());
                attachedNewsCollectionNew.add(newsCollectionNewNewsToAttach);
            }
            newsCollectionNew = attachedNewsCollectionNew;
            category.setNewsCollection(newsCollectionNew);
            category = em.merge(category);
            for (News newsCollectionOldNews : newsCollectionOld) {
                if (!newsCollectionNew.contains(newsCollectionOldNews)) {
                    newsCollectionOldNews.setIdCategory(null);
                    newsCollectionOldNews = em.merge(newsCollectionOldNews);
                }
            }
            for (News newsCollectionNewNews : newsCollectionNew) {
                if (!newsCollectionOld.contains(newsCollectionNewNews)) {
                    Category oldIdCategoryOfNewsCollectionNewNews = newsCollectionNewNews.getIdCategory();
                    newsCollectionNewNews.setIdCategory(category);
                    newsCollectionNewNews = em.merge(newsCollectionNewNews);
                    if (oldIdCategoryOfNewsCollectionNewNews != null && !oldIdCategoryOfNewsCollectionNewNews.equals(category)) {
                        oldIdCategoryOfNewsCollectionNewNews.getNewsCollection().remove(newsCollectionNewNews);
                        oldIdCategoryOfNewsCollectionNewNews = em.merge(oldIdCategoryOfNewsCollectionNewNews);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = category.getId();
                if (findCategory(id) == null) {
                    throw new NonexistentEntityException("The category with id " + id + " no longer exists.");
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
            Category category;
            try {
                category = em.getReference(Category.class, id);
                category.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The category with id " + id + " no longer exists.", enfe);
            }
            Collection<News> newsCollection = category.getNewsCollection();
            for (News newsCollectionNews : newsCollection) {
                newsCollectionNews.setIdCategory(null);
                newsCollectionNews = em.merge(newsCollectionNews);
            }
            em.remove(category);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Category> findCategoryEntities() {
        return findCategoryEntities(true, -1, -1);
    }

    public List<Category> findCategoryEntities(int maxResults, int firstResult) {
        return findCategoryEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Category.class));
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

    public Category findCategory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Category> rt = cq.from(Category.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
