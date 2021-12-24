package com.techvg.courtcase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserData.
 */
@Entity
@Table(name = "user_data")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "address")
    private String address;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "verify_password")
    private String verifyPassword;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified")
    private String lastModified;

    @ManyToOne
    @JsonIgnoreProperties(value = { "users", "hearing" }, allowSetters = true)
    private CourtCase courtCase;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserData id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public UserData firstname(String firstname) {
        this.setFirstname(firstname);
        return this;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return this.lastName;
    }

    public UserData lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return this.contactNo;
    }

    public UserData contactNo(String contactNo) {
        this.setContactNo(contactNo);
        return this;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public UserData emailId(String emailId) {
        this.setEmailId(emailId);
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return this.address;
    }

    public UserData address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return this.userName;
    }

    public UserData userName(String userName) {
        this.setUserName(userName);
        return this;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public UserData password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return this.verifyPassword;
    }

    public UserData verifyPassword(String verifyPassword) {
        this.setVerifyPassword(verifyPassword);
        return this;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public UserData lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public UserData lastModified(String lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public CourtCase getCourtCase() {
        return this.courtCase;
    }

    public void setCourtCase(CourtCase courtCase) {
        this.courtCase = courtCase;
    }

    public UserData courtCase(CourtCase courtCase) {
        this.setCourtCase(courtCase);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserData)) {
            return false;
        }
        return id != null && id.equals(((UserData) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserData{" +
            "id=" + getId() +
            ", firstname='" + getFirstname() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", contactNo='" + getContactNo() + "'" +
            ", emailId='" + getEmailId() + "'" +
            ", address='" + getAddress() + "'" +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", verifyPassword='" + getVerifyPassword() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
