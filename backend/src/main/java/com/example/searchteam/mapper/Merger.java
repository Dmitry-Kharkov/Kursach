package com.example.searchteam.mapper;

public interface Merger <T,S> {

    T merge (T target,S source);

}
