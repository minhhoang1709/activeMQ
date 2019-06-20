package com.elasticsearchserver.loader;

import com.elasticsearchserver.model.UserModel;
import com.elasticsearchserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    @Transactional
    public void loadAll(){

        operations.putMapping(UserModel.class);
        System.out.println("Loading Data");
        userRepository.save(getData());
        System.out.printf("Loading Completed");

    }

    private List<UserModel> getData() {
        List<UserModel> userses = new ArrayList<>();
        userses.add(new UserModel(1, "Hoang"));
        userses.add(new UserModel(6, "MahWaif"));
       
        return userses;
    }
}
