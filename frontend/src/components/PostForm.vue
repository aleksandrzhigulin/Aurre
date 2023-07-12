<template>
    <form @submit.prevent>
        <h2>New post:</h2>
        <input class="input_default"
        v-model="post.title"
        type="text" 
        placeholder="Title:"
        >
        
        <input class="input_default"
        v-model="post.preview"
        type="text" 
        placeholder="Content:"
        >

        <input class="input_default"
        v-model="post.author"
        type="text" 
        placeholder="Author:"
        >
        <button @click="createPost" class="yellow_btn">Create new post</button>
    </form>
</template>

<script>

export default {
    data() {
        return {
            post: {
                title: '',
                preview: '',
                author: ''
            }
        }
    },

    methods: {
        createPost() {
            var postTitle = this.post.title;
            var postContent = this.post.preview;
            var postAuthor = this.post.author;
            this.axios.post('http://localhost:8080/posts/create', {
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
}
</script>