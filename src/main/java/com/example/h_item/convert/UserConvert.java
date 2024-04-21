package com.example.h_item.convert;

import com.example.h_item.model.dto.UserInfoDTO;
import com.example.h_item.model.po.UserPO;

/**
 * @Date 2024/4/21
 * @Created by wangshuai
 */
public class UserConvert {


    public static UserInfoDTO poToDTO(UserPO userPO) {
        if (userPO == null) {
            return null;
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setId(userPO.getId());
        userInfoDTO.setUsername(userPO.getUsername());
        userInfoDTO.setPassword(userPO.getPassword());
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
}
