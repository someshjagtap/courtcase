package com.techvg.courtcase.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link com.techvg.courtcase.domain.CourtCase} entity.
 */
public class CourtCaseDTO implements Serializable {

    private Long id;

    private String caseNo;

    private String villageName;

    private String accuserName;

    private String applicationNo;

    private String landReferenceNo;

    private String firstAppeal;

    private String amount;

    private String projectName;

    private String courtName;

    private String defendantName;

    private String caseDescription;

    private Instant caseFilingDate;

    private String totalClaimAmount;

    private String caseOfficer;

    private String caselawyer;

    private Instant nextHearingDate;

    private String amountDepositeInCourt;

    private String lar;

    private String incCompensation;

    private String amountPaidSLO;

    private String chequeNo;

    private Instant chequeDate;

    private String appealNo;

    private String courtAmount;

    private String appealAmount;

    private Instant appealDate;

    private String description;

    private String comment;

    private String caseStatus;

    private String freefield1;

    private String freefield2;

    private String freefield3;

    private String lastModifiedBy;

    private String lastModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNo() {
        return caseNo;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getAccuserName() {
        return accuserName;
    }

    public void setAccuserName(String accuserName) {
        this.accuserName = accuserName;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getLandReferenceNo() {
        return landReferenceNo;
    }

    public void setLandReferenceNo(String landReferenceNo) {
        this.landReferenceNo = landReferenceNo;
    }

    public String getFirstAppeal() {
        return firstAppeal;
    }

    public void setFirstAppeal(String firstAppeal) {
        this.firstAppeal = firstAppeal;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getDefendantName() {
        return defendantName;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public Instant getCaseFilingDate() {
        return caseFilingDate;
    }

    public void setCaseFilingDate(Instant caseFilingDate) {
        this.caseFilingDate = caseFilingDate;
    }

    public String getTotalClaimAmount() {
        return totalClaimAmount;
    }

    public void setTotalClaimAmount(String totalClaimAmount) {
        this.totalClaimAmount = totalClaimAmount;
    }

    public String getCaseOfficer() {
        return caseOfficer;
    }

    public void setCaseOfficer(String caseOfficer) {
        this.caseOfficer = caseOfficer;
    }

    public String getCaselawyer() {
        return caselawyer;
    }

    public void setCaselawyer(String caselawyer) {
        this.caselawyer = caselawyer;
    }

    public Instant getNextHearingDate() {
        return nextHearingDate;
    }

    public void setNextHearingDate(Instant nextHearingDate) {
        this.nextHearingDate = nextHearingDate;
    }

    public String getAmountDepositeInCourt() {
        return amountDepositeInCourt;
    }

    public void setAmountDepositeInCourt(String amountDepositeInCourt) {
        this.amountDepositeInCourt = amountDepositeInCourt;
    }

    public String getLar() {
        return lar;
    }

    public void setLar(String lar) {
        this.lar = lar;
    }

    public String getIncCompensation() {
        return incCompensation;
    }

    public void setIncCompensation(String incCompensation) {
        this.incCompensation = incCompensation;
    }

    public String getAmountPaidSLO() {
        return amountPaidSLO;
    }

    public void setAmountPaidSLO(String amountPaidSLO) {
        this.amountPaidSLO = amountPaidSLO;
    }

    public String getChequeNo() {
        return chequeNo;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public Instant getChequeDate() {
        return chequeDate;
    }

    public void setChequeDate(Instant chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getAppealNo() {
        return appealNo;
    }

    public void setAppealNo(String appealNo) {
        this.appealNo = appealNo;
    }

    public String getCourtAmount() {
        return courtAmount;
    }

    public void setCourtAmount(String courtAmount) {
        this.courtAmount = courtAmount;
    }

    public String getAppealAmount() {
        return appealAmount;
    }

    public void setAppealAmount(String appealAmount) {
        this.appealAmount = appealAmount;
    }

    public Instant getAppealDate() {
        return appealDate;
    }

    public void setAppealDate(Instant appealDate) {
        this.appealDate = appealDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getFreefield1() {
        return freefield1;
    }

    public void setFreefield1(String freefield1) {
        this.freefield1 = freefield1;
    }

    public String getFreefield2() {
        return freefield2;
    }

    public void setFreefield2(String freefield2) {
        this.freefield2 = freefield2;
    }

    public String getFreefield3() {
        return freefield3;
    }

    public void setFreefield3(String freefield3) {
        this.freefield3 = freefield3;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourtCaseDTO)) {
            return false;
        }

        CourtCaseDTO courtCaseDTO = (CourtCaseDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, courtCaseDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CourtCaseDTO{" +
            "id=" + getId() +
            ", caseNo='" + getCaseNo() + "'" +
            ", villageName='" + getVillageName() + "'" +
            ", accuserName='" + getAccuserName() + "'" +
            ", applicationNo='" + getApplicationNo() + "'" +
            ", landReferenceNo='" + getLandReferenceNo() + "'" +
            ", firstAppeal='" + getFirstAppeal() + "'" +
            ", amount='" + getAmount() + "'" +
            ", projectName='" + getProjectName() + "'" +
            ", courtName='" + getCourtName() + "'" +
            ", defendantName='" + getDefendantName() + "'" +
            ", caseDescription='" + getCaseDescription() + "'" +
            ", caseFilingDate='" + getCaseFilingDate() + "'" +
            ", totalClaimAmount='" + getTotalClaimAmount() + "'" +
            ", caseOfficer='" + getCaseOfficer() + "'" +
            ", caselawyer='" + getCaselawyer() + "'" +
            ", nextHearingDate='" + getNextHearingDate() + "'" +
            ", amountDepositeInCourt='" + getAmountDepositeInCourt() + "'" +
            ", lar='" + getLar() + "'" +
            ", incCompensation='" + getIncCompensation() + "'" +
            ", amountPaidSLO='" + getAmountPaidSLO() + "'" +
            ", chequeNo='" + getChequeNo() + "'" +
            ", chequeDate='" + getChequeDate() + "'" +
            ", appealNo='" + getAppealNo() + "'" +
            ", courtAmount='" + getCourtAmount() + "'" +
            ", appealAmount='" + getAppealAmount() + "'" +
            ", appealDate='" + getAppealDate() + "'" +
            ", description='" + getDescription() + "'" +
            ", comment='" + getComment() + "'" +
            ", caseStatus='" + getCaseStatus() + "'" +
            ", freefield1='" + getFreefield1() + "'" +
            ", freefield2='" + getFreefield2() + "'" +
            ", freefield3='" + getFreefield3() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModified='" + getLastModified() + "'" +
            "}";
    }
}
