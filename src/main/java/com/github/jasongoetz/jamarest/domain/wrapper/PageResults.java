package com.github.jasongoetz.jamarest.domain.wrapper;

import java.util.List;

public class PageResults<T> {
    private List<T> results;

    private PageInfo pageInfo;

    public PageResults(List<T> results, PageInfo pageInfo) {
        this.results = results;
        this.pageInfo = pageInfo;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }
}
