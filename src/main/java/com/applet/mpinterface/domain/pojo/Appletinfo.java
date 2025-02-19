package com.applet.mpinterface.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("appletinfo")
public class Appletinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String appletName;

    private Integer actionsDelay;

    private String speed;

    private String channelId;

    private String channelName;

    private String moduleName;

    private String permissionName;

    private String description;

    private Stage stage;


}
