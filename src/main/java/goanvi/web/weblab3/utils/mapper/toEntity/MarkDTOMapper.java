package goanvi.web.weblab3.utils.mapper.toEntity;

import goanvi.web.weblab3.dto.MarkDTO;
import goanvi.web.weblab3.entity.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MarkDTOMapper {
    MarkDTOMapper INSTANCE = Mappers.getMapper(MarkDTOMapper.class);

    Mark toEntity(MarkDTO markDTO);
}
