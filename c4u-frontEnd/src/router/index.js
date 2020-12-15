import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/login/:login",
    name: "Login",
    component: () => import("../views/Sign")
  },
  {
    path: "/profile/:type/:account",
    name: "Profile",
    component: () => import("../views/ProfileView")
  },
  {
    path: "/test",
    name: 'Test',
    component: ()=>import("../views/Test")
  },
  {
    path: "/course/:courseId",
    name: "Course",
    component: () => import("../views/Course"),
  },
  {
    path: "/courses/:id",
    name: "CategoryCourses",
    component: () => import("../views/CategoryCourses")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
