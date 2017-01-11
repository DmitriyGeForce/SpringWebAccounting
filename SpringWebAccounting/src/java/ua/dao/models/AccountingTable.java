/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.dao.models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "accounting_table", catalog = "accounting", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"accounting_table_id"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountingTable.findAll", query = "SELECT a FROM AccountingTable a"),
    @NamedQuery(name = "AccountingTable.findByAccountingTableId", query = "SELECT a FROM AccountingTable a WHERE a.accountingTableId = :accountingTableId"),
    @NamedQuery(name = "AccountingTable.findByUserId", query = "SELECT a FROM AccountingTable a WHERE a.userId = :userId"),
    @NamedQuery(name = "AccountingTable.findByDateOfAction", query = "SELECT a FROM AccountingTable a WHERE a.dateOfAction = :dateOfAction"),
    @NamedQuery(name = "AccountingTable.findByTypeOfAction", query = "SELECT a FROM AccountingTable a WHERE a.typeOfAction = :typeOfAction"),
    @NamedQuery(name = "AccountingTable.findByAmount", query = "SELECT a FROM AccountingTable a WHERE a.amount = :amount"),
    @NamedQuery(name = "AccountingTable.findByTotalAmount", query = "SELECT a FROM AccountingTable a WHERE a.totalAmount = :totalAmount")})
public class AccountingTable implements Serializable {

    @Basic(optional = false)
    @Column(name = "amount", nullable = false)
    private double amount;
    @Basic(optional = false)
    @Column(name = "totalAmount", nullable = false)
    private double totalAmount;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "accounting_table_id", nullable = false)
    private Integer accountingTableId;
    @Basic(optional = false)
    @Column(name = "dateOfAction", nullable = false, length = 45)
    private String dateOfAction;
    @Basic(optional = false)
    @Column(name = "typeOfAction", nullable = false, length = 45)
    private String typeOfAction;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ManyToOne(optional = false)
    private Users userId;

    public AccountingTable() {
    }

    public AccountingTable(double amount, String dateOfAction, String typeOfAction) {
        this.amount = amount;
        this.dateOfAction = dateOfAction;
        this.typeOfAction = typeOfAction;
    }

    public AccountingTable(double amount, Integer accountingTableId, String typeOfAction) {
        this.amount = amount;
        this.accountingTableId = accountingTableId;
        this.typeOfAction = typeOfAction;
    }

    public Integer getAccountingTableId() {
        return accountingTableId;
    }

    public void setAccountingTableId(Integer accountingTableId) {
        this.accountingTableId = accountingTableId;
    }

    public String getDateOfAction() {
        return dateOfAction;
    }

    public void setDateOfAction(String dateOfAction) {
        this.dateOfAction = dateOfAction;
    }

    public String getTypeOfAction() {
        return typeOfAction;
    }

    public void setTypeOfAction(String typeOfAction) {
        this.typeOfAction = typeOfAction;
    }


    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountingTableId != null ? accountingTableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountingTable)) {
            return false;
        }
        AccountingTable other = (AccountingTable) object;
        if ((this.accountingTableId == null && other.accountingTableId != null) || (this.accountingTableId != null && !this.accountingTableId.equals(other.accountingTableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ua.web.AccountingTable[ accountingTableId=" + accountingTableId + " ]";
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
}
