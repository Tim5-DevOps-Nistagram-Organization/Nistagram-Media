package rs.ac.uns.ftn.devops.tim5.nistagrammedia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.devops.tim5.nistagrammedia.model.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {
}
