package com.ssg.springtodoservice.service;

import com.ssg.springtodoservice.domain.TodoVO;
import com.ssg.springtodoservice.dto.PageRequestDTO;
import com.ssg.springtodoservice.dto.PageResponseDTO;
import com.ssg.springtodoservice.dto.TodoDTO;
import com.ssg.springtodoservice.mapper.TodoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    //스프링 컨테이너가 관리해야된 빈 객체임
    //Mapper의 의존성을 주입받음??
    //@RequiredArgsConstructor : 의존성 주입을 내가 지정할게.
    //new : 직접 주입 방식
    //setter : 스프링 예전 주입 방식
    //final : final한 객체를 @RequiredArgsConstructor를 이용해서 생성자를 생성하고 주입 받는다.
    //TodoServiceImpl 클래스를 만들 때, 아래 두 클래스를 미리 만들어서 나에게 주입해줘!
    private final TodoMapper  todoMapper;
    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);
    }

    //    @Override
//    public List<TodoDTO> getAll() {
//
//           List<TodoDTO> dtoList = todoMapper.selectAll().stream()
//                   .map(vo->modelMapper.map(vo,TodoDTO.class)).collect(Collectors.toList());
//
//
//        return dtoList;
//    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO vo = todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(vo,TodoDTO.class);
        return todoDTO;
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO,TodoVO.class);
        todoMapper.update(todoVO);
    }

    @Override
    public PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO) {
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        List<TodoDTO> dtoList = voList.stream()
                .map(vo->modelMapper.map(vo,TodoDTO.class)).collect(Collectors.toList());;

        int total = todoMapper.getCount(pageRequestDTO);

        PageResponseDTO<TodoDTO> pageResponseDTO = PageResponseDTO.<TodoDTO>withAll().dtoList(dtoList)
                .total(total).pageRequestDTO(pageRequestDTO).build();
        return pageResponseDTO;
    }
}
