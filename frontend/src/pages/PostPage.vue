

<template>
  <div class="post__page">
    <div class="container">
      <div class="post">
        <h1>{{this.title}}</h1>
        <div class="components">
          <div class="post__component" v-for="component in components" :key="component">
            <p class="paragraph" v-if="component.type === 'text'">{{component.body}}</p>
          </div>
        </div>
        <p class="author"><router-link :to="'/profile/' + this.author">{{this.author}}</router-link></p>
      </div>

      <div class="post__comments">
        <CommentsList :post-id="this.id"></CommentsList>
      </div>
    </div>
  </div>

</template>

<script>
import CommentsList from "@/components/CommentsList.vue";

export default {
  components: {CommentsList},
  data() {
    return {
      title: "",
      author: "",
      components: []
    }
  },

  props: {
    id: {
      type: Number,
      required: true
    }
  },

  mounted() {
    this.axios
        .get("http://localhost:8080/posts/get/" + this.id)
        .then(response => {
          this.title = response.data.title;
          this.author = response.data.author;
          this.components = response.data.components;
        })
  }
}
</script>