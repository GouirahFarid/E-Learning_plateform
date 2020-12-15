package com.c4u.course4you.services;

import com.c4u.course4you.entities.*;
import com.c4u.course4you.entities.form.CategoryForm;
import com.c4u.course4you.repositories.*;
import com.c4u.course4you.exceptions.*;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class FormationService implements IFormationService {
    public  final PersonRepository personRepository;
    private  final CategoryRepository categoryRepository;
    private  final CourseRepository courseRepository;
    private  final ChapterRepositoty chapterRepositoty;
    private final SubChapterRepository subChapterRepository;
    private final CourseResourceRepository courseResourceRepository;
    private final QuizRepository quizRepository;
    private  final QuestionRepository questionRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final Path fileStorageLocation;
    private  final PassedQuizRepository passedQuizRepository;
    public FormationService(PersonRepository personRepository, CategoryRepository categoryRepository,
                            CourseRepository courseRepository, ChapterRepositoty chapterRepositoty,
                            SubChapterRepository subChapterRepository, CourseResourceRepository courseResourceRepository,
                            QuizRepository quizRepository, QuestionRepository questionRepository, QuestionOptionRepository questionOptionRepository, CourseResource courseResource, PassedQuizRepository passedQuizRepository) {
        this.personRepository = personRepository;
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.chapterRepositoty = chapterRepositoty;
        this.subChapterRepository = subChapterRepository;
        this.courseResourceRepository = courseResourceRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.questionOptionRepository = questionOptionRepository;
        this.fileStorageLocation = Paths.get(courseResource.getUploadDir()).toAbsolutePath().normalize();
        this.passedQuizRepository = passedQuizRepository;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new DocumentStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Person verifyPerson(Long personId) {
        return personRepository.findById(personId).orElseThrow(()->new ResourceNotFoundException("Person not found"));
    }
    @Override
    public ResponseEntity<?> storeFile(MultipartFile file,Long id, String typeResource) {
        // Normalize file name
        String originalFileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String fileName = "";
        // Check if the file's name contains invalid characters
        if(originalFileName.contains("..")) { throw new DocumentStorageException("Sorry! Filename contains invalid path sequence " + originalFileName); }
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        fileName =typeResource.equals("course")?"course_"+id + "_"+originalFileName:"subChapter_"+id + "_"+originalFileName;
        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        CourseResource doc = courseResourceRepository.findByFileName(fileName);
        if(doc != null) {
            throw  new FileNameExistException("this file '"+originalFileName+"' exist");
        } else {
            CourseResource newResource = new CourseResource();
            newResource.setFileName(fileName);
            newResource.setResourceType(fileExtension);
            newResource.setUploadDir(String.valueOf(this.fileStorageLocation)+"/"+fileName);
            if (typeResource.equals("course"))
                newResource.setCourse(verifyCourse(id));
            else
                newResource.setSubChapter(verifySubChapter(id));
            courseResourceRepository.save(newResource);
        }
        return ResponseEntity.ok("file Uploaded");
    }
    /*********************************************************/
    /*                     Category                          */
    /*********************************************************/
    @Override
    public ResponseEntity<?> saveCategory(CategoryForm categoryForm) {
        Category category=new Category();
        category.setTitle(categoryForm.getTitle());
        category.setDescription(categoryForm.getDescription());
        categoryRepository.save(category);
        return ResponseEntity.ok(new MessageResponse("Category created"));
    }
    @Override
    public ResponseEntity<?> updateCategory(CategoryForm categoryForm,Long id) {
        Category category1=verifyCategory(id);
        if (!categoryForm.getDescription().equals(""))
            category1.setDescription(categoryForm.getDescription());
        if (!categoryForm.getTitle().equals(""))
            category1.setTitle(categoryForm.getTitle());
        categoryRepository.save(category1);
        return ResponseEntity.ok(new MessageResponse("category updated successfully!"));
    }
    @Override
    public ResponseEntity<?> deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
        return  ResponseEntity.ok(new MessageResponse("category deleted successfully!"));
    }
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll(Sort.by(Sort.Direction.ASC,"title"));
    }
    @Override
    public Category verifyCategory(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category not found"));
    }
    /*********************************************************/
    /*                     Course                            */
    /*********************************************************/
    @Override
    public ResponseEntity<?> saveCourse(String title,String description,Long[] categoriesId,MultipartFile file, Long personId) {
        Course course1=new Course();
        course1.getPersons().add(verifyPerson(personId));
        for (Long categoryId:categoriesId) {
            course1.getCategories().add(verifyCategory(categoryId));
        }
        course1.setTitle(title);
        course1.setDescription(description);
        courseRepository.save(course1);
        Course course=courseRepository.findByTitleContaining(title).orElseThrow(()->new ResourceNotFoundException("Course not found"));
        storeFile(file,course.getId(),"course");
         return ResponseEntity.ok(new MessageResponse("Course added successfully!"));
    }
    @Override
    public ResponseEntity<?> addCourseToPerson(Long courseId, Long personId) {
        Course course=verifyCourse(courseId);
        Person person=verifyPerson(personId);
        course.getPersons().add(person);
        courseRepository.save(course);
        return ResponseEntity.ok(new MessageResponse("enrolled"));
    }
    public  ResponseEntity<?> removePersonFromCourse(Long courseId,Long personId){
        var course=verifyCourse(courseId);
        var person=verifyPerson(personId);
        course.getPersons().remove(person);
        courseRepository.save(course);
        return  ResponseEntity.ok(new MessageResponse("Unrolled"));
    }
    @Override
    public ResponseEntity<?> updateCourse(String title,String description,Long[] categoriesId,MultipartFile file, Long courseId) {
        Course course1=verifyCourse(courseId);
        if (!title.equals(""))
            course1.setTitle(title);
        if(!description.equals(""))
            course1.setDescription(description);
        if (categoriesId.length>0)
            for (Long categoryId:categoriesId) {
                course1.getCategories().add(verifyCategory(categoryId));
            }
        if (!file.isEmpty())
            storeFile(file,course1.getId(),"course");
        courseRepository.save(course1);
        return  ResponseEntity.ok(new MessageResponse("course updated"));
    }
    @Override
    public ResponseEntity<?> deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
        return ResponseEntity.ok("course deleted successfully!");
    }
    @Override
    public ResponseEntity<?> getCoursesByCategory(Long categoryId) {
        return new ResponseEntity<>(courseRepository.findByCategories_Id(categoryId),HttpStatus.OK);
    }
    @Override
    public Course verifyCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("course not found"));
    }
    @Override
    public ResponseEntity<?> getCourseById(Long courseId) {
        return new ResponseEntity<>(verifyCourse(courseId),HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> getPersonCourses(Long personId) {
        return new ResponseEntity<>(courseRepository.findAllByPersons_Id(personId),HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> getCourseChapters(Long chapterId) {
        return new ResponseEntity<>(courseRepository.findByChapters_Id(chapterId),HttpStatus.OK);
    }

    /*********************************************************/
    /*                     Chapter                           */
    /*********************************************************/
    @Override
    public Chapter verifyChapter(Long chapterId) {
        return chapterRepositoty.findById(chapterId).orElseThrow(()->new ResourceNotFoundException("chapter not found"));
    }
    @Override
    public ResponseEntity<?> saveChapter(String title,String description,Long courseId) {
        var chapter=new  Chapter();
        chapter.getCourses().add(verifyCourse(courseId));
        chapter.setTitle(title);
        chapter.setDescription(description);
        chapterRepositoty.save(chapter);
        return ResponseEntity.ok(new MessageResponse("Chapter created"));
    }
    @Override
    public ResponseEntity<?> updateChapter(String title,String description,Long chapterId) {
        var chapter1=verifyChapter(chapterId);
        if (!title.equals(""))
            chapter1.setTitle(title);
        if (!description.equals(""))
            chapter1.setDescription(description);
        chapterRepositoty.save(chapter1);
        return ResponseEntity.ok(new MessageResponse("chapter updated"));

    }
    @Override
    public ResponseEntity<?> getChapterById(Long chapterId) {
        return new ResponseEntity<>(verifyChapter(chapterId),HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> deleteChapter(Long chapterId) {
        chapterRepositoty.deleteById(chapterId);
        return ResponseEntity.ok(new MessageResponse("Chapter updated"));
    }
    /*********************************************************/
    /*                     SubChapter                        */
    /*********************************************************/
    @Override
    public ResponseEntity<?> getSubChapterById(Long subChapterId) {
        return new ResponseEntity<>(verifySubChapter(subChapterId),HttpStatus.OK);
    }
    @Override
    public ResponseEntity<?> saveSubChapter(String title,String content,MultipartFile file,Long chapterId) {
        var chapter=verifyChapter(chapterId);
        var subChapter=new SubChapter();
        subChapter.setTitle(title);
        subChapter.setContent(content);
        subChapter.setChapter(chapter);
        subChapterRepository.save(subChapter);
        var subChapter1=subChapterRepository.findByTitleContaining(title).orElseThrow(()->new ResourceNotFoundException("sub chapter not found"));
        storeFile(file,subChapter1.getId(),"subChapter");
        return ResponseEntity.ok(new MessageResponse("SubChapter created"));
    }
    @Override
    public ResponseEntity<?> updateSubChapter(String title,String content,MultipartFile file,Long subChapterId) {
        SubChapter subChapter=verifySubChapter(subChapterId);
        if (!title.equals(""))
            subChapter.setTitle(title);
        if (!content.equals(""))
            subChapter.setContent(content);
        subChapterRepository.save(subChapter);
        return  ResponseEntity.ok(new MessageResponse("subChapter updated"));
    }
    @Override
    public ResponseEntity<?> deleteSubChapter(Long subChapterId) {
        subChapterRepository.deleteById(subChapterId);
        return  ResponseEntity.ok(new MessageResponse("Sub Chapter deleted"));
    }
    @Override
    public SubChapter verifySubChapter(Long subChapterId) {
        return subChapterRepository.findById(subChapterId).orElseThrow(()->new ResourceNotFoundException("Sub Chpater not found"));
    }

    /*********************************************************/
    /*                     Quiz                              */
    /*********************************************************/
    @Override
    public ResponseEntity<?> saveQuiz(String title,String description,Long courseId) {
        var quiz=new Quiz();
        quiz.setCourse(verifyCourse(courseId));
        quiz.setTitle(title);
        quiz.setDescription(description);
        quizRepository.save(quiz);
        return ResponseEntity.ok(new MessageResponse("quiz created"));
    }
    @Override
    public ResponseEntity<?> updateQuiz(String title,String description,Long quizId) {
        var quiz=verifyQuiz(quizId);
        if (!title.equals(""))
            quiz.setTitle(title);
        if (!description.equals(""))
            quiz.setDescription("");
        quizRepository.save(quiz);
        return ResponseEntity.ok(new MessageResponse("Quiz updated"));
    }
    @Override
    public ResponseEntity<?> deleteQuiz(Long id) {
        quizRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Quiz Deleted"));
    }
    @Override
    public Quiz verifyQuiz(Long quizId) {
        return quizRepository.findById(quizId).orElseThrow(()->new ResourceNotFoundException("quiz not found"));
    }
    /*********************************************************/
    /*                     Question                          */
    /*********************************************************/
    @Override
    public ResponseEntity<?> saveQuestion(String content,double mark,Long quizId ){
        var question=new  Question();
        question.setQuiz(verifyQuiz(quizId));
        question.setContent(content);
        question.setMark(mark);
        questionRepository.save(question);
        return ResponseEntity.ok(new MessageResponse("question created"));
    }
    @Override
    public ResponseEntity<?> updateQuestion(String content,double mark,Long questionId) {
        var question=verifyQuestion(questionId);
        if (!content.equals(""))
            question.setContent(content);
        if (mark>=0)
            question.setMark(mark);
        questionRepository.save(question);
        return ResponseEntity.ok(new MessageResponse("Question updated"));
    }
    @Override
    public ResponseEntity<?> deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
        return ResponseEntity.ok(new MessageResponse("question deleted"));
    }
    @Override
    public Question verifyQuestion(Long questionId) {
        return questionRepository.findById(questionId).orElseThrow(()->new ResourceNotFoundException("question not found"));
    }
    /*********************************************************/
    /*                     Question Option                   */
    /*********************************************************/
    @Override
    public ResponseEntity<?> saveQuestionOption(String content,int isCorrect,Long questionId) {
        var questionOption=new  QuestionOption();
        questionOption.setQuestion(verifyQuestion(questionId));
        questionOption.setContent(content);
        questionOption.setIsCorrect(isCorrect);
        questionOptionRepository.save(questionOption);
        return ResponseEntity.ok(new MessageResponse("question option created"));
    }
    @Override
    public ResponseEntity<?> updateQuestionOption(String content,int isCorrect,Long questionOptionId) {
        var questionOption=verifyQuestionOption(questionOptionId);
        if (!content.equals(""))
            questionOption.setContent(content);
        questionOption.setIsCorrect(isCorrect);
        questionOptionRepository.save(questionOption);
        return ResponseEntity.ok(new MessageResponse("Question option updated"));
    }
    @Override
    public ResponseEntity<?> deleteQuestionOption(Long questionOptionId) {
        questionOptionRepository.deleteById(questionOptionId);
        return ResponseEntity.ok(new MessageResponse("question option deleted"));
    }
    @Override
    public QuestionOption verifyQuestionOption(Long questionOptionId) {
        return questionOptionRepository.findById(questionOptionId).orElseThrow(()->new ResourceNotFoundException("question option not found"));
    }

    /*********************************************************/
    /*                     Course resource                   */
    /*********************************************************/
    @Override
    public ResponseEntity<?> deleteCourseResource(Long courseResourceId) {
        courseResourceRepository.deleteById(courseResourceId);
        return ResponseEntity.ok(new MessageResponse("course resource not found"));
    }
    @Override
    public CourseResource verifyCourseResource(Long courseResourceId) {
        return courseResourceRepository.findById(courseResourceId).orElseThrow(()->new ResourceNotFoundException("course resource not found"));
    }

    /**********************Passed Quiz*************************************/
    public  ResponseEntity<?> createPassedQuiz(double note,Long personId,Long quizId){
        var person=verifyPerson(personId);
        var quiz=verifyQuiz(quizId);
        var passedQuiz=new PassedQuiz();
        passedQuiz.setPerson(person);
        passedQuiz.setQuiz(quiz);
        passedQuiz.setNote(note);
        passedQuizRepository.save(passedQuiz);
        return  ResponseEntity.ok(new MessageResponse("passedQuiz created"));
    }

}
