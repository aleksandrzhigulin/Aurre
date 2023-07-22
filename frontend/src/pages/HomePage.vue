<template>
    <div class="container">
        <PostList :posts="posts"></PostList>
    </div>

</template>

<script>

import PostList from '@/components/PostList';
export default {
    components: {
        PostList
    },
    data() {
        return {
            posts: null,
            
        }
    },



    mounted() {
        this.axios
        .get("http://localhost:8080/posts/get/all")
        .then(response => (this.posts = response.data))
    }
}
</script>

<style>
@import '@/style/main.css';
</style>