package com.segovelo.api.graphqlexample.controller;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.GraphQLException;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.segovelo.api.graphqlexample.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class GraphQLController {
    private final GraphQL graphQL;

    @Autowired
    public GraphQLController(BookService bookResolver) {
        GraphQLSchema schema = new GraphQLSchemaGenerator().withBasePackages("com.segovelo")
            .withOperationsFromSingleton(bookResolver)
            .generate();
        this.graphQL = new GraphQL.Builder(schema).build();
    }

    @PostMapping(value = "/graphql")
    public Map<String, Object> execute(@RequestBody Map<String, String> request, HttpServletRequest raw) throws GraphQLException {
        ExecutionResult result = graphQL.execute(request.get("query"));
        return result.getData();
    }


}
