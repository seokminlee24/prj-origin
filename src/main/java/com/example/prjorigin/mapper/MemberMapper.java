package com.example.prjorigin.mapper;

import com.example.prjorigin.dto.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO jsp2.member
            (id,password, nick_name, description)
            VALUES (#{id},#{password}, #{nickName}, #{description})
            """)
    int insert(Member member);
}
