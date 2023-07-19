

<template>
  <div class="div">
    <div class="post-create__element" v-if="component_type==='text'">
      <p class="post-create__element-header">Text</p>
      <textarea v-model="body" v-on:change="this.$emit('edit', id, body)"></textarea>
      <!--<input type="text" class="text__input" v-model="body" v-on:change="this.$emit('edit', id, body)">-->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      body: ""
    }
  },
  props: {
    component_type: {
        type: String,
        required: true
    },
    id: {
      type: Number,
      required: true
    }
  }
}
</script>

<style scoped>

</style>