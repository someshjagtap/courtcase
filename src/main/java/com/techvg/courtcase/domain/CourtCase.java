package com.techvg.courtcase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CourtCase.
 */
@Entity
@Table(name = "court_case")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CourtCase implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "case_no")
    private String caseNo;

    @Column(name = "village_name")
    private String villageName;

    @Column(name = "accuser_name")
    private String accuserName;

    @Column(name = "application_no")
    private String applicationNo;

    @Column(name = "land_reference_no")
    private String landReferenceNo;

    @Column(name = "first_appeal")
    private String firstAppeal;

    @Column(name = "amount")
    private String amount;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "court_name")
    private String courtName;

    @Column(name = "defendant_name")
    private String defendantName;

    @Column(name = "case_description")
    private String caseDescription;

    @Column(name = "case_filing_date")
    private String caseFilingDate;

    @Column(name = "total_claim_amount")
    private String totalClaimAmount;

    @Column(name = "case_officer")
    private String caseOfficer;

    @Column(name = "caselawyer")
    private String caselawyer;

    @Column(name = "next_hearing_date")
    private String nextHearingDate;

    @Column(name = "amount_deposite_in_court")
    private String amountDepositeInCourt;

    @Column(name = "lar")
    private String lar;

    @Column(name = "inc_compensation")
    private String incCompensation;

    @Column(name = "amount_paid_slo")
    private String amountPaidSLO;

    @Column(name = "cheque_no")
    private String chequeNo;

    @Column(name = "cheque_date")
    private String chequeDate;

    @Column(name = "appeal_no")
    private String appealNo;

    @Column(name = "court_amount")
    private String courtAmount;

    @Column(name = "appeal_amount")
    private String appealAmount;

    @Column(name = "appeal_date")
    private String appealDate;

    @Column(name = "description")
    private String description;

    @Column(name = "comment")
    private String comment;

    @Column(name = "case_status")
    private String caseStatus;

    @Column(name = "freefield_1")
    private String freefield1;

    @Column(name = "freefield_2")
    private String freefield2;

    @Column(name = "freefield_3")
    private String freefield3;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @Column(name = "last_modified")
    private String lastModified;

    @OneToMany(mappedBy = "hearing")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "hearing" }, allowSetters = true)
    private Set<Hearing> courtCases = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CourtCase id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaseNo() {
        return this.caseNo;
    }

    public CourtCase caseNo(String caseNo) {
        this.setCaseNo(caseNo);
        return this;
    }

    public void setCaseNo(String caseNo) {
        this.caseNo = caseNo;
    }

    public String getVillageName() {
        return this.villageName;
    }

    public CourtCase villageName(String villageName) {
        this.setVillageName(villageName);
        return this;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getAccuserName() {
        return this.accuserName;
    }

    public CourtCase accuserName(String accuserName) {
        this.setAccuserName(accuserName);
        return this;
    }

    public void setAccuserName(String accuserName) {
        this.accuserName = accuserName;
    }

    public String getApplicationNo() {
        return this.applicationNo;
    }

    public CourtCase applicationNo(String applicationNo) {
        this.setApplicationNo(applicationNo);
        return this;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getLandReferenceNo() {
        return this.landReferenceNo;
    }

    public CourtCase landReferenceNo(String landReferenceNo) {
        this.setLandReferenceNo(landReferenceNo);
        return this;
    }

    public void setLandReferenceNo(String landReferenceNo) {
        this.landReferenceNo = landReferenceNo;
    }

    public String getFirstAppeal() {
        return this.firstAppeal;
    }

    public CourtCase firstAppeal(String firstAppeal) {
        this.setFirstAppeal(firstAppeal);
        return this;
    }

    public void setFirstAppeal(String firstAppeal) {
        this.firstAppeal = firstAppeal;
    }

    public String getAmount() {
        return this.amount;
    }

    public CourtCase amount(String amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public CourtCase projectName(String projectName) {
        this.setProjectName(projectName);
        return this;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCourtName() {
        return this.courtName;
    }

    public CourtCase courtName(String courtName) {
        this.setCourtName(courtName);
        return this;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getDefendantName() {
        return this.defendantName;
    }

    public CourtCase defendantName(String defendantName) {
        this.setDefendantName(defendantName);
        return this;
    }

    public void setDefendantName(String defendantName) {
        this.defendantName = defendantName;
    }

    public String getCaseDescription() {
        return this.caseDescription;
    }

    public CourtCase caseDescription(String caseDescription) {
        this.setCaseDescription(caseDescription);
        return this;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public String getCaseFilingDate() {
        return this.caseFilingDate;
    }

    public CourtCase caseFilingDate(String caseFilingDate) {
        this.setCaseFilingDate(caseFilingDate);
        return this;
    }

    public void setCaseFilingDate(String caseFilingDate) {
        this.caseFilingDate = caseFilingDate;
    }

    public String getTotalClaimAmount() {
        return this.totalClaimAmount;
    }

    public CourtCase totalClaimAmount(String totalClaimAmount) {
        this.setTotalClaimAmount(totalClaimAmount);
        return this;
    }

    public void setTotalClaimAmount(String totalClaimAmount) {
        this.totalClaimAmount = totalClaimAmount;
    }

    public String getCaseOfficer() {
        return this.caseOfficer;
    }

    public CourtCase caseOfficer(String caseOfficer) {
        this.setCaseOfficer(caseOfficer);
        return this;
    }

    public void setCaseOfficer(String caseOfficer) {
        this.caseOfficer = caseOfficer;
    }

    public String getCaselawyer() {
        return this.caselawyer;
    }

    public CourtCase caselawyer(String caselawyer) {
        this.setCaselawyer(caselawyer);
        return this;
    }

    public void setCaselawyer(String caselawyer) {
        this.caselawyer = caselawyer;
    }

    public String getNextHearingDate() {
        return this.nextHearingDate;
    }

    public CourtCase nextHearingDate(String nextHearingDate) {
        this.setNextHearingDate(nextHearingDate);
        return this;
    }

    public void setNextHearingDate(String nextHearingDate) {
        this.nextHearingDate = nextHearingDate;
    }

    public String getAmountDepositeInCourt() {
        return this.amountDepositeInCourt;
    }

    public CourtCase amountDepositeInCourt(String amountDepositeInCourt) {
        this.setAmountDepositeInCourt(amountDepositeInCourt);
        return this;
    }

    public void setAmountDepositeInCourt(String amountDepositeInCourt) {
        this.amountDepositeInCourt = amountDepositeInCourt;
    }

    public String getLar() {
        return this.lar;
    }

    public CourtCase lar(String lar) {
        this.setLar(lar);
        return this;
    }

    public void setLar(String lar) {
        this.lar = lar;
    }

    public String getIncCompensation() {
        return this.incCompensation;
    }

    public CourtCase incCompensation(String incCompensation) {
        this.setIncCompensation(incCompensation);
        return this;
    }

    public void setIncCompensation(String incCompensation) {
        this.incCompensation = incCompensation;
    }

    public String getAmountPaidSLO() {
        return this.amountPaidSLO;
    }

    public CourtCase amountPaidSLO(String amountPaidSLO) {
        this.setAmountPaidSLO(amountPaidSLO);
        return this;
    }

    public void setAmountPaidSLO(String amountPaidSLO) {
        this.amountPaidSLO = amountPaidSLO;
    }

    public String getChequeNo() {
        return this.chequeNo;
    }

    public CourtCase chequeNo(String chequeNo) {
        this.setChequeNo(chequeNo);
        return this;
    }

    public void setChequeNo(String chequeNo) {
        this.chequeNo = chequeNo;
    }

    public String getChequeDate() {
        return this.chequeDate;
    }

    public CourtCase chequeDate(String chequeDate) {
        this.setChequeDate(chequeDate);
        return this;
    }

    public void setChequeDate(String chequeDate) {
        this.chequeDate = chequeDate;
    }

    public String getAppealNo() {
        return this.appealNo;
    }

    public CourtCase appealNo(String appealNo) {
        this.setAppealNo(appealNo);
        return this;
    }

    public void setAppealNo(String appealNo) {
        this.appealNo = appealNo;
    }

    public String getCourtAmount() {
        return this.courtAmount;
    }

    public CourtCase courtAmount(String courtAmount) {
        this.setCourtAmount(courtAmount);
        return this;
    }

    public void setCourtAmount(String courtAmount) {
        this.courtAmount = courtAmount;
    }

    public String getAppealAmount() {
        return this.appealAmount;
    }

    public CourtCase appealAmount(String appealAmount) {
        this.setAppealAmount(appealAmount);
        return this;
    }

    public void setAppealAmount(String appealAmount) {
        this.appealAmount = appealAmount;
    }

    public String getAppealDate() {
        return this.appealDate;
    }

    public CourtCase appealDate(String appealDate) {
        this.setAppealDate(appealDate);
        return this;
    }

    public void setAppealDate(String appealDate) {
        this.appealDate = appealDate;
    }

    public String getDescription() {
        return this.description;
    }

    public CourtCase description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComment() {
        return this.comment;
    }

    public CourtCase comment(String comment) {
        this.setComment(comment);
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCaseStatus() {
        return this.caseStatus;
    }

    public CourtCase caseStatus(String caseStatus) {
        this.setCaseStatus(caseStatus);
        return this;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getFreefield1() {
        return this.freefield1;
    }

    public CourtCase freefield1(String freefield1) {
        this.setFreefield1(freefield1);
        return this;
    }

    public void setFreefield1(String freefield1) {
        this.freefield1 = freefield1;
    }

    public String getFreefield2() {
        return this.freefield2;
    }

    public CourtCase freefield2(String freefield2) {
        this.setFreefield2(freefield2);
        return this;
    }

    public void setFreefield2(String freefield2) {
        this.freefield2 = freefield2;
    }

    public String getFreefield3() {
        return this.freefield3;
    }

    public CourtCase freefield3(String freefield3) {
        this.setFreefield3(freefield3);
        return this;
    }

    public void setFreefield3(String freefield3) {
        this.freefield3 = freefield3;
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public CourtCase lastModifiedBy(String lastModifiedBy) {
        this.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public CourtCase lastModified(String lastModified) {
        this.setLastModified(lastModified);
        return this;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Set<Hearing> getCourtCases() {
        return this.courtCases;
    }

    public void setCourtCases(Set<Hearing> hearings) {
        if (this.courtCases != null) {
            this.courtCases.forEach(i -> i.setHearing(null));
        }
        if (hearings != null) {
            hearings.forEach(i -> i.setHearing(this));
        }
        this.courtCases = hearings;
    }

    public CourtCase courtCases(Set<Hearing> hearings) {
        this.setCourtCases(hearings);
        return this;
    }

    public CourtCase addCourtCase(Hearing hearing) {
        this.courtCases.add(hearing);
        hearing.setHearing(this);
        return this;
    }

    public CourtCase removeCourtCase(Hearing hearing) {
        this.courtCases.remove(hearing);
        hearing.setHearing(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CourtCase)) {
            return false;
        }
        return id != null && id.equals(((CourtCase) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CourtCase{" +
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
