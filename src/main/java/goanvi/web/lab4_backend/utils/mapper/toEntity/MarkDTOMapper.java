package goanvi.web.lab4_backend.utils.mapper.toEntity;

import goanvi.web.lab4_backend.dto.MarkDTO;
import goanvi.web.lab4_backend.entity.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MarkDTOMapper {
    MarkDTOMapper INSTANCE = Mappers.getMapper(MarkDTOMapper.class);

    Mark toEntity(MarkDTO markDTO);
}
