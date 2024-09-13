package com.ssg.springtodoservice.mapper;

import com.ssg.springtodoservice.domain.TodoVO;
import com.ssg.springtodoservice.dto.PageRequestDTO;

import java.util.List;

public interface TodoMapper {
    //현재 시각을 알 수 있는 기능을 만들자~ 실제 쿼리문은 분리해서 인터페이스의 이름과 동일한 xml 파일로 구분할 거야.
    String getTime();

    void insert(TodoVO todovo);

    List<TodoVO> selectAll();

    TodoVO selectOne(Long tno);

    void delete(Long tno);

    void update(TodoVO todoVO);

    List<TodoVO> selectList(PageRequestDTO pageRequestDTO);

    int getCount(PageRequestDTO pageRequestDTO);
}
