/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gokisoft.SpringBootExample.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrator
 */
@Entity
@Table(name = "user_tokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserTokens.findAll", query = "SELECT u FROM UserTokens u"),
    @NamedQuery(name = "UserTokens.findByIdUser", query = "SELECT u FROM UserTokens u WHERE u.userTokensPK.idUser = :idUser"),
    @NamedQuery(name = "UserTokens.findByToken", query = "SELECT u FROM UserTokens u WHERE u.userTokensPK.token = :token")})
public class UserTokens implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserTokensPK userTokensPK;

    public UserTokens() {
    }

    public UserTokens(UserTokensPK userTokensPK) {
        this.userTokensPK = userTokensPK;
    }

    public UserTokens(int idUser, String token) {
        this.userTokensPK = new UserTokensPK(idUser, token);
    }

    public UserTokensPK getUserTokensPK() {
        return userTokensPK;
    }

    public void setUserTokensPK(UserTokensPK userTokensPK) {
        this.userTokensPK = userTokensPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userTokensPK != null ? userTokensPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTokens)) {
            return false;
        }
        UserTokens other = (UserTokens) object;
        if ((this.userTokensPK == null && other.userTokensPK != null) || (this.userTokensPK != null && !this.userTokensPK.equals(other.userTokensPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.gokisoft.SpringBootExample.entities.UserTokens[ userTokensPK=" + userTokensPK + " ]";
    }
    
}
