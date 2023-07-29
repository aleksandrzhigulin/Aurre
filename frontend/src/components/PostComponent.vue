

<template>
  <div class="div">
    <div class="post-create__element" v-if="component_type==='text'">
      <div class="element__header">
        <p class="post-create__element-header">Text</p>
        <button @click="this.$emit('remove', id)">Remove</button>
      </div>

      <textarea v-model="body" v-on:change="this.$emit('edit', id, body)"></textarea>
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