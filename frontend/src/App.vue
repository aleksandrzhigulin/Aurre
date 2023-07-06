<template>
    <div>
        <div class="posts">
            <div class="post" v-for="post in posts" :key="post.id">
                <p class="title"><strong>{{ post.title }}</strong></p>
                <p class="preview">{{ post.preview }}</p>
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
export default {
    data() {
        return {
            posts: [
                {id: 1, title: "Hello", preview: "Hello world!", author: "admin"},
                {id: 2, title: "Post 2", preview: "Hello!", author: "ancapybara"},
                {id: 3, title: "Post 3", preview: "Whats up?", author: "capybara"},
            ],
            title: "",
            preview: "",
            author: ""
        }
    },

    methods: {
        createPost() {
            const newPost = {
                id: Date.now(),
                title: this.title,
                preview: this.preview,
                author: this.author,
            }
            this.posts.push(newPost);
            this.title = "";
            this.preview = "";
            this.author = "";
        },
    
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