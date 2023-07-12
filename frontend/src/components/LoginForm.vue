<template>
    <div class="forms">
        <div class="tabs">
           <div class="tab">
                <a href="#signin" data-tab="signin" class="first__tab b-nav-tab active">Sign in</a>
           </div>
           <div class="tab">
                <a href="#signup" data-tab="signup" class="last__tab b-nav-tab">Sign up</a> 
           </div>
        </div>
        
    
          <div id="signin" class="b-tab active">
            <form @submit.prevent>
                <input class="input_default"
                v-model="usernameAuth"
                type="text" 
                placeholder="Username"
                >
                
                <input class="input_default"
                v-model="passwordAuth"
                type="text" 
                placeholder="Password"
                >
                <button @click="authorization()" class="yellow_btn">Sign in</button>
            </form>
          </div>
          <div id="signup" class="b-tab">
            <form @submit.prevent>
                <input class="input_default"
                v-model="usernameRegistration"
                type="text" 
                placeholder="Username"
                >
                
                <input class="input_default"
                v-model="passwordRegistration"
                type="text" 
                placeholder="Password"
                >
                <button @click="registration()" class="yellow_btn">Sign up</button>
            </form>
          </div>
        
    </div>
</template>

<script>

export default {
    data() {
        return {
            usernameAuth: '',
            passwordAuth: '',
            usernameRegistration: '',
            passwordRegistration: ''
        }
    },
    methods: {
        authorization() {
          this.axios.post('http://localhost:8080/auth/login', {
                username: this.usernameAuth,
                password: this.passwordAuth
            })
            .then(function (response) {
                console.log(response.data.access_token);
                setCookie("jwt", response.data.access_token, {secure: true, 'max-age': 31536000});
                location.reload();
            })
            .catch(function (error) {
                console.log(error);
                console.log("error")
            });
            
        },
        registration() {
          this.axios.post('http://localhost:8080/auth/signup', {
                username: this.usernameRegistration,
                password: this.passwordRegistration
            })
            .then(function (response) {
                console.log(response.data.access_token);
                setCookie("jwt", response.data.access_token, {secure: true, 'max-age': 31536000});
                location.reload();
            })
            .catch(function (error) {
                console.log(error);
            });
        }
    },
    mounted() {
        Tabs()
        connectTabs
    }
}

function Tabs() {
  var bindAll = function() {
    var menuElements = document.querySelectorAll('[data-tab]');
    for(var i = 0; i < menuElements.length ; i++) {
      menuElements[i].addEventListener('click', change, false);
    }
  }

  var clear = function() {
    var menuElements = document.querySelectorAll('[data-tab]');
    for(var i = 0; i < menuElements.length ; i++) {
      menuElements[i].classList.remove('active');
      var id = menuElements[i].getAttribute('data-tab');
      document.getElementById(id).classList.remove('active');
    }
  }

  var change = function(e) {
    clear();
    e.target.classList.add('active');
    var id = e.currentTarget.getAttribute('data-tab');
    document.getElementById(id).classList.add('active');
  }

  bindAll();
}

var connectTabs = new Tabs();

</script>

<style scoped>
</style>