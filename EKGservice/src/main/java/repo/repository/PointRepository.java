package repo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import repo.entity.MyPoint;

/**
 * Created by Vlad.M on 18.08.2016.
 */
@Repository
public interface PointRepository extends CrudRepository<MyPoint,Integer>{
}
