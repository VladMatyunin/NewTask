package repo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import repo.entity.MyImage;

/**
 * Created by Vlad.M on 18.08.2016.
 */
@Repository
public interface ImageRepository extends CrudRepository<MyImage,Integer>{
}
