package com.ssg.springtodoservice.domain;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
public class TodoVO {
    //데이터베이스의 todo_tbl 테이블을 보고 필드를 작성
    private int tno;
    private String title;
    private LocalDate dueDate;
    private String writer;
    private boolean finished;

}
