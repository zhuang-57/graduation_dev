package com.example.h_item.convert;

import cn.hutool.core.collection.CollUtil;
import com.example.h_item.model.dto.UserInfoDTO;
import com.example.h_item.model.po.RolePO;
import com.example.h_item.model.po.UserPO;
import com.example.h_item.service.RoleService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @Date 2024/4/21
 * @Created by wangshuai
 */
@Component
public class UserConvert {

    @Resource
    private RoleService roleService;


    public UserInfoDTO poToDTO(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userPO.getId());
        userInfoDTO.setUsername(userPO.getUsername());
        userInfoDTO.setPassword(userPO.getPassword());
        userInfoDTO.setRoleId(userPO.getRoleId());
        RolePO rolePO = roleService.queryById(userPO.getRoleId());
        if (rolePO != null) {
            userInfoDTO.setRoleName(rolePO.getRolesName());
        }
        userInfoDTO.setSex(userPO.getSex());
        userInfoDTO.setBirthday(userPO.getBirthday());
        userInfoDTO.setImg(userPO.getImg());
        userInfoDTO.setAcademyId(userPO.getAcademyId());
        userInfoDTO.setEducational(userPO.getEducational());
        userInfoDTO.setMajor(userPO.getMajor());
        userInfoDTO.setPhone(userPO.getPhone());
        userInfoDTO.setRemark(userPO.getRemark());
        return userInfoDTO;
    }

    public List<UserInfoDTO> poToDTO(List<UserPO> userPOList) {
        if (CollUtil.isEmpty(userPOList)) {
            return null;
        }
        List<UserInfoDTO> result = new LinkedList<>();
        userPOList.forEach(x -> result.add(poToDTO(x)));
        return result;
    }
}
