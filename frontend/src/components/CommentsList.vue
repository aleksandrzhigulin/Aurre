<template>
<div class="comments">
  <div class="comment" v-for="comment in comments" :key="comment.id">
    <div class="comment__left">
      <div class="image-wrap">
        <img src="" alt="Avatar">
      </div>
    </div>
    <div class="comment__right">
        <div class="comment-info">
          <p class="author"><router-link :to="'/profile/' + comment.author">{{comment.author}}</router-link></p>
          <p class="message">{{comment.message}}</p>
        </div>
    </div>
  </div>
</div>
</template>

<script>

export default {
  props: {
    postId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      comments: []
    }
  },
  mounted() {
    this.axios
        .get("http://localhost:8080/comments/post/" + this.postId)
        .then(response => (this.comments = response.data))
  }
}

</script>