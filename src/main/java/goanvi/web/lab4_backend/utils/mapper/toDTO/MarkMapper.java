package goanvi.web.lab4_backend.utils.mapper.toDTO;

import goanvi.web.lab4_backend.dto.MarkDTO;
import goanvi.web.lab4_backend.entity.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MarkMapper {
    MarkMapper INSTANCE = Mappers.getMapper(MarkMapper.class);

    MarkDTO toDTO(Mark mark);
}
