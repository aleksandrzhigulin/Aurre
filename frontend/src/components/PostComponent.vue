

<template>
  <div class="div">
    <div class="post-create__element">
      <p class="post-create__element-header">Text</p>
      <input type="text" class="text__input" v-model="body" v-on:change="this.$emit('edit', body)">
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
    }
  }
}
</script>

<style scoped>

</style>