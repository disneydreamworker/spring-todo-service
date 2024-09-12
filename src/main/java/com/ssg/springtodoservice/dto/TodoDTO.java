package com.ssg.springtodoservice.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
    //스프링이 자동 파라미터 수집을 할 때 컨트롤러가 검증 작업을 하는데, 이때 필요한게 @Valid야.
    //@Valid와 @BindingResult 객체를 이용해서 처리해.
    //위 어노테이션을 사용할려면 hibernate-validate 라이브러리가 필요해.
    private Long tno;
    @NotEmpty
    private String title;
    @Future
    private LocalDate dueDate;
    private boolean finished;
    @NotEmpty
    private String writer;

}