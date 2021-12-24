package com.techvg.courtcase.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.techvg.courtcase.domain.UserData} entity.
 */
public class UserDataDTO implements Serializable {

    private Long id;

    private String firstname;

    private String lastName;

    private String contactNo;

    private String emailId;

    private String address;

    private String userName;

    private String password;

    private String verifyPassword;

    private String lastModifiedBy;

    private String lastModified;

    private CourtCaseDTO courtCase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public CourtCaseDTO getCourtCase() {
        return courtCase;
    }

    public void setCourtCase(CourtCaseDTO courtCase) {
        this.courtCase = courtCase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDataDTO)) {
            return false;
        }

        UserDataDTO userDataDTO = (UserDataDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userDataDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserDataDTO{" +
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
            ", courtCase=" + getCourtCase() +
            "}";
    }
}
