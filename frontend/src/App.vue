<template>
    <header>
        <div class="container">
            <div class="pages">
                <a href="">Home</a>
            </div>
            <div class="user">
                <div class="user__actions">
                    <a href="">Write a post</a>
                </div>
                <div class="user__info">
                    <div class="user__avatar">
                        <img src="" alt="">
                    </div>
                    <p>Ancapybara</p>
                </div>
            </div>
        </div>
    </header>
    <div class="container">
        <div>
            <div class="posts">
                <div class="post" v-for="post in posts" :key="post.id">
                    <div class="post__header">
                       <p class="title">{{ post.title }}</p> 
                    </div>
                    <div class="post__body">
                        <div class="post__image">
                            <img src="./image/newyork.jpg" alt=""> 
                        </div> 
                    </div>
                    <div class="post__footer">
                        <div class="post__info">
                            <div class="post__info-rating">
                                <i class="fa-regular fa-heart"></i>
                                <span>+200</span>
                            </div>

                            <div class="post__info-author">
                                <p class="author">{{ post.author }}</p> 
                             </div>
                        </div>
                    </div>
                    
                </div>
    
            </div>
        </div>
        <form @submit.prevent>
            <h2>New post:</h2>
            <input class="input_default"
            v-bind:value="title" 
            @input="title = $event.target.value"
            type="text" 
            placeholder="Title:"
            >
            
            <input class="input_default"
            v-bind:value="preview" 
            @input="preview = $event.target.value"
            type="text" 
            placeholder="Content:"
            >
    
            <input class="input_default"
            v-bind:value="author" 
            @input="author = $event.target.value"
            type="text" 
            placeholder="Author:"
            >
            <button @click="createPost" class="yellow_btn">Create new post</button>
        </form>
    </div>

</template>

<script>
import axios from 'axios';

export default {
    data() {
        return {
            posts: null,
            title: "",
            preview: "",
            author: ""
        }
    },

    methods: {
        createPost() {
            var postTitle = this.title;
            var postContent = this.preview;
            var postAuthor = this.author;
            axios.post('http://localhost:8080/posts/create', {
                title: postTitle,
                content: postContent,
                author: postAuthor
            })
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });
            this.title = "";
            this.preview = "";
            this.author = "";
        },
    
    },

    mounted() {
        axios
        .get("http://localhost:8080/posts/get/all")
        .then(response => (this.posts = response.data))
    }
}
</script>

<style>
@import 'style/main.css';
</style>