package com.applet.mpinterface.domain.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.scheduling.Trigger;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName(value = "rundetails")
public class Rundetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private Integer actionTime;

    private String actionType;

    @TableField(value = "`Trigger`")
    private String Trigger;

    private Integer triggerNum;

    private Integer triggerIngredientsNum;

    private String Filter;

    private Integer filterNum;

    private Integer filterIngredientsNum;

    private String Query;

    private Integer queryNum;

    private Integer queryIngredientsNum;

    private String Delay;

    private Integer delayNum;

    private Integer delayIngredientsNum;

    private String Action;

    private Integer actionNum;

    private Integer actionIngredientsNum;


}
