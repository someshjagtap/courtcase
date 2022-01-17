package com.techvg.courtcase.service.mapper;

import com.techvg.courtcase.domain.UserData;
import com.techvg.courtcase.service.dto.UserDataDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserData} and its DTO {@link UserDataDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UserDataMapper extends EntityMapper<UserDataDTO, UserData> {}
