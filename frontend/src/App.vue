<template>
    <div>
        <div class="posts">
            <div class="post" v-for="post in posts" :key="post.id">
                <p class="title"><strong>{{ post.title }}</strong></p>
                <p class="preview">{{ post.content }}</p>
                <p class="author">{{ post.author }}</p>
            </div>

        </div>
    </div>
    <form @submit.prevent>
        <h2>New post:</h2>
        <input 
        v-bind:value="title" 
        @input="title = $event.target.value"
        type="text" 
        placeholder="Title:"
        >
        
        <input 
        v-bind:value="preview" 
        @input="preview = $event.target.value"
        type="text" 
        placeholder="Content:"
        >

        <input 
        v-bind:value="author" 
        @input="author = $event.target.value"
        type="text" 
        placeholder="Author:"
        >
        <button @click="createPost">Create</button>
    </form>
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
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}
form {
    width: 50%;
    margin: 0 auto;
    display: flex;
    flex-direction: column;
    width: 30rem;
    
}
form * {
    margin-bottom: 1em;
    height: 3em;
}
form h2 {
    margin-bottom: 0;
    height: 100%;
}
.posts {
    width: 50%;
    margin: 0 auto;
    background-color: #FFFFFF;
    border: 3px solid black;
    border-radius: 15px;
    padding: 10px;
}
.post {
    margin-bottom: 5em;
}
.post:last-child {
    margin-bottom: 0;
}
</style>