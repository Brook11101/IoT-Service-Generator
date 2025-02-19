package com.applet.mpinterface.mapper;

import com.applet.mpinterface.domain.pojo.Appletinfo;
import com.applet.mpinterface.domain.pojo.Rundetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Mapper
public interface RundetailsMapper extends BaseMapper<Rundetails> {
    Rundetails selectByIdAndLatestTime(@Param("id") Long id);
}
