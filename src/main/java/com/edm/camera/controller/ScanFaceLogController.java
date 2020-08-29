package com.edm.camera.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.edm.camera.dto.FaceLogDTO;
import com.edm.camera.request.FaceLogRequest;
import com.edm.camera.response.ApiResult;
import com.edm.camera.service.IScanFaceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 扫描人脸日志表 前端控制器
 * </p>
 *
 * @author Edm
 * @since 2020-08-05
 */
@RestController
@RequestMapping("/scanFaceLog")
public class ScanFaceLogController {
    @Autowired
    private IScanFaceLogService scanFaceLogService;


    @PostMapping("/listPageFaceLog")
    public ApiResult<IPage<FaceLogDTO>> listFaceLog(@RequestBody FaceLogRequest request) {
        return ApiResult.ok(scanFaceLogService.listPageFaceLog(request));
    }
}
