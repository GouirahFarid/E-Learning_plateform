package com.c4u.course4you.controllers;

import com.c4u.course4you.entities.*;
import com.c4u.course4you.entities.form.CategoryForm;
import com.c4u.course4you.entities.form.CourseForm;
import com.c4u.course4you.services.FormationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@PreAuthorize("permitAll()")
@RequestMapping("/api/")
public class FormationController {
    private  final FormationService formationService;
    public FormationController(FormationService formationService) {
        this.formationService = formationService;
    }
    @PostMapping("/upload")
    public ResponseEntity<?> createResource(
             @RequestParam("file") MultipartFile file
            , @RequestParam("chapterId") Long chapterId){
        formationService.storeFile(file,chapterId,"subChapter");
        return ResponseEntity.ok("resource created");
    }

    /*********************************************************/
    /*                     Category                          */
    /*********************************************************/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping( "categories")
    public ResponseEntity<?> createCategory(@RequestBody CategoryForm categoryForm){
        return formationService.saveCategory(categoryForm);
    }
    @GetMapping("categories")
    @PreAuthorize("permitAll()")
    public  ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(formationService.getAllCategories(),HttpStatus.OK);
    }
    @PatchMapping("categories")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateCategory( @RequestParam("title") String title,@RequestParam("description") String description, @RequestParam("categoryId") Long categoryId){
         CategoryForm categoryForm=new CategoryForm();
         categoryForm.setTitle(title);
         categoryForm.setDescription(description);
        return formationService.updateCategory(categoryForm,categoryId);
    }
    @PostMapping("categories/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public  ResponseEntity<?> deleteCategory(@RequestParam("categoryId") Long categoryId){
        return  formationService.deleteCategory(categoryId);
    }
    /*********************************************************/
    /*                     Course                          */
    /*********************************************************/
    @PostMapping("courses")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> createCourse(@RequestParam(value = "title") String title,
                                          @RequestParam(value = "description") String description,
                                            @RequestParam(value = "file") MultipartFile file,
                                            @RequestParam(value = "categoriesId") Long[] categoriesId
                                            ,@RequestParam("personId") Long personId ){

      return formationService.saveCourse(title,description,categoriesId,file,personId);
    }
    @PatchMapping("courses")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> updateCourse(@RequestParam(value = "title") String title,
                                          @RequestParam(value = "description") String description,
                                          @RequestParam(value = "file") MultipartFile file,
                                          @RequestParam(value = "categoriesId") Long[] categoriesId,
                                          @RequestParam("courseId") Long courseId ){
        return  formationService.updateCourse(title,description,categoriesId,file,courseId);
    }
    @PostMapping("persons/add")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public  ResponseEntity<?> addPersonToCourse(@RequestParam("courseId") Long courseId,@RequestParam("personId") Long personId){
        System.out.println(courseId);
        System.out.println(personId);
        return formationService.addCourseToPerson(courseId,personId);
    }
    @PostMapping("persons/remove")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public  ResponseEntity<?> removePersonFromCourse(@RequestParam("courseId") Long courseId,@RequestParam("personId") Long personId){
        System.out.println("********************************hello**********************");
        return formationService.removePersonFromCourse(courseId,personId);
    }
    @PostMapping("courses/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> deleteCourse(@RequestParam("courseId") Long courseId){
        return  formationService.deleteCourse(courseId);
    }

    /*********************************************************/
    /*                     Chapter                           */
    /*********************************************************/
    @PostMapping("chapters")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> createChapter( @RequestParam("title") String title,
                                            @RequestParam("description") String description,
                                            @RequestParam("courseId") Long courseId){
        return  formationService.saveChapter(title,description,courseId);
    }
    @PatchMapping("chapters")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> updateChapter( @RequestParam("title") String title,
                                            @RequestParam("description") String description,
                                            @RequestParam("chapterId") Long chapterId){
        return formationService.updateChapter(title,description,chapterId);
    }
    /*********************************************************/
    /*                     Sub-Chapter                           */
    /*********************************************************/
    @PostMapping("subChapters")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> createSubChapter(@RequestParam(value = "title") String title,
                                              @RequestParam(value = "content") String content,
                                              @RequestParam(value = "file") MultipartFile file,
                                              @RequestParam("chapterId") Long chapterId){
        return  formationService.saveSubChapter(title,content,file,chapterId);
    }
    @PatchMapping("subChapters")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public  ResponseEntity<?> updateSubChapter(@RequestParam(value = "title") String title,
                                               @RequestParam(value = "content") String content,
                                               @RequestParam(value = "file") MultipartFile file,
                                               @RequestParam("subChapterId") Long subChapterId){
        return formationService.updateSubChapter(title,content,file,subChapterId);
    }
    @PostMapping("subChapters/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public  ResponseEntity<?>  deleteSubChapter(@PathVariable Long subChapterId){
        return  formationService.deleteSubChapter(subChapterId);
    }
    @GetMapping("subChapters")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getSubChapterById(@RequestParam("subChapterId") Long subChapterId){
        return formationService.getSubChapterById(subChapterId);
    }
    /*********************************************************/
    /*                     Quizzes                           */
    /*********************************************************/
    @PostMapping("quizzes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> createQuiz(@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("courseId") Long courseId){
        return  formationService.saveQuiz(title,description,courseId);
    }
    @PatchMapping("quizzes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public  ResponseEntity<?> updateQuiz(@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("quizId") Long quizId){
        return  formationService.updateQuiz(title,description,quizId);
    }
    @PostMapping("quizzes/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> deleteQuiz(@RequestParam("quizId") Long quizId){
        return  formationService.deleteQuiz(quizId);
    }
    /*********************************************************/
    /*                     Questions                           */
    /*********************************************************/
    @PostMapping("questions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> createQuestion(@RequestParam("content") String content,@RequestParam("mark") double mark,@RequestParam("quizId") Long quizId){
        return  formationService.saveQuestion(content,mark,quizId);
    }
    @PatchMapping("questions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> updateQuestion(@RequestParam("content") String content,@RequestParam("mark") double mark,@RequestParam("questionId") Long questionId){
        return  formationService.updateQuestion(content,mark,questionId);
    }
    @PostMapping("questions/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> deleteQuestion(@RequestParam("questionId") Long questionId){
        return  formationService.deleteQuestion(questionId);
    }
    /*********************************************************/
    /*                     Question options                          */
    /*********************************************************/
    @PostMapping("questionOptions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> createQuestionOption(@RequestParam("content") String content,@RequestParam("isCorrect") int isCorrect,@RequestParam("questionId") Long questionId){
        return  formationService.saveQuestionOption(content,isCorrect,questionId);
    }
    @PatchMapping("questionOptions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> updateQuestionOption(@RequestParam("content") String content,@RequestParam("isCorrect") int isCorrect,@RequestParam("questionOptionId") Long questionOptionId){
        return  formationService.updateQuestionOption(content,isCorrect,questionOptionId);
    }
    @PostMapping("questionOptions/delete")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
    public ResponseEntity<?> deleteQuestionOption(@RequestParam("questionOptionId") Long questionOptionId){
        return  formationService.deleteQuestionOption(questionOptionId);
    }
    @PostMapping("passed")
    @PreAuthorize("isAuthenticated()")
    public  ResponseEntity<?> createPassedQuiz(@RequestParam("note") double note,
                                               @RequestParam("personId") Long personId,
                                               @RequestParam("quizId") Long quizId){
        return formationService.createPassedQuiz(note,personId,quizId);
    }
}
