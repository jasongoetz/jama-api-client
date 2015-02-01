package com.github.jasongoetz.jamarest.domain.wrapper;

public abstract class ResponseWrapper<DataType> {

    private Meta meta;

    private DataType data;

    public DataType getData() {
        return data;
    }

    public void setData(DataType data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
