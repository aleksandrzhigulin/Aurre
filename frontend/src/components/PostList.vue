<template>
    <div class="posts">
        <div class="post" v-for="post in posts" :key="post.id">
            <div class="post__header">
               <p class="title">
                 <router-link :to="'/post/' + post.id" class="title" :id="post.id">{{post.title}}</router-link>
               </p>
            </div>
            <div class="post__body">
                <div class="post__image">
                    <img :src="'http://localhost:8080/files/get/' + post.preview" alt="">
                </div> 
            </div>
            <div class="post__footer">
                <div class="post__info">
                    <div class="post__info-rating">
                        <i class="fa-regular fa-heart"></i>
                        <span>+200</span>
                    </div>

                    <div class="post__info-author">
                        <p class="author"><router-link :to="'/profile/' + post.author">{{post.author}}</router-link></p>
                     </div>
                </div>
            </div>
            
        </div>

    </div>
</template>

<script>

export default {
    props: {
        posts: {
            type: Array,
            required: true
        }
    },
}
</script>