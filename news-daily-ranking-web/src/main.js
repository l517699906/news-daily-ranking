import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

import axios from 'axios';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';

createApp(App).config.globalProperties.$axios = axios;
createApp(App).use(ElementPlus).mount('#app');

