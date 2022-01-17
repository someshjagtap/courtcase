package com.techvg.courtcase.service;

import com.techvg.courtcase.service.dto.UserDataDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.techvg.courtcase.domain.UserData}.
 */
public interface UserDataService {
    /**
     * Save a userData.
     *
     * @param userDataDTO the entity to save.
     * @return the persisted entity.
     */
    UserDataDTO save(UserDataDTO userDataDTO);

    /**
     * Partially updates a userData.
     *
     * @param userDataDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<UserDataDTO> partialUpdate(UserDataDTO userDataDTO);

    /**
     * Get all the userData.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UserDataDTO> findAll(Pageable pageable);

    /**
     * Get the "id" userData.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDataDTO> findOne(Long id);

    /**
     * Delete the "id" userData.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
