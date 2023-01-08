package goanvi.web.weblab3.service;

import goanvi.web.weblab3.dto.MarkDTO;
import goanvi.web.weblab3.entity.Mark;
import goanvi.web.weblab3.entity.User;
import goanvi.web.weblab3.exceptions.IdentificationException;
import goanvi.web.weblab3.repository.MarkRepository;
import goanvi.web.weblab3.repository.UserRepository;
import goanvi.web.weblab3.security.CustomUserDetails;
import goanvi.web.weblab3.security.jwt.JwtAuthToken;
import goanvi.web.weblab3.utils.HitMark;
import goanvi.web.weblab3.utils.mapper.toDTO.MarkMapper;
import goanvi.web.weblab3.utils.mapper.toEntity.MarkDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final UserRepository userRepository;

    @Autowired
    public MarkService(MarkRepository markRepository, UserRepository userRepository) {
        this.markRepository = markRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Mark saveMark(MarkDTO markDTO, JwtAuthToken authToken) throws IdentificationException {
        Mark mark = MarkDTOMapper.INSTANCE.toEntity(markDTO);
        CustomUserDetails userDetails = (CustomUserDetails) authToken.getDetails();
        Long id = userDetails.getUserId();
        Optional<User> user = userRepository.findById(id);
        mark.setHit(HitMark.hitMark(
                mark.getXValue(),
                mark.getYValue(),
                mark.getRValue())?"hit":"miss");
        mark.setTime(LocalDateTime.now());
        user.ifPresent(mark::setUser);
        mark.setLeadTime(System.nanoTime()-mark.getLeadTime());
        return markRepository.save(mark);
    }

    @Transactional
    public void deleteMarkById(Long markId, JwtAuthToken authToken) throws IdentificationException {
        CustomUserDetails userDetails = (CustomUserDetails) authToken.getDetails();
        Long id = userDetails.getUserId();
        boolean exist = markRepository.existsByIdAndUser_Id(markId, id);
        if (!exist) {
            throw new IdentificationException(" mark with id " + markId + " does not exist");
        }
        markRepository.deleteById(id);
    }

    @Transactional
    public List<MarkDTO> findAllMarks(JwtAuthToken authToken) {
        CustomUserDetails userDetails = (CustomUserDetails) authToken.getDetails();
        Long id = userDetails.getUserId();
        List<Mark> marks = markRepository.findAllByUser_Id(id);
        return marks.stream().map(MarkMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @Transactional
    public Long deleteAllMark(JwtAuthToken authToken) {
        CustomUserDetails userDetails = (CustomUserDetails) authToken.getDetails();
        Long id = userDetails.getUserId();
        return markRepository.deleteAllByUser_Id(id);
    }

    @Transactional
    public MarkDTO updateById(Long markId, MarkDTO markDTO, JwtAuthToken authToken) throws IdentificationException {
        CustomUserDetails userDetails = (CustomUserDetails) authToken.getDetails();
        Long id = userDetails.getUserId();
        if (!markRepository.existsByIdAndUser_Id(markId, id)){
            throw new IdentificationException("mark with id " + markId + " does not exist");
        }
        Optional<Mark> obj = markRepository.findById(markId);
        if (obj.isPresent()){
            Mark mark = obj.get();
            mark.setRValue(markDTO.getRValue());
            mark.setXValue(markDTO.getXValue());
            mark.setYValue(markDTO.getYValue());
            Mark newMark = markRepository.save(mark);
            return MarkMapper.INSTANCE.toDTO(newMark);
        }else throw new IdentificationException("mark with id " + markId + " does not exist");
    }

    @Transactional
    public List<MarkDTO> findAllInPage(Pageable page, JwtAuthToken authToken){
        CustomUserDetails userDetails = (CustomUserDetails) authToken.getDetails();
        Long id = userDetails.getUserId();
        List<Mark> marks = markRepository.findAllByUser_Id(id, page);
        return marks.stream().map(MarkMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }
}
