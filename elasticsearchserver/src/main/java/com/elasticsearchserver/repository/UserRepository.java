package com.elasticsearchserver.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;
import com.elasticsearchserver.model.UserModel;

public interface UserRepository extends ElasticsearchRepository<UserModel, Integer>{
	List<UserModel> findByUserName(String userName);

    List<UserModel> findById(int id);
}
