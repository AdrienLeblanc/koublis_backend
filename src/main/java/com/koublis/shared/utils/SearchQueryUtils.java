package com.koublis.shared.utils;

import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.Arrays;
import java.util.Optional;

import static java.util.Objects.isNull;

@UtilityClass
public class SearchQueryUtils {

    public static String contains(String propertyName, String propertyValue) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return propertyName + ":*" + propertyValue + "*";
    }

    public static String isEquelTo(String propertyName, String propertyValue) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return propertyName + ":" + propertyValue;
    }

    public static String not(String clause) {
        return Optional.of(clause)
                .map(q -> "NOT (" + q + ")")
                .orElse(clause);
    }

    public static String startsWith(String propertyName, String propertyValue) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return propertyName + ":" + propertyValue + "*";
    }

    public static String endsWith(String propertyName, String propertyValue) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return propertyName + ":*" + propertyValue;
    }

    public static String fuzzySearch(String propertyName, String propertyValue) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return propertyName + ":" + propertyValue + "~";
    }

    public static String fuzzySearchWithDistance(String propertyName, String propertyValue, int distance) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return propertyName + ":" + propertyValue + "~" + distance;
    }

    public static String containsFuzzy(String propertyName, String propertyValue) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return "(" + propertyName + ":" + propertyValue + "* OR " + propertyName + ":" + propertyValue + "~)";
    }

    public static String containsFuzzy(String propertyName, String propertyValue, int fuzzyDistance) {
        if (isNull(propertyName) || isNull(propertyValue)) {
            return "";
        }

        return "(" + propertyName + ":" + propertyValue + "* OR " + propertyName + ":" + propertyValue + "~" + fuzzyDistance + ")";
    }

    public static String add(String clause, Operator operator) {
        val operatorAlias = Optional.ofNullable(operator.aliases())
                .map(Arrays::stream)
                .stream()
                .flatMap(s -> s)
                .findFirst()
                .orElseThrow();
        return Optional.of(clause)
                .map(q -> " " + operatorAlias + " " + clause)
                .orElse(clause);
    }
}
