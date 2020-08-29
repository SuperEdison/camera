package com.edm.camera.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edm.camera.dto.FaceLogDTO;
import com.edm.camera.dto.FaceLogMQ;
import com.edm.camera.dto.SimilarFaceDTO;
import com.edm.camera.entity.ScanFaceLog;
import com.edm.camera.entity.ScanSimilarFaceLog;
import com.edm.camera.mapper.ScanFaceLogMapper;
import com.edm.camera.request.FaceLogRequest;
import com.edm.camera.service.IScanFaceLogService;
import com.edm.camera.service.IScanSimilarFaceLogService;
import com.edm.camera.utils.BeanCopierUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 扫描人脸日志表 服务实现类
 * </p>
 *
 * @author Edm
 * @since 2020-08-27
 */
@Service
public class ScanFaceLogServiceImpl extends ServiceImpl<ScanFaceLogMapper, ScanFaceLog> implements IScanFaceLogService {
    @Autowired
    private IScanSimilarFaceLogService scanSimilarFaceLogService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFaceLogData(FaceLogMQ data) {
        ScanFaceLog log = new ScanFaceLog();
        BeanCopierUtils.X.copy(data, log);
        log.setBeginTime(LocalDateTime.ofEpochSecond(Long.parseLong(data.getBeginTime()),0, ZoneOffset.ofHours(8)));
        log.setEndTime(LocalDateTime.ofEpochSecond(Long.parseLong(data.getEndTime()),0, ZoneOffset.ofHours(8)));
        save(log);
        List<SimilarFaceDTO> similarFaces = data.getSimilarFaces();
        List<ScanSimilarFaceLog> list = new ArrayList<>();
        similarFaces.forEach(v->{
            ScanSimilarFaceLog similarFaceLog = new ScanSimilarFaceLog();
            BeanCopierUtils.X.copy(v, similarFaceLog);
            similarFaceLog.setScanFaceLogId(log.getId());
            list.add(similarFaceLog);
        });
        scanSimilarFaceLogService.saveBatch(list);
    }

    @Override
    public IPage<FaceLogDTO> listPageFaceLog(FaceLogRequest request) {
        Page<FaceLogDTO> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.baseMapper.listPageFaceLog(page, request);
    }
}
