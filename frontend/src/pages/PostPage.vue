

<template>
  <div class="post__page">
    <div class="container">
      <div class="post">
        <h1>{{this.title}}</h1>
        <div class="components">
          <div class="post__component" v-for="component in components" :key="component">
            <div class="text__component" v-if="component.type === 'text'">
              <p><b>{{component.body}}</b></p>
            </div>
          </div>
        </div>
        <p>{{this.author}}</p>
      </div>
    </div>
  </div>

</template>

<script>
export default {
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