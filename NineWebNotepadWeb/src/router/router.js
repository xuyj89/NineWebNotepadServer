import {createRouter, createWebHistory } from 'vue-router';
import Login from '../components/Login.vue';
import HelloWorld from '../components/HelloWorld.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    // route -> routes
    {
      path: '/',
      name: 'Login',
      component: Login,
    }
　]
});

export default router;