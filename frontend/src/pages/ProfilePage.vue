<template>
  <div class="profile__page">
    <div class="container">
      <div class="profile">
          <div class="profile__info">
            <div class="profile__info-left">
              <div class="image-wrap">
                <img :src="'http://localhost:8080/files/get/' + this.avatarFilename" alt="Avatar">
              </div>
              <button class="yellow_btn"
                      v-if="profileUsername === username"
                      type="button"
                      @click="openPopup">Upload an avatar</button>
            </div>
            <div class="profile__info-right">
              <h1>{{this.profileUsername}}</h1>
              <p class="text__header">About</p>
            </div>
          </div>

      </div>
    </div>

    <div class="popup">
      <h1>Upload an avatar</h1>
      <div class="preview">
        <div class="image-wrap">
          <img :src="'http://localhost:8080/files/get/' + this.avatarFilename" alt="Avatar">
        </div>
      </div>

      <progress max="100" :value.prop="uploadProgress"></progress>
      <label class="custom-file-upload">
        <input accept="image/*" type="file" @change="uploadAvatar" style="display: none">
        <p>Upload</p>
      </label>

    </div>
    <div class="popup__bg" @click="closePopup"></div>
  </div>

</template>

<script>
export default {
  data() {
    return {
      isAuthorized: false,
      username: "",
      avatarFilename: "",
      uploadProgress: 0
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
  },
  methods: {
    openPopup() {
      let popup = document.querySelector('.popup');
      let popupBg = document.querySelector('.popup__bg');
      popupBg.classList.add('active');
      popup.classList.add('active');
    },
    closePopup() {
      let popup = document.querySelector('.popup');
      let popupBg = document.querySelector('.popup__bg');
      popupBg.classList.remove('active');
      popup.classList.remove('active');
    },
    updateProgress(progressEvent) {
      this.uploadProgress = Math.round( (progressEvent.loaded * 100) / progressEvent.total);
    },
    uploadAvatar(e) {
      this.axios
          .postForm("http://localhost:8080/files/upload/avatar/", {
            file: e.target.files[0]
          }, {
            onUploadProgress: progressEvent => this.updateProgress(progressEvent)
          })
          .then(response => {
            console.log(response);
            this.avatarFilename = response.data;
          })
    }
  }

}
</script>