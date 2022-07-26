package com.example.demo.structs;


import com.example.demo.config.StructConfig;
import com.example.demo.entity.TestEntity;
import com.example.demo.request.TestReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author cx
 */
@Mapper(config = StructConfig.class)
public interface TestMap {

    TestMap TEST_MAPPER = Mappers.getMapper(TestMap.class);

    TestEntity toEntity(TestReq req);

}
