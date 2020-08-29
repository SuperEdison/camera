package com.edm.camera.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edm.camera.dto.FaceLogDTO;
import com.edm.camera.entity.ScanFaceLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edm.camera.request.FaceLogRequest;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 扫描人脸日志表 Mapper 接口
 * </p>
 *
 * @author Edm
 * @since 2020-08-27
 */
public interface ScanFaceLogMapper extends BaseMapper<ScanFaceLog> {

    @Select({
            "<script>",
            " select l.channel_id, l.channel_name, l.face_image_url, l.picture_url, l.age, ls.sex,ls.face_image_url as faceImageLibUrl,",
            " l.begin_time, l.service_code, l.appear_times, l.recognition, l.similar, ls.phone, ls.similarity, ls.name",
            " from scan_face_log l left join scan_similar_face_log ls on l.id=ls.scan_face_log_id",
            " where l.id>0 and l.flag=0",
            " <if test='request.beginStartTime != null and request.beginStartTime.length()> 0'>and l.begin_time between #{request.beginStartTime} and #{request.beginEndTime} </if>",
            " <if test='request.similar != null'>and l.similar = #{request.similar} </if>",
            " order by l.begin_time desc",
            "</script>"
    })
    IPage<FaceLogDTO> listPageFaceLog(Page<FaceLogDTO> page, @Param("request") FaceLogRequest request);
}
