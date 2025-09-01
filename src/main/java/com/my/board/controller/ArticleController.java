package com.my.board.controller;

import com.my.board.dto.ArticleDto;
import com.my.board.service.ArticleService;
import com.my.board.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;
    @GetMapping({"", "/"})
    public String showArticles(Model model,
                               @PageableDefault(
                                       page = 1,
                                       size = 5,
                                       direction = Sort.Direction.ASC
                               )Pageable pageable,
                               RedirectAttributes redirectAttributes) {
//        redirectAttributes.addFlashAttribute("msg",null);
        Page<ArticleDto> articles = articleService.getArticlePage(pageable);
        //전체 페이지
        int totalPage = articles.getTotalPages();
        System.out.println("TotalPage : " + totalPage);
        //현재 페이지 번호
        int currentPage = articles.getNumber();
        System.out.println("currentPage : " + currentPage);
        //3. PaginationServie에서 페이지 블럭을 얻어온다.
        List<Integer> barNumbers = paginationService.getPagenationBarNumber(currentPage,totalPage);
        System.out.println(barNumbers);
        model.addAttribute("pageBars",barNumbers);
        model.addAttribute("articles",articles);
        return "/articles/show_all";
    }
    @GetMapping("/{id}")
    private String showOneArticle(@PathVariable("id")Long id,
                                  Model model){
        ArticleDto dto = articleService.getOneArticle(id);
        model.addAttribute("dto",dto);
        return "/articles/show";
    }

    @GetMapping("/new")
    private String createNewArticle(Model model){
        model.addAttribute("dto",new ArticleDto());
        return "/articles/new";
    }

    @GetMapping("/{id}/delete")
    public String deleteArticle(@PathVariable("id")Long id,
                                RedirectAttributes redirectAttributes){
        articleService.deleteArticle(id);
        redirectAttributes.addFlashAttribute("msg","정상적으로 삭제되었습니다.");
        return "redirect:/articles";
    }

    @PostMapping("create")
    public String createArticle(ArticleDto dto,
                                RedirectAttributes redirectAttributes) {
        articleService.insertArticle(dto);
        redirectAttributes.addFlashAttribute("msg","새로운 게시글이 등록 되었습니다.");
        return "redirect:/articles";
    }

    @GetMapping("/{id}/update")
    public String updateArticle(@PathVariable("id")Long id,
                                Model model){
        ArticleDto articleDto = articleService.getOneArticle(id);
        model.addAttribute("dto", articleDto);
        return "/articles/update";
    }

    @PostMapping("update")
    public String updateArticle(@ModelAttribute("dto")ArticleDto dto,
                                RedirectAttributes redirectAttributes) {
        articleService.updateArticle(dto);
        redirectAttributes.addFlashAttribute("msg","기존 게시글이 정상적으로 수정되었습니다.");
        return "redirect:/articles";
    }
}
