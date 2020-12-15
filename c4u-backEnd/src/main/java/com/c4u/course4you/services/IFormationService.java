package com.c4u.course4you.services;

import com.c4u.course4you.entities.*;
import com.c4u.course4you.entities.form.CategoryForm;
import com.c4u.course4you.entities.form.CourseForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IFormationService {
   public Person verifyPerson(Long personId);
   /**************Category****************/
   public ResponseEntity<?> saveCategory(CategoryForm categoryForm);
   public ResponseEntity<?> deleteCategory(Long categoryId);
   public ResponseEntity<?> updateCategory(CategoryForm categoryForm,Long id);
   public Category verifyCategory(Long categoryId);
   public List<Category> getAllCategories();

   /******************Course***************/
   public ResponseEntity<?> saveCourse(String title,String description,Long[] categoriesId,MultipartFile file, Long personId);
   public ResponseEntity<?> deleteCourse(Long courseId);
   public Course verifyCourse(Long courseId);
   public ResponseEntity<?>getCoursesByCategory(Long categoryId);
   public  ResponseEntity<?> getPersonCourses(Long personId);
//   public ResponseEntity<?> searchCourse(String keyWord);
   public ResponseEntity<?> updateCourse(String title,String description,Long[] categoriesId,MultipartFile file, Long courseId);
   public ResponseEntity<?> getCourseById(Long courseId);
   public  ResponseEntity<?> addCourseToPerson(Long courseId,Long personId);
   public  ResponseEntity<?> getCourseChapters(Long chapterId);

   /********************Chapter**************/
   public ResponseEntity<?> saveChapter(String title,String description,Long courseId);
   public ResponseEntity<?> deleteChapter(Long chapterId);
   public Chapter verifyChapter(Long chapterId);
   public ResponseEntity<?> updateChapter(String title,String description,Long chapterId);
   public  ResponseEntity<?> getChapterById(Long chapterId);
   /**************************SubChapter***********/
   public ResponseEntity<?> saveSubChapter(String title,String content,MultipartFile file,Long chapterId);
   public ResponseEntity<?> deleteSubChapter(Long subChapterId);
   public SubChapter verifySubChapter(Long subChapterId);
   public ResponseEntity<?> updateSubChapter(String title,String content,MultipartFile file,Long subChapterId);
   public  ResponseEntity<?> getSubChapterById(Long subChapterId);
   /************************Quiz*******************************/
   public ResponseEntity<?> saveQuiz(String title,String description,Long courseId);
   public  ResponseEntity<?> updateQuiz(String title,String description,Long quizId);
   public  ResponseEntity<?> deleteQuiz(Long id);
   public Quiz verifyQuiz(Long quizId);
   /**********************Question*******************/
   public  ResponseEntity<?> saveQuestion(String content,double mark,Long quizId);
   public  ResponseEntity<?> updateQuestion(String content,double mark,Long questionId);
   public   ResponseEntity<?> deleteQuestion(Long questionId);
   public  Question verifyQuestion(Long questionId);
   /************************QuestionOption***************************/
   public  ResponseEntity<?> saveQuestionOption(String content,int isCorrect,Long questionId);
   public  ResponseEntity<?>  updateQuestionOption(String content,int isCorrect,Long questionOptionId);
   public  ResponseEntity<?>  deleteQuestionOption(Long questionOptionId);
   public  QuestionOption verifyQuestionOption(Long questionOptionId);
   /************************course resource ******************************/
   public  ResponseEntity<?> deleteCourseResource(Long courseResourceId);
   public CourseResource verifyCourseResource(Long courseResourceId);
   public ResponseEntity<?> storeFile(MultipartFile file,Long id, String typeResource);


}
