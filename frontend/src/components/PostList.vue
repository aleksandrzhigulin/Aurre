<template>
    <div class="posts">
        <div class="post" v-for="post in posts" :key="post.id">
            <div class="post__header">
               <p class="title">{{ post.title }}</p> 
            </div>
            <div class="post__body">
                <div class="post__image">
                    <img src="../image/newyork.jpg" alt=""> 
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
</template>

<script>

export default {
    props: {
        posts: {
            type: Array,
            required: true
        }
    }
}
</script>