package com.techvg.courtcase.service.impl;

import com.techvg.courtcase.domain.UserData;
import com.techvg.courtcase.repository.UserDataRepository;
import com.techvg.courtcase.service.UserDataService;
import com.techvg.courtcase.service.dto.UserDataDTO;
import com.techvg.courtcase.service.mapper.UserDataMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link UserData}.
 */
@Service
@Transactional
public class UserDataServiceImpl implements UserDataService {

    private final Logger log = LoggerFactory.getLogger(UserDataServiceImpl.class);

    private final UserDataRepository userDataRepository;

    private final UserDataMapper userDataMapper;

    public UserDataServiceImpl(UserDataRepository userDataRepository, UserDataMapper userDataMapper) {
        this.userDataRepository = userDataRepository;
        this.userDataMapper = userDataMapper;
    }

    @Override
    public UserDataDTO save(UserDataDTO userDataDTO) {
        log.debug("Request to save UserData : {}", userDataDTO);
        UserData userData = userDataMapper.toEntity(userDataDTO);
        userData = userDataRepository.save(userData);
        return userDataMapper.toDto(userData);
    }

    @Override
    public Optional<UserDataDTO> partialUpdate(UserDataDTO userDataDTO) {
        log.debug("Request to partially update UserData : {}", userDataDTO);

        return userDataRepository
            .findById(userDataDTO.getId())
            .map(existingUserData -> {
                userDataMapper.partialUpdate(existingUserData, userDataDTO);

                return existingUserData;
            })
            .map(userDataRepository::save)
            .map(userDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDataDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UserData");
        return userDataRepository.findAll(pageable).map(userDataMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDataDTO> findOne(Long id) {
        log.debug("Request to get UserData : {}", id);
        return userDataRepository.findById(id).map(userDataMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete UserData : {}", id);
        userDataRepository.deleteById(id);
    }
}
