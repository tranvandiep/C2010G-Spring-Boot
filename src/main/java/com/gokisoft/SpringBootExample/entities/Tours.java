/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "tours")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tours.findAll", query = "SELECT t FROM Tours t"),
    @NamedQuery(name = "Tours.findById", query = "SELECT t FROM Tours t WHERE t.id = :id"),
    @NamedQuery(name = "Tours.findByTitle", query = "SELECT t FROM Tours t WHERE t.title = :title"),
    @NamedQuery(name = "Tours.findByThumbnail", query = "SELECT t FROM Tours t WHERE t.thumbnail = :thumbnail"),
    @NamedQuery(name = "Tours.findByAddress", query = "SELECT t FROM Tours t WHERE t.address = :address"),
    @NamedQuery(name = "Tours.findByTourDate", query = "SELECT t FROM Tours t WHERE t.tourDate = :tourDate"),
    @NamedQuery(name = "Tours.findByPrice", query = "SELECT t FROM Tours t WHERE t.price = :price")})
public class Tours implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 250)
    @Column(name = "title")
    private String title;
    @Size(max = 500)
    @Column(name = "thumbnail")
    private String thumbnail;
    @Size(max = 250)
    @Column(name = "address")
    private String address;
    @Column(name = "tour_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tourDate;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private Float price;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "content")
    private String content;

    public Tours() {
    }

    public Tours(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getTourDate() {
        return tourDate;
    }

    public void setTourDate(Date tourDate) {
        this.tourDate = tourDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tours)) {
            return false;
        }
        Tours other = (Tours) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gokisoft.SpringBootExample.entities.Tours[ id=" + id + " ]";
    }
    
}
