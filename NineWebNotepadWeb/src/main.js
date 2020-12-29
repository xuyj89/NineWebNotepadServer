import { createApp } from 'vue';
import { createStore } from 'vuex';
import App from './App.vue';
import ElementPlus from 'element-plus';
import config from './config';

import 'element-plus/lib/theme-chalk/index.css';
import router from './router/router';
import './index.css';

const store = createStore({
  state () {
    return {
      config:config
    }
  },
  mutations: {
    increment (state) {
      state.count++
    }
  }
})

const app = createApp(App);
app.use(ElementPlus);
app.use(store);
app.use(router);
app.mount('#app');