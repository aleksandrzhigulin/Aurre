import login from '@/pages/LoginPage';
import Home from "@/pages/HomePage";
import Profile from "@/pages/ProfilePage";
import AboutPage from "@/pages/AboutPage.vue";
import CreatePostPage from "@/pages/CreatePostPage.vue";
import { createWebHistory } from 'vue-router';
import {createRouter} from "vue-router";
import PostPage from "@/pages/PostPage.vue";

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
        component: CreatePostPage,
        props: true
    },
    {
        path: '/post/:id',
        component: PostPage,
        props: true
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;