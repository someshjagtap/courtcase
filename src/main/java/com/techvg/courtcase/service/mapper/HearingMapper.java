package com.techvg.courtcase.service.mapper;

import com.techvg.courtcase.domain.Hearing;
import com.techvg.courtcase.service.dto.HearingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Hearing} and its DTO {@link HearingDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HearingMapper extends EntityMapper<HearingDTO, Hearing> {}
