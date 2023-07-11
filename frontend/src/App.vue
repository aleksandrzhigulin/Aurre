<template>
    <div class="app">
        <Header></Header>
        <router-view></router-view>
    </div>
</template>

<script>
import Header from '@/components/Header';
export default {
    components: {
        Header
    }
}
</script>

<style>
@import 'style/main.css';
</style>