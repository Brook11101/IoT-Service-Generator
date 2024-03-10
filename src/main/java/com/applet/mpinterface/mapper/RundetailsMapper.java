package com.applet.mpinterface.mapper;

import com.applet.mpinterface.domain.pojo.Appletinfo;
import com.applet.mpinterface.domain.pojo.Rundetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author nana
 * @since 2024-01-25
 */
@Mapper
public interface RundetailsMapper extends BaseMapper<Rundetails> {
    Rundetails selectByIdAndLatestTime(@Param("id") Long id);
}
