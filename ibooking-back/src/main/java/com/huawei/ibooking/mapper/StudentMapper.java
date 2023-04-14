package com.huawei.ibooking.mapper;

import com.huawei.ibooking.model.StudentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {
    List<StudentDO> getStudents();

    List<StudentDO> getStudent(@Param("stuNum") String stuNum);

    List<StudentDO> getStudentByLoginName(@Param("loginName")String loginName);

    int saveStudent(@Param("stu") StudentDO stu);

    int deleteStudent(@Param("stuNum") String stuNum);
}
