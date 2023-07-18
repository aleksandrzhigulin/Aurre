<template>
    <div class="container newPost">
        <form @submit.prevent>
            <div class="post-create__element">
              <p class="post-create__element-header">Title</p>
              <input type="text" class="text__input" v-model="title">
            </div>

           <div class="post-create__element">
            <p class="post-create__element-header">Preview</p>
            <input type="file" accept="image/*" @change="onFileChange">
             <img v-if="url" :src="url"/>
           </div>


            <div class="custom__elements" id="custom__elements" v-for="component in components" :key="component.id">
              <PostComponent :id="component.id" :component_type="component.type" @edit="onComponentChange"></PostComponent>
            </div>

          <div class="new__element-btn dropdown">
            <button class="dropbtn" @click="myFunction"><i class="fa-solid fa-plus"></i>New element</button>
            <div id="myDropdown" class="dropdown-content">
              <a href="#" @click="addTextInstance">Text</a>
              <!--<a href="#">Image</a> -->
            </div>
          </div>
          
            <div class="form__btns">
              <button class="yellow_btn">Save</button>
              <button class="yellow_btn" @click="publishPost">Publish</button>
            </div>

        </form>
    </div>
</template>

<script>
import PostComponent from "@/components/PostComponent.vue";
import PostComponentClass from "@/models/PostComponentClass.js";


export default {
    data() {
        return {
            title: "",
            url: null,
            components: [],
            componentsAmount: 0
        }
    },
    components: {
      PostComponent
    },
  methods: {
    onFileChange(e) {
      const file = e.target.files[0];
      this.url = URL.createObjectURL(file);
    },
    myFunction() {
      document.getElementById("myDropdown").classList.toggle("show");
    },

    addTextInstance() {
      const new_component = new PostComponentClass(this.componentsAmount, "text");
      this.components.push(new_component);
      this.componentsAmount += 1;
    },
    onComponentChange(id, body) {
      this.components.forEach(function (elem) {
        if (elem.id === id) {
          elem.body = body;
        }
      })
    },

    publishPost() {
      this.axios.post("http://localhost:8080/posts/create", {
        title: this.title,
        author: getCookie("username"),
        components: this.components
      })
    }

  }
}

// Close the dropdown menu if the user clicks outside of it
window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}
</script>