package com.journaldev.elasticsearch.dao;

import org.springframework.stereotype.Repository;

import com.journaldev.elasticsearch.bean.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class UserDao {
	private final String INDEX = "user_data";
    private final String TYPE = "user";

    private RestHighLevelClient restHighLevelClient;

    private ObjectMapper objectMapper;
    
    public UserDao( ObjectMapper objectMapper, RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }
    
    public Map<String, Object> getUserByName(String name){
        GetRequest getRequest = new GetRequest(INDEX, TYPE, name);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }
    
    public Map<String, Object> getUserById(String id){
        GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getRequest);
        } catch (java.io.IOException e){
            e.getLocalizedMessage();
        }
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        return sourceAsMap;
    }
    
//    public List<User> searchByName(String name) throws Exception {
//
//        SearchRequest searchRequest = new SearchRequest();
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//
//        QueryBuilder queryBuilder = QueryBuilders
//                .boolQuery()
//                .must(QueryBuilders
//                        .matchQuery("name", name));
//
//        searchSourceBuilder.query(QueryBuilders
//                .nestedQuery("name",
//                        queryBuilder,
//                        ScoreMode.Avg));
//
//        searchRequest.source(searchSourceBuilder);
//
//        SearchResponse response =
//        		restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
//
//        return getSearchResult(response);
//    }
    
    private List<User> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<User> userDocuments = new ArrayList<>();

        for (SearchHit hit : searchHit){
        	userDocuments
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), User.class));
        }

        return userDocuments;
    }
    
    public List<User> findAll() throws Exception {


        SearchRequest searchRequest = buildSearchRequest(INDEX,TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
        		restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }
    
    private SearchRequest buildSearchRequest(String index, String type) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        return searchRequest;
    }
    
    public List<User> findByName(String name) throws Exception{


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.types(TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                .matchQuery("name",name)
                .operator(Operator.OR);

        searchSourceBuilder.query(matchQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
        		restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);

    }
    
    public List<User> findByAbout(String about) throws Exception{


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.types(TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                .matchQuery("about",about)
                .operator(Operator.AND);

        searchSourceBuilder.query(matchQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
        		restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);

    }
}
