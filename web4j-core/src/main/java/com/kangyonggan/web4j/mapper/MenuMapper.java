package com.kangyonggan.web4j.mapper;

import com.kangyonggan.web4j.model.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper extends MyMapper<Menu> {

    /**
     * 查找用户某类型的菜单
     *
     * @param username
     * @param type     admin|dashboard|null
     * @return
     */
    List<Menu> selectMenusByUsernameAndType(@Param("username") String username, @Param("type") String type);

    /**
     * 查找角色菜单
     *
     * @param code
     * @param type
     * @return
     */
    List<Menu> selectMenus4Role(@Param("code") String code, @Param("type") String type);
}