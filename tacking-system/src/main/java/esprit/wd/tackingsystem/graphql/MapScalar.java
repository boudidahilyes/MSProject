package esprit.wd.tackingsystem.graphql;

import graphql.schema.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.Value;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;


@Configuration
public class MapScalar {

    public static final GraphQLScalarType MAP_SCALAR = GraphQLScalarType.newScalar()
            .name("MapScalar")
            .description("A custom scalar that handles Map<String, Object>")
            .coercing(new Coercing<Map<String, Object>, Map<String, Object>>() {

                @Override
                public Map<String, Object> serialize(Object dataFetcherResult) throws CoercingSerializeException {
                    if (dataFetcherResult instanceof Map) {
                        return (Map<String, Object>) dataFetcherResult;
                    }
                    throw new CoercingSerializeException("Expected a Map");
                }

                @Override
                public Map<String, Object> parseValue(Object input) throws CoercingParseValueException {
                    if (input instanceof Map) {
                        return (Map<String, Object>) input;
                    }
                    throw new CoercingParseValueException("Expected a Map");
                }

                @Override
                public Map<String, Object> parseLiteral(Value input, CoercedVariables variables, GraphQLContext graphQLContext, Locale locale) throws CoercingParseLiteralException {
                    if (input instanceof graphql.language.ObjectValue) {
                        Map<String, Object> result = new LinkedHashMap<>();
                        ((graphql.language.ObjectValue) input).getObjectFields()
                                .forEach(field -> result.put(field.getName(), field.getValue()));
                        return result;
                    }
                    throw new CoercingParseLiteralException("Expected ObjectValue");
                }
            })
            .build();
}