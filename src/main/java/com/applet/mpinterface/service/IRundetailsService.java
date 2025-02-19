package com.applet.mpinterface.service;

import com.applet.mpinterface.domain.pojo.Rundetails;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author nana
 * @since 2024-01-25
 */
public interface IRundetailsService extends IService<Rundetails> {
    public Rundetails getLatestRecordById(String id);
    public List<Rundetails> getLatestRecordsById(String id);

}
