package com.techvg.courtcase.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.techvg.courtcase.IntegrationTest;
import com.techvg.courtcase.domain.CourtCase;
import com.techvg.courtcase.repository.CourtCaseRepository;
import com.techvg.courtcase.service.dto.CourtCaseDTO;
import com.techvg.courtcase.service.mapper.CourtCaseMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CourtCaseResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CourtCaseResourceIT {

    private static final String DEFAULT_CASE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CASE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_VILLAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_VILLAGE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_ACCUSER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ACCUSER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATION_NO = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION_NO = "BBBBBBBBBB";

    private static final String DEFAULT_LAND_REFERENCE_NO = "AAAAAAAAAA";
    private static final String UPDATED_LAND_REFERENCE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_APPEAL = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_APPEAL = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COURT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COURT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEFENDANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEFENDANT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_CASE_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_FILING_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CASE_FILING_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_TOTAL_CLAIM_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TOTAL_CLAIM_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_OFFICER = "AAAAAAAAAA";
    private static final String UPDATED_CASE_OFFICER = "BBBBBBBBBB";

    private static final String DEFAULT_CASELAWYER = "AAAAAAAAAA";
    private static final String UPDATED_CASELAWYER = "BBBBBBBBBB";

    private static final String DEFAULT_NEXT_HEARING_DATE = "AAAAAAAAAA";
    private static final String UPDATED_NEXT_HEARING_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT_DEPOSITE_IN_COURT = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_DEPOSITE_IN_COURT = "BBBBBBBBBB";

    private static final String DEFAULT_LAR = "AAAAAAAAAA";
    private static final String UPDATED_LAR = "BBBBBBBBBB";

    private static final String DEFAULT_INC_COMPENSATION = "AAAAAAAAAA";
    private static final String UPDATED_INC_COMPENSATION = "BBBBBBBBBB";

    private static final String DEFAULT_AMOUNT_PAID_SLO = "AAAAAAAAAA";
    private static final String UPDATED_AMOUNT_PAID_SLO = "BBBBBBBBBB";

    private static final String DEFAULT_CHEQUE_NO = "AAAAAAAAAA";
    private static final String UPDATED_CHEQUE_NO = "BBBBBBBBBB";

    private static final String DEFAULT_CHEQUE_DATE = "AAAAAAAAAA";
    private static final String UPDATED_CHEQUE_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_APPEAL_NO = "AAAAAAAAAA";
    private static final String UPDATED_APPEAL_NO = "BBBBBBBBBB";

    private static final String DEFAULT_COURT_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_COURT_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_APPEAL_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_APPEAL_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_APPEAL_DATE = "AAAAAAAAAA";
    private static final String UPDATED_APPEAL_DATE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    private static final String DEFAULT_CASE_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CASE_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_FREEFIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_FREEFIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_FREEFIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_FREEFIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_FREEFIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_FREEFIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_MODIFIED = "AAAAAAAAAA";
    private static final String UPDATED_LAST_MODIFIED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/court-cases";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CourtCaseRepository courtCaseRepository;

    @Autowired
    private CourtCaseMapper courtCaseMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCourtCaseMockMvc;

    private CourtCase courtCase;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourtCase createEntity(EntityManager em) {
        CourtCase courtCase = new CourtCase()
            .caseNo(DEFAULT_CASE_NO)
            .villageName(DEFAULT_VILLAGE_NAME)
            .accuserName(DEFAULT_ACCUSER_NAME)
            .applicationNo(DEFAULT_APPLICATION_NO)
            .landReferenceNo(DEFAULT_LAND_REFERENCE_NO)
            .firstAppeal(DEFAULT_FIRST_APPEAL)
            .amount(DEFAULT_AMOUNT)
            .projectName(DEFAULT_PROJECT_NAME)
            .courtName(DEFAULT_COURT_NAME)
            .defendantName(DEFAULT_DEFENDANT_NAME)
            .caseDescription(DEFAULT_CASE_DESCRIPTION)
            .caseFilingDate(DEFAULT_CASE_FILING_DATE)
            .totalClaimAmount(DEFAULT_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(DEFAULT_CASE_OFFICER)
            .caselawyer(DEFAULT_CASELAWYER)
            .nextHearingDate(DEFAULT_NEXT_HEARING_DATE)
            .amountDepositeInCourt(DEFAULT_AMOUNT_DEPOSITE_IN_COURT)
            .lar(DEFAULT_LAR)
            .incCompensation(DEFAULT_INC_COMPENSATION)
            .amountPaidSLO(DEFAULT_AMOUNT_PAID_SLO)
            .chequeNo(DEFAULT_CHEQUE_NO)
            .chequeDate(DEFAULT_CHEQUE_DATE)
            .appealNo(DEFAULT_APPEAL_NO)
            .courtAmount(DEFAULT_COURT_AMOUNT)
            .appealAmount(DEFAULT_APPEAL_AMOUNT)
            .appealDate(DEFAULT_APPEAL_DATE)
            .description(DEFAULT_DESCRIPTION)
            .comment(DEFAULT_COMMENT)
            .caseStatus(DEFAULT_CASE_STATUS)
            .freefield1(DEFAULT_FREEFIELD_1)
            .freefield2(DEFAULT_FREEFIELD_2)
            .freefield3(DEFAULT_FREEFIELD_3)
            .lastModifiedBy(DEFAULT_LAST_MODIFIED_BY)
            .lastModified(DEFAULT_LAST_MODIFIED);
        return courtCase;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CourtCase createUpdatedEntity(EntityManager em) {
        CourtCase courtCase = new CourtCase()
            .caseNo(UPDATED_CASE_NO)
            .villageName(UPDATED_VILLAGE_NAME)
            .accuserName(UPDATED_ACCUSER_NAME)
            .applicationNo(UPDATED_APPLICATION_NO)
            .landReferenceNo(UPDATED_LAND_REFERENCE_NO)
            .firstAppeal(UPDATED_FIRST_APPEAL)
            .amount(UPDATED_AMOUNT)
            .projectName(UPDATED_PROJECT_NAME)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .totalClaimAmount(UPDATED_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(UPDATED_CASE_OFFICER)
            .caselawyer(UPDATED_CASELAWYER)
            .nextHearingDate(UPDATED_NEXT_HEARING_DATE)
            .amountDepositeInCourt(UPDATED_AMOUNT_DEPOSITE_IN_COURT)
            .lar(UPDATED_LAR)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .chequeDate(UPDATED_CHEQUE_DATE)
            .appealNo(UPDATED_APPEAL_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .appealDate(UPDATED_APPEAL_DATE)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .caseStatus(UPDATED_CASE_STATUS)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield2(UPDATED_FREEFIELD_2)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModified(UPDATED_LAST_MODIFIED);
        return courtCase;
    }

    @BeforeEach
    public void initTest() {
        courtCase = createEntity(em);
    }

    @Test
    @Transactional
    void createCourtCase() throws Exception {
        int databaseSizeBeforeCreate = courtCaseRepository.findAll().size();
        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);
        restCourtCaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courtCaseDTO)))
            .andExpect(status().isCreated());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeCreate + 1);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(DEFAULT_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(DEFAULT_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(DEFAULT_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(DEFAULT_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(DEFAULT_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(DEFAULT_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(DEFAULT_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(DEFAULT_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(DEFAULT_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(DEFAULT_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(DEFAULT_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(DEFAULT_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(DEFAULT_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(DEFAULT_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(DEFAULT_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(DEFAULT_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(DEFAULT_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(DEFAULT_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(DEFAULT_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(DEFAULT_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(DEFAULT_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(DEFAULT_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(DEFAULT_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(DEFAULT_COMMENT);
        assertThat(testCourtCase.getCaseStatus()).isEqualTo(DEFAULT_CASE_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(DEFAULT_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(DEFAULT_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(DEFAULT_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(DEFAULT_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void createCourtCaseWithExistingId() throws Exception {
        // Create the CourtCase with an existing ID
        courtCase.setId(1L);
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        int databaseSizeBeforeCreate = courtCaseRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCourtCaseMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courtCaseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCourtCases() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get all the courtCaseList
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(courtCase.getId().intValue())))
            .andExpect(jsonPath("$.[*].caseNo").value(hasItem(DEFAULT_CASE_NO)))
            .andExpect(jsonPath("$.[*].villageName").value(hasItem(DEFAULT_VILLAGE_NAME)))
            .andExpect(jsonPath("$.[*].accuserName").value(hasItem(DEFAULT_ACCUSER_NAME)))
            .andExpect(jsonPath("$.[*].applicationNo").value(hasItem(DEFAULT_APPLICATION_NO)))
            .andExpect(jsonPath("$.[*].landReferenceNo").value(hasItem(DEFAULT_LAND_REFERENCE_NO)))
            .andExpect(jsonPath("$.[*].firstAppeal").value(hasItem(DEFAULT_FIRST_APPEAL)))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT)))
            .andExpect(jsonPath("$.[*].projectName").value(hasItem(DEFAULT_PROJECT_NAME)))
            .andExpect(jsonPath("$.[*].courtName").value(hasItem(DEFAULT_COURT_NAME)))
            .andExpect(jsonPath("$.[*].defendantName").value(hasItem(DEFAULT_DEFENDANT_NAME)))
            .andExpect(jsonPath("$.[*].caseDescription").value(hasItem(DEFAULT_CASE_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].caseFilingDate").value(hasItem(DEFAULT_CASE_FILING_DATE)))
            .andExpect(jsonPath("$.[*].totalClaimAmount").value(hasItem(DEFAULT_TOTAL_CLAIM_AMOUNT)))
            .andExpect(jsonPath("$.[*].caseOfficer").value(hasItem(DEFAULT_CASE_OFFICER)))
            .andExpect(jsonPath("$.[*].caselawyer").value(hasItem(DEFAULT_CASELAWYER)))
            .andExpect(jsonPath("$.[*].nextHearingDate").value(hasItem(DEFAULT_NEXT_HEARING_DATE)))
            .andExpect(jsonPath("$.[*].amountDepositeInCourt").value(hasItem(DEFAULT_AMOUNT_DEPOSITE_IN_COURT)))
            .andExpect(jsonPath("$.[*].lar").value(hasItem(DEFAULT_LAR)))
            .andExpect(jsonPath("$.[*].incCompensation").value(hasItem(DEFAULT_INC_COMPENSATION)))
            .andExpect(jsonPath("$.[*].amountPaidSLO").value(hasItem(DEFAULT_AMOUNT_PAID_SLO)))
            .andExpect(jsonPath("$.[*].chequeNo").value(hasItem(DEFAULT_CHEQUE_NO)))
            .andExpect(jsonPath("$.[*].chequeDate").value(hasItem(DEFAULT_CHEQUE_DATE)))
            .andExpect(jsonPath("$.[*].appealNo").value(hasItem(DEFAULT_APPEAL_NO)))
            .andExpect(jsonPath("$.[*].courtAmount").value(hasItem(DEFAULT_COURT_AMOUNT)))
            .andExpect(jsonPath("$.[*].appealAmount").value(hasItem(DEFAULT_APPEAL_AMOUNT)))
            .andExpect(jsonPath("$.[*].appealDate").value(hasItem(DEFAULT_APPEAL_DATE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT)))
            .andExpect(jsonPath("$.[*].caseStatus").value(hasItem(DEFAULT_CASE_STATUS)))
            .andExpect(jsonPath("$.[*].freefield1").value(hasItem(DEFAULT_FREEFIELD_1)))
            .andExpect(jsonPath("$.[*].freefield2").value(hasItem(DEFAULT_FREEFIELD_2)))
            .andExpect(jsonPath("$.[*].freefield3").value(hasItem(DEFAULT_FREEFIELD_3)))
            .andExpect(jsonPath("$.[*].lastModifiedBy").value(hasItem(DEFAULT_LAST_MODIFIED_BY)))
            .andExpect(jsonPath("$.[*].lastModified").value(hasItem(DEFAULT_LAST_MODIFIED)));
    }

    @Test
    @Transactional
    void getCourtCase() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        // Get the courtCase
        restCourtCaseMockMvc
            .perform(get(ENTITY_API_URL_ID, courtCase.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(courtCase.getId().intValue()))
            .andExpect(jsonPath("$.caseNo").value(DEFAULT_CASE_NO))
            .andExpect(jsonPath("$.villageName").value(DEFAULT_VILLAGE_NAME))
            .andExpect(jsonPath("$.accuserName").value(DEFAULT_ACCUSER_NAME))
            .andExpect(jsonPath("$.applicationNo").value(DEFAULT_APPLICATION_NO))
            .andExpect(jsonPath("$.landReferenceNo").value(DEFAULT_LAND_REFERENCE_NO))
            .andExpect(jsonPath("$.firstAppeal").value(DEFAULT_FIRST_APPEAL))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT))
            .andExpect(jsonPath("$.projectName").value(DEFAULT_PROJECT_NAME))
            .andExpect(jsonPath("$.courtName").value(DEFAULT_COURT_NAME))
            .andExpect(jsonPath("$.defendantName").value(DEFAULT_DEFENDANT_NAME))
            .andExpect(jsonPath("$.caseDescription").value(DEFAULT_CASE_DESCRIPTION))
            .andExpect(jsonPath("$.caseFilingDate").value(DEFAULT_CASE_FILING_DATE))
            .andExpect(jsonPath("$.totalClaimAmount").value(DEFAULT_TOTAL_CLAIM_AMOUNT))
            .andExpect(jsonPath("$.caseOfficer").value(DEFAULT_CASE_OFFICER))
            .andExpect(jsonPath("$.caselawyer").value(DEFAULT_CASELAWYER))
            .andExpect(jsonPath("$.nextHearingDate").value(DEFAULT_NEXT_HEARING_DATE))
            .andExpect(jsonPath("$.amountDepositeInCourt").value(DEFAULT_AMOUNT_DEPOSITE_IN_COURT))
            .andExpect(jsonPath("$.lar").value(DEFAULT_LAR))
            .andExpect(jsonPath("$.incCompensation").value(DEFAULT_INC_COMPENSATION))
            .andExpect(jsonPath("$.amountPaidSLO").value(DEFAULT_AMOUNT_PAID_SLO))
            .andExpect(jsonPath("$.chequeNo").value(DEFAULT_CHEQUE_NO))
            .andExpect(jsonPath("$.chequeDate").value(DEFAULT_CHEQUE_DATE))
            .andExpect(jsonPath("$.appealNo").value(DEFAULT_APPEAL_NO))
            .andExpect(jsonPath("$.courtAmount").value(DEFAULT_COURT_AMOUNT))
            .andExpect(jsonPath("$.appealAmount").value(DEFAULT_APPEAL_AMOUNT))
            .andExpect(jsonPath("$.appealDate").value(DEFAULT_APPEAL_DATE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT))
            .andExpect(jsonPath("$.caseStatus").value(DEFAULT_CASE_STATUS))
            .andExpect(jsonPath("$.freefield1").value(DEFAULT_FREEFIELD_1))
            .andExpect(jsonPath("$.freefield2").value(DEFAULT_FREEFIELD_2))
            .andExpect(jsonPath("$.freefield3").value(DEFAULT_FREEFIELD_3))
            .andExpect(jsonPath("$.lastModifiedBy").value(DEFAULT_LAST_MODIFIED_BY))
            .andExpect(jsonPath("$.lastModified").value(DEFAULT_LAST_MODIFIED));
    }

    @Test
    @Transactional
    void getNonExistingCourtCase() throws Exception {
        // Get the courtCase
        restCourtCaseMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewCourtCase() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();

        // Update the courtCase
        CourtCase updatedCourtCase = courtCaseRepository.findById(courtCase.getId()).get();
        // Disconnect from session so that the updates on updatedCourtCase are not directly saved in db
        em.detach(updatedCourtCase);
        updatedCourtCase
            .caseNo(UPDATED_CASE_NO)
            .villageName(UPDATED_VILLAGE_NAME)
            .accuserName(UPDATED_ACCUSER_NAME)
            .applicationNo(UPDATED_APPLICATION_NO)
            .landReferenceNo(UPDATED_LAND_REFERENCE_NO)
            .firstAppeal(UPDATED_FIRST_APPEAL)
            .amount(UPDATED_AMOUNT)
            .projectName(UPDATED_PROJECT_NAME)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .totalClaimAmount(UPDATED_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(UPDATED_CASE_OFFICER)
            .caselawyer(UPDATED_CASELAWYER)
            .nextHearingDate(UPDATED_NEXT_HEARING_DATE)
            .amountDepositeInCourt(UPDATED_AMOUNT_DEPOSITE_IN_COURT)
            .lar(UPDATED_LAR)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .chequeDate(UPDATED_CHEQUE_DATE)
            .appealNo(UPDATED_APPEAL_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .appealDate(UPDATED_APPEAL_DATE)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .caseStatus(UPDATED_CASE_STATUS)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield2(UPDATED_FREEFIELD_2)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModified(UPDATED_LAST_MODIFIED);
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(updatedCourtCase);

        restCourtCaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, courtCaseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isOk());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(UPDATED_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(UPDATED_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(UPDATED_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(UPDATED_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(UPDATED_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(UPDATED_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(UPDATED_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(UPDATED_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(UPDATED_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(UPDATED_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(UPDATED_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(UPDATED_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(UPDATED_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(UPDATED_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(UPDATED_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(UPDATED_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(UPDATED_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(UPDATED_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(UPDATED_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(UPDATED_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(UPDATED_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(UPDATED_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCourtCase.getCaseStatus()).isEqualTo(UPDATED_CASE_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(UPDATED_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(UPDATED_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(UPDATED_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void putNonExistingCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, courtCaseDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(courtCaseDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCourtCaseWithPatch() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();

        // Update the courtCase using partial update
        CourtCase partialUpdatedCourtCase = new CourtCase();
        partialUpdatedCourtCase.setId(courtCase.getId());

        partialUpdatedCourtCase
            .applicationNo(UPDATED_APPLICATION_NO)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .caselawyer(UPDATED_CASELAWYER)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModified(UPDATED_LAST_MODIFIED);

        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourtCase.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourtCase))
            )
            .andExpect(status().isOk());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(DEFAULT_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(DEFAULT_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(DEFAULT_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(UPDATED_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(DEFAULT_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(DEFAULT_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(DEFAULT_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(UPDATED_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(UPDATED_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(UPDATED_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(DEFAULT_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(DEFAULT_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(UPDATED_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(DEFAULT_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(DEFAULT_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(DEFAULT_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(UPDATED_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(UPDATED_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(UPDATED_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(DEFAULT_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(DEFAULT_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(UPDATED_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(UPDATED_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(DEFAULT_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCourtCase.getCaseStatus()).isEqualTo(DEFAULT_CASE_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(UPDATED_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(DEFAULT_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(UPDATED_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(DEFAULT_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void fullUpdateCourtCaseWithPatch() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();

        // Update the courtCase using partial update
        CourtCase partialUpdatedCourtCase = new CourtCase();
        partialUpdatedCourtCase.setId(courtCase.getId());

        partialUpdatedCourtCase
            .caseNo(UPDATED_CASE_NO)
            .villageName(UPDATED_VILLAGE_NAME)
            .accuserName(UPDATED_ACCUSER_NAME)
            .applicationNo(UPDATED_APPLICATION_NO)
            .landReferenceNo(UPDATED_LAND_REFERENCE_NO)
            .firstAppeal(UPDATED_FIRST_APPEAL)
            .amount(UPDATED_AMOUNT)
            .projectName(UPDATED_PROJECT_NAME)
            .courtName(UPDATED_COURT_NAME)
            .defendantName(UPDATED_DEFENDANT_NAME)
            .caseDescription(UPDATED_CASE_DESCRIPTION)
            .caseFilingDate(UPDATED_CASE_FILING_DATE)
            .totalClaimAmount(UPDATED_TOTAL_CLAIM_AMOUNT)
            .caseOfficer(UPDATED_CASE_OFFICER)
            .caselawyer(UPDATED_CASELAWYER)
            .nextHearingDate(UPDATED_NEXT_HEARING_DATE)
            .amountDepositeInCourt(UPDATED_AMOUNT_DEPOSITE_IN_COURT)
            .lar(UPDATED_LAR)
            .incCompensation(UPDATED_INC_COMPENSATION)
            .amountPaidSLO(UPDATED_AMOUNT_PAID_SLO)
            .chequeNo(UPDATED_CHEQUE_NO)
            .chequeDate(UPDATED_CHEQUE_DATE)
            .appealNo(UPDATED_APPEAL_NO)
            .courtAmount(UPDATED_COURT_AMOUNT)
            .appealAmount(UPDATED_APPEAL_AMOUNT)
            .appealDate(UPDATED_APPEAL_DATE)
            .description(UPDATED_DESCRIPTION)
            .comment(UPDATED_COMMENT)
            .caseStatus(UPDATED_CASE_STATUS)
            .freefield1(UPDATED_FREEFIELD_1)
            .freefield2(UPDATED_FREEFIELD_2)
            .freefield3(UPDATED_FREEFIELD_3)
            .lastModifiedBy(UPDATED_LAST_MODIFIED_BY)
            .lastModified(UPDATED_LAST_MODIFIED);

        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCourtCase.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCourtCase))
            )
            .andExpect(status().isOk());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
        CourtCase testCourtCase = courtCaseList.get(courtCaseList.size() - 1);
        assertThat(testCourtCase.getCaseNo()).isEqualTo(UPDATED_CASE_NO);
        assertThat(testCourtCase.getVillageName()).isEqualTo(UPDATED_VILLAGE_NAME);
        assertThat(testCourtCase.getAccuserName()).isEqualTo(UPDATED_ACCUSER_NAME);
        assertThat(testCourtCase.getApplicationNo()).isEqualTo(UPDATED_APPLICATION_NO);
        assertThat(testCourtCase.getLandReferenceNo()).isEqualTo(UPDATED_LAND_REFERENCE_NO);
        assertThat(testCourtCase.getFirstAppeal()).isEqualTo(UPDATED_FIRST_APPEAL);
        assertThat(testCourtCase.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testCourtCase.getProjectName()).isEqualTo(UPDATED_PROJECT_NAME);
        assertThat(testCourtCase.getCourtName()).isEqualTo(UPDATED_COURT_NAME);
        assertThat(testCourtCase.getDefendantName()).isEqualTo(UPDATED_DEFENDANT_NAME);
        assertThat(testCourtCase.getCaseDescription()).isEqualTo(UPDATED_CASE_DESCRIPTION);
        assertThat(testCourtCase.getCaseFilingDate()).isEqualTo(UPDATED_CASE_FILING_DATE);
        assertThat(testCourtCase.getTotalClaimAmount()).isEqualTo(UPDATED_TOTAL_CLAIM_AMOUNT);
        assertThat(testCourtCase.getCaseOfficer()).isEqualTo(UPDATED_CASE_OFFICER);
        assertThat(testCourtCase.getCaselawyer()).isEqualTo(UPDATED_CASELAWYER);
        assertThat(testCourtCase.getNextHearingDate()).isEqualTo(UPDATED_NEXT_HEARING_DATE);
        assertThat(testCourtCase.getAmountDepositeInCourt()).isEqualTo(UPDATED_AMOUNT_DEPOSITE_IN_COURT);
        assertThat(testCourtCase.getLar()).isEqualTo(UPDATED_LAR);
        assertThat(testCourtCase.getIncCompensation()).isEqualTo(UPDATED_INC_COMPENSATION);
        assertThat(testCourtCase.getAmountPaidSLO()).isEqualTo(UPDATED_AMOUNT_PAID_SLO);
        assertThat(testCourtCase.getChequeNo()).isEqualTo(UPDATED_CHEQUE_NO);
        assertThat(testCourtCase.getChequeDate()).isEqualTo(UPDATED_CHEQUE_DATE);
        assertThat(testCourtCase.getAppealNo()).isEqualTo(UPDATED_APPEAL_NO);
        assertThat(testCourtCase.getCourtAmount()).isEqualTo(UPDATED_COURT_AMOUNT);
        assertThat(testCourtCase.getAppealAmount()).isEqualTo(UPDATED_APPEAL_AMOUNT);
        assertThat(testCourtCase.getAppealDate()).isEqualTo(UPDATED_APPEAL_DATE);
        assertThat(testCourtCase.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testCourtCase.getComment()).isEqualTo(UPDATED_COMMENT);
        assertThat(testCourtCase.getCaseStatus()).isEqualTo(UPDATED_CASE_STATUS);
        assertThat(testCourtCase.getFreefield1()).isEqualTo(UPDATED_FREEFIELD_1);
        assertThat(testCourtCase.getFreefield2()).isEqualTo(UPDATED_FREEFIELD_2);
        assertThat(testCourtCase.getFreefield3()).isEqualTo(UPDATED_FREEFIELD_3);
        assertThat(testCourtCase.getLastModifiedBy()).isEqualTo(UPDATED_LAST_MODIFIED_BY);
        assertThat(testCourtCase.getLastModified()).isEqualTo(UPDATED_LAST_MODIFIED);
    }

    @Test
    @Transactional
    void patchNonExistingCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, courtCaseDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCourtCase() throws Exception {
        int databaseSizeBeforeUpdate = courtCaseRepository.findAll().size();
        courtCase.setId(count.incrementAndGet());

        // Create the CourtCase
        CourtCaseDTO courtCaseDTO = courtCaseMapper.toDto(courtCase);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCourtCaseMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(courtCaseDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CourtCase in the database
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCourtCase() throws Exception {
        // Initialize the database
        courtCaseRepository.saveAndFlush(courtCase);

        int databaseSizeBeforeDelete = courtCaseRepository.findAll().size();

        // Delete the courtCase
        restCourtCaseMockMvc
            .perform(delete(ENTITY_API_URL_ID, courtCase.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CourtCase> courtCaseList = courtCaseRepository.findAll();
        assertThat(courtCaseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
