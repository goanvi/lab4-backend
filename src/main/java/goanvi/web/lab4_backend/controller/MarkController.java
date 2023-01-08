package goanvi.web.lab4_backend.controller;

import goanvi.web.lab4_backend.dto.MarkDTO;
import goanvi.web.lab4_backend.exceptions.IdentificationException;
import goanvi.web.lab4_backend.security.jwt.JwtAuthToken;
import goanvi.web.lab4_backend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mark")
public class MarkController {
    MarkService markService;
    @Autowired
    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping
    public Long saveMark(@Validated @RequestBody MarkDTO markDTO,
                         JwtAuthToken authToken)throws IdentificationException {
        markDTO.setLeadTime(System.nanoTime());
        return markService.saveMark(markDTO, authToken).getId();
}

    @GetMapping
    public ResponseEntity<List<MarkDTO>> findAllMark(JwtAuthToken authToken){
        return ResponseEntity.status(HttpStatus.OK)
                .body(markService.findAllMarks(authToken));
    }

    @GetMapping("/page")
    public ResponseEntity<List<MarkDTO>> findPageMark(@PageableDefault(value = 2, page = 0) Pageable pageable,
                                                      JwtAuthToken authToken){
        return ResponseEntity.ok(markService.findAllInPage(pageable, authToken));

    }


    @DeleteMapping(path = "{markId}")
    public ResponseEntity<?> deleteMarkById (@PathVariable("markId") Long markId,
                                             JwtAuthToken authToken) throws IdentificationException {
        markService.deleteMarkById(markId, authToken);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteAllMark(JwtAuthToken authToken){
        return ResponseEntity.ok(markService.deleteAllMark(authToken));
    }

    @PutMapping(path="{markId}")
    public ResponseEntity<MarkDTO> updateById (@PathVariable("markId") Long markId,
                                               @Validated @RequestBody MarkDTO markDTO,
                                               JwtAuthToken authToken) throws IdentificationException {
        MarkDTO newMarkDTO = markService.updateById(markId,markDTO, authToken);
        return ResponseEntity.ok(newMarkDTO);

    }

}
