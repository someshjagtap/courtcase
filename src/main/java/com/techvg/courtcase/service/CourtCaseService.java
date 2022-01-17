package com.techvg.courtcase.service;

import com.techvg.courtcase.service.dto.CourtCaseDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.techvg.courtcase.domain.CourtCase}.
 */
public interface CourtCaseService {
    /**
     * Save a courtCase.
     *
     * @param courtCaseDTO the entity to save.
     * @return the persisted entity.
     */
    CourtCaseDTO save(CourtCaseDTO courtCaseDTO);

    /**
     * Partially updates a courtCase.
     *
     * @param courtCaseDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CourtCaseDTO> partialUpdate(CourtCaseDTO courtCaseDTO);

    /**
     * Get all the courtCases.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CourtCaseDTO> findAll(Pageable pageable);

    /**
     * Get the "id" courtCase.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CourtCaseDTO> findOne(Long id);

    /**
     * Delete the "id" courtCase.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
