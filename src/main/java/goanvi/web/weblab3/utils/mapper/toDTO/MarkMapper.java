package goanvi.web.weblab3.utils.mapper.toDTO;

import goanvi.web.weblab3.dto.MarkDTO;
import goanvi.web.weblab3.entity.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MarkMapper {
    MarkMapper INSTANCE = Mappers.getMapper(MarkMapper.class);

    MarkDTO toDTO(Mark mark);
}
