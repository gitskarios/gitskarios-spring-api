package com.alorma.domain;

public class SimplePage<K> {
    private Integer nextPage;
    private K k;

    public SimplePage(Integer nextPage, K k) {
        this.nextPage = nextPage;
        this.k = k;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public K getK() {
        return k;
    }

    public void setK(K k) {
        this.k = k;
    }
}
