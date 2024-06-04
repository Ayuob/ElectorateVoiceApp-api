package ly.qubit.evp.repository;

import ly.qubit.evp.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, Long> {
    Long countByAnswerId(Long answerId);
}
