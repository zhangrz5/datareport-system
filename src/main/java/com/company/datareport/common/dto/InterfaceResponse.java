package com.company.datareport.common.dto;

import lombok.Data;

/**
 * 接口通用响应DTO
 *
 * @author system
 * @since 2025-11-25
 */
@Data
public class InterfaceResponse {

    /**
     * 服务标识: 0-失败, 1-成功, 2-无权限, 3-无新文件
     */
    private String serviceFlag;

    /**
     * 响应消息
     */
    private String msg;

    /**
     * 日志列表(用于日志下载接口)
     */
    private Object logList;

    public static InterfaceResponse success(String msg) {
        InterfaceResponse response = new InterfaceResponse();
        response.setServiceFlag("1");
        response.setMsg(msg);
        return response;
    }

    public static InterfaceResponse fail(String msg) {
        InterfaceResponse response = new InterfaceResponse();
        response.setServiceFlag("0");
        response.setMsg(msg);
        return response;
    }

    public static InterfaceResponse noPermission(String msg) {
        InterfaceResponse response = new InterfaceResponse();
        response.setServiceFlag("2");
        response.setMsg(msg);
        return response;
    }

    public static InterfaceResponse noNewFile(String msg) {
        InterfaceResponse response = new InterfaceResponse();
        response.setServiceFlag("1");
        response.setMsg(msg);
        return response;
    }

    public static InterfaceResponse withLogList(String msg, Object logList) {
        InterfaceResponse response = new InterfaceResponse();
        response.setServiceFlag("3");
        response.setMsg(msg);
        response.setLogList(logList);
        return response;
    }
}
