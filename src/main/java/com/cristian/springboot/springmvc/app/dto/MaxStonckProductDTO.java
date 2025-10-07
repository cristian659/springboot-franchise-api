package com.cristian.springboot.springmvc.app.dto;

public class MaxStonckProductDTO {
    private String brachName;
    private String pruductName;
    private Integer stock;

    public MaxStonckProductDTO(String brachName, String pruductName, Integer stock) {
        this.brachName = brachName;
        this.pruductName = pruductName;
        this.stock = stock;
    }

    public String getBrachName() {
        return brachName;
    }

    public void setBrachName(String brachName) {
        this.brachName = brachName;
    }

    public String getPruductName() {
        return pruductName;
    }

    public void setPruductName(String pruductName) {
        this.pruductName = pruductName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
