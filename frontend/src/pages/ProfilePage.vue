<template>
  <div class="profile__page">
    <div class="container">
      <div class="profile">
          <div class="profile__info">
            <div class="profile__info-left">
              <div class="image-wrap">
                <img :src="'http://localhost:8080/files/get/' + this.avatarFilename" alt="Avatar">
              </div>
              <button class="yellow_btn" v-if="profileUsername === username">Upload an avatar</button>
            </div>
            <div class="profile__info-right">
              <h1>{{this.profileUsername}}</h1>
              <p class="text__header">About</p>
            </div>
          </div>

      </div>
    </div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      isAuthorized: false,
      username: "",
      avatarFilename: ""
    }
  },

  props: {
    profileUsername: {
      type: String,
      required: true
    }
  },

  mounted() {
    if (getCookie("jwt") != null) {
      this.isAuthorized = true;
      this.axios
          .get("http://localhost:8080/user/get")
          .then(response => (this.id = response.data.id,
              this.username = response.data.username,
              this.avatarFilename = response.data.avatarFilename))

    } else {
      this.axios
          .get("http://localhost:8080/user/get/" + this.profileUsername)
          .then(response => (this.id = response.data.id,
              this.avatarFilename = response.data.avatarFilename))
    }
  }

}
</script>