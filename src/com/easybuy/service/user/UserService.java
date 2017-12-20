package com.easybuy.service.user;

import com.easybuy.entity.User;

public interface  UserService {
     User getUserByLoginName(String loginName);
     /**
      * 用户注册
      * @param user
      * @return
      */
     boolean save(User user);
}
