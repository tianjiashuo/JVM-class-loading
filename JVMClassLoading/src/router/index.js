import Vue from 'vue'
import Router from 'vue-router'
import StartPage from "../components/StartPage";
import CodePage from "../components/CodePage";
import ShowPage from "../components/ShowPage";

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'StartPage',
      component: StartPage
    },
    {
      path: '/code',
      name: 'CodePage',
      component: CodePage
    },
    {
      path: '/show',
      name: 'ShowPage',
      component: ShowPage
    }
  ]
})
