

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
      <div class="write__comment">
        <p class="header">Write a comment</p>
        <textarea v-model="comment"></textarea>
        <button class="yellow_btn" @click="createComment">Send</button>
      </div>
      <div class="post__comments">
        <CommentsList :postId="parseInt(this.id)"></CommentsList>
      </div>
    </div>
  </div>

</template>

<script>
import CommentsList from "@/components/CommentsList.vue";
import {useToast} from "vue-toastification";
const toast =  useToast();
export default {
  components: {CommentsList},
  data() {
    return {
      title: "",
      author: "",
      components: [],
      comment: ""
    }
  },

  props: {
    id: {
      type: [Number, String],
      required: true
    }
  },

  mounted() {
    this.axios
        .get("http://localhost:8080/posts/get/" + parseInt(this.id))
        .then(response => {
          this.title = response.data.title;
          this.author = response.data.author;
          this.components = response.data.components;
        })
  },
  methods: {
   createComment() {
     if (this.comment !== "") {
       this.axios.post("http://localhost:8080/comments/create", {
         postId: parseInt(this.id),
         message: this.comment
       }).then(response => {
         toast.success("Comment successfully created!");
       })

     }
   }
  }
}
</script>