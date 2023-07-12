import { createApp } from 'vue';
import App from './App.vue';
import router from "@/router/router";
import { axiosInstance } from './plugins/axios';
import VueCookies from 'vue-cookies';

const app = createApp(App)

app.config.globalProperties.axios=axiosInstance
app
    .use(router)
    .use(VueCookies)
    .mount("#app");
    
