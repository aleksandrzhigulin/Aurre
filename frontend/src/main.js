import { createApp } from 'vue';
import App from './App.vue';
import router from "@/router/router";
import { axiosInstance } from './plugins/axios';
import VueCookies from 'vue-cookies';
import Toast, {useToast} from "vue-toastification";
import "vue-toastification/dist/index.css";
import {createStore} from "vuex";

const store = createStore({
    state() {
        return {
            isAuthorized: false,
            username: ""
        }
    },
    mutations: {
        setIsAuthorized(state, status) {
            state.isAuthorized = status;
        },
        setUsername(state, nickname) {
            state.username = nickname;
        }
    }
})
const options = {
    position: "bottom-right",
    timeout: 3000,
    closeOnClick: true,
    pauseOnFocusLoss: true,
    pauseOnHover: true,
    draggable: true,
    draggablePercent: 1,
    showCloseButtonOnHover: false,
    hideProgressBar: true,
    closeButton: "button",
    icon: true,
    rtl: false
};
const app = createApp(App)

app.config.globalProperties.axios=axiosInstance
app
    .use(store)
    .use(router)
    .use(Toast, options)
    .use(VueCookies)
    .mount("#app");
    
