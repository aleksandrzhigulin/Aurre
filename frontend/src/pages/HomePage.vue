<template>
    <div class="container">
        <PostList :posts="posts"></PostList>
        <PostForm></PostForm>
    </div>

</template>

<script>
import axios from 'axios';

import PostForm from '@/components/PostForm';
import PostList from '@/components/PostList';
export default {
    components: {
        PostForm, PostList
    },
    data() {
        return {
            posts: null,
            
        }
    },



    mounted() {
        axios
        .get("http://localhost:8080/posts/get/all")
        .then(response => (this.posts = response.data))
    }
}
</script>

<style>
@import '@/style/main.css';
</style>