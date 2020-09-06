package com.edm.camera.service.impl;

import com.edm.camera.dto.LockerDTO;
import com.edm.camera.request.ListLockerRequest;
import com.edm.camera.request.LockerRegisterRequest;
import com.edm.camera.service.ILockerService;
import com.edm.camera.utils.IpUtils;
import com.edm.camera.utils.RedisUtils;
import com.edm.camera.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Edm
 * @create 2020-08-05 22:04
 * @email edisonmiao@icloud.com
 */
@Service
public class LockerServiceImpl implements ILockerService {
    @Autowired
    private RedisUtils redisUtils;


    @Override
    synchronized public void registerLocker(HttpServletRequest request, LockerRegisterRequest registerRequest) {
        String ipAddr = IpUtils.getIpAddr(request);
        redisUtils.hset("locker:" + registerRequest.getUniCode(), "status", registerRequest.getStatus(), 30);
        redisUtils.hset("locker:" + registerRequest.getUniCode(), "ip", ipAddr, 30);
    }

    @Override
    public List<LockerDTO> listLocker(ListLockerRequest request) {
        //总keys
        Set<String> keys = redisUtils.keys("locker:*");
        String requestIp = request.getIp();
        String requestDoorStatus = request.getDoorStatus();
        String requestLockerStatus = request.getLockerStatus();
        List<LockerDTO> list = new ArrayList<>();
        if (StringUtils.isNotEmpty(requestIp)) {
            keys = keys.stream().filter(v->requestIp.equals(v.split(":")[1])).collect(Collectors.toSet());
        }
        for (String key : keys) {
            String id = key.split(":")[1];
            LockerDTO dto =new LockerDTO();
            dto.setIp((String)redisUtils.hget("locker:"+id, "ip"));
            dto.setElectricity((String)redisUtils.hget("locker:"+id, "electricity"));
            dto.setLockStatus((String)redisUtils.hget("locker:"+id, "lockStatus"));
            dto.setDoorStatus((String)redisUtils.hget("locker:"+id, "doorStatus"));
            list.add(dto);
        }
        if (StringUtils.isNotEmpty(requestDoorStatus)) {
            list = list.stream().filter(v->v.getDoorStatus().equals(requestDoorStatus)).collect(Collectors.toList());
        }
        if (StringUtils.isNotEmpty(requestLockerStatus)) {
            list = list.stream().filter(v->v.getLockStatus().equals(requestLockerStatus)).collect(Collectors.toList());
        }
        return list;
    }
}
