import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import VeeValidate from "vee-validate";
import "bootstrap";
import "normalize-css";
import "bootstrap/dist/css/bootstrap.min.css";
import 'vue-select/dist/vue-select.css';
import { library } from "@fortawesome/fontawesome-svg-core";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt
} from "@fortawesome/free-solid-svg-icons";
library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);
Vue.config.productionTip = false;
import VueCoreVideoPlayer from "vue-core-video-player";
import vSelect from 'vue-select'
import VueSimpleAlert from "vue-simple-alert";
Vue.use(VueSimpleAlert);
Vue.use(VueCoreVideoPlayer);
Vue.use(VeeValidate);
Vue.component('v-select', vSelect)
Vue.component("font-awesome-icon", FontAwesomeIcon);
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
