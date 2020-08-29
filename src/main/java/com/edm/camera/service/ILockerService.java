package com.edm.camera.service;

import com.edm.camera.dto.LockerDTO;
import com.edm.camera.request.ListLockerRequest;
import com.edm.camera.request.LockerRegisterRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Edm
 * @create 2020-08-05 22:04
 * @email edisonmiao@icloud.com
 */
public interface ILockerService {

    void registerLocker(HttpServletRequest request, LockerRegisterRequest registerRequest);

    List<LockerDTO> listLocker(ListLockerRequest request);
}
