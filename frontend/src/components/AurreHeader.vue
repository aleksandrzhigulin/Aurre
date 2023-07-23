<template>
    <header>
        <div class="container__wide">
            <div class="pages">
                <router-link to="/">Home</router-link>
                <router-link to="/about">About (for HRs)</router-link>
                <router-link to="/auth" v-if="!(this.isAuthorized)">Sign in/up</router-link>
                <a v-on:click.prevent="delCookie()" v-if="this.isAuthorized" style="cursor: pointer;">Logout</a>
            </div>
            <div class="user" v-if="this.isAuthorized">
                <div class="user__actions">
                  <router-link to="/createPost">Create post</router-link>
                </div>
                <div class="user__info">
                    <div class="user__avatar">
                        <img src="" alt="">
                    </div>
                    <p><router-link :to="'/profile/' + this.username">{{username}}</router-link></p>
                </div>
            </div>
        </div>
    </header>
</template>

<script>


export default {
    data() {
        return {
            isAuthorized: false,
            id: 0,
            username: ""
        }
    },
    methods: {
        delCookie() {
            deleteCookie("jwt");
            deleteCookie("username");
            location.reload();
        }
    },
    mounted() {
        if (getCookie("jwt") != null) {
            this.isAuthorized = true;
            this.axios
                .get("http://localhost:8080/user/get")
                .then(response => (this.id = response.data.id, this.username = response.data.username))

        }

    }
    
}
</script>

<style>

</style>