import login from '@/pages/LoginPage';
import Home from "@/pages/HomePage";
import Profile from "@/pages/ProfilePage";

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
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;