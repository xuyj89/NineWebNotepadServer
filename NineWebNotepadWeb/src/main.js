import { createApp } from 'vue';
import App from './App.vue';
import Vuex from 'vuex';
import ElementPlus from 'element-plus';
import 'element-plus/lib/theme-chalk/index.css';
import router from './router/router';
import './index.css';

const app = createApp(App);
app.use(ElementPlus);
app.use(Vuex);
app.use(router);
app.mount('#app');

