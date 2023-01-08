package goanvi.web.weblab3.repository;

import goanvi.web.weblab3.entity.Mark;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Long> {
    List<Mark> findAllByUser_Id(@NonNull Long id, Pageable pageable);
    List<Mark> findAllByUser_Id(@NonNull Long id);
    long deleteAllByUser_Id(@NonNull Long id);
    boolean existsByIdAndUser_Id(@NonNull Long id, @NonNull Long userId);

}
