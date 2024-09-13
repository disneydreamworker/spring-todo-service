package com.ssg.springtodoservice.controller;

import com.ssg.springtodoservice.dto.PageRequestDTO;
import com.ssg.springtodoservice.dto.TodoDTO;
import com.ssg.springtodoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {
    //TodoService 인젝션 받기
    private final TodoService todoService;

    //////////////////////////////////////////////////////////////////////////
    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
    //    @RequestMapping("/list")
    //    public void list(Model model){
    //        log.info("list ~~~~");
    //    }

        log.info("pageRequestDTO");
        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }


    @GetMapping("/register")
    public void registerGet() {
        log.info("register get ~~~");
    }


    //옛날에는 모든 파라미터를 getParameter로 받아서 DTO 객체를 만들어줘야 했는데, 이제는 스프링이 알아서 만들어줘! -> 자동 파라미터 수집
    //그리고 RedirectAttributes를 사용하고 문자열로 "redirect:/" 로 리턴하면 해당 페이지로 보내줘!
    //@Valid 를 사용해서 파라미터 수집 시 서버 사이드 검증을 하도록 하자! (라이브러리 build.gradle에 있어!)
    //BindingResult :
    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("POSt register");
        //에러가 난다면 문제가 있었던 애들을 "error"라는 이름으로 담아서 잠깐 저장해줄게~
        if(bindingResult.hasErrors()) {
            log.info("has error...");
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }
        log.info("todoDTO" + todoDTO);
        todoService.register(todoDTO);
        return "redirect:/todo/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Long tno, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto",todoDTO);

    }

    @PostMapping("/remove")
    public String remove(Long tno , RedirectAttributes redirectAttributes){
        log.info("remove ...........");
        log.info("tno =  " + tno );
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, BindingResult bindingResult,RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("has...error....");
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno",todoDTO.getTno());
            return "redirect:/todo/modify";
        }
        log.info("todoDTO " + todoDTO );
        todoService.modify(todoDTO);
        return "redirect:/todo/list";
    }
}
