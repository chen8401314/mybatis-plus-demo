package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2020-11-09
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
    @Select("SELECT * FROM pf_user WHERE id = '${id}'")
    UserDTO findById(@Param("id") String id);
}
