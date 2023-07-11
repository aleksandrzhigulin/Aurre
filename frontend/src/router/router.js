import login from '@/pages/LoginPage';
import Home from "@/pages/HomePage";
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
    }
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;