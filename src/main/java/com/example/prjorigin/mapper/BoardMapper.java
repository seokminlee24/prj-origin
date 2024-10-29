package com.example.prjorigin.mapper;

import com.example.prjorigin.dto.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Insert("""
            INSERT INTO board
            (title, content, writer)
            VALUES (#{title}, #{content}, #{writer})
            """)
    int insert(Board board);

    @Select("""
                        SELECT * 
            FROM board 
            ORDER BY id DESC 
            LIMIT #{offset}, 10
            """)
    List<Board> selectAllPaging(Integer offset);

    @Select("""
            SELECT * 
            FROM board
            WHERE id = #{id}
            """)
    Board selectById(Integer id);

    @Delete("""
            DELETE FROM board
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Update("""
            UPDATE board
            SET title=#{title},
                content=#{content}, 
                writer=#{writer}
            WHERE   
                id = #{id}
            """)
    int update(Board board);

    @Select("""
            SELECT COUNT(id)
            FROM board
            """)
    Integer countAll();
}
