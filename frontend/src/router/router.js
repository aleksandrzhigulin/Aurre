import login from '@/pages/LoginPage';
import Home from "@/pages/HomePage";
import Profile from "@/pages/ProfilePage";
import AboutPage from "@/pages/AboutPage.vue";
import CreatePostPage from "@/pages/CreatePostPage.vue";
import { createWebHistory } from 'vue-router';
import {createRouter} from "vue-router";

const routes = [
    {
        path: '/',
        component: Home
    },
    {
        path: '/auth',
        component: login
    },
    {
        path: '/profile/:username',
        component: Profile
    },
    {
        path: '/profile',
        component: Profile
    },
    {
        path: '/about',
        component: AboutPage
    },
    {
        path: '/createPost',
        component: CreatePostPage
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;