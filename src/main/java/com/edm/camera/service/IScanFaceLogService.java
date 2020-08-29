package com.edm.camera.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.edm.camera.dto.FaceLogDTO;
import com.edm.camera.dto.FaceLogMQ;
import com.edm.camera.entity.ScanFaceLog;
import com.edm.camera.request.FaceLogRequest;

/**
 * <p>
 * 扫描人脸日志表 服务类
 * </p>
 *
 * @author Edm
 * @since 2020-08-27
 */
public interface IScanFaceLogService extends IService<ScanFaceLog> {

    void saveFaceLogData(FaceLogMQ data);

    IPage<FaceLogDTO> listPageFaceLog(FaceLogRequest request);
}
