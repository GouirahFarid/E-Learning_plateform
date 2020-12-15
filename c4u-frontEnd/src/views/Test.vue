<template>
    <div class="container mt-5">
        <CourseForm></CourseForm>
        <Chapter></Chapter>
    </div>
</template>

<script>
    import CourseForm from "../components/profile/courses/course/NewCourseForm"
    import  Chapter from "../components/profile/courses/chapter/NewChapterForm"
    export default {
        name: "Test",
        components:{CourseForm,Chapter}
    }
</script>

<style scoped>

</style>