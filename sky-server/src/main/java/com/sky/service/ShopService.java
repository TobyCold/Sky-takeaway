package com.sky.service;

public interface ShopService {
    /**
     * 获取店铺状态
     * @return
     */
    public Integer getStatus();

    /**
     * 设置店铺状态
     * @param status
     */
    public void setStatus(Integer status);
}
