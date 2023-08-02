<template>
    <div class="app">
        <AurreHeader></AurreHeader>
        <router-view :key="$route.fullPath"></router-view>
    </div>
</template>

<script>

import AurreHeader from '@/components/AurreHeader';
export default {
    components: {
        AurreHeader
    },
    data() {
        return {
            isAuthorized: false,
        }
    },
    mounted() {
    }
}
</script>

<style>
@import 'style/main.css';
</style>