package org.spring.springboot.repository;

import org.spring.springboot.domain.City;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * 接口只要继承 ElasticsearchRepository 类即可。默认会提供很多实现，比如 CRUD 和搜索相关的实现。
 *
 * Created by bysocket on 17/05/2017.
 */
@Repository
public interface CityRepository extends ElasticsearchRepository<City,Long> {


}
