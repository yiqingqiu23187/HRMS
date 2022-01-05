<template>
  <v-app id="login">
    <v-main>
      <v-container fluid>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="5">
            <v-card shaped id="loginBase">
              <v-card-text>
                <v-toolbar flat color="rgba(255,255,255,0)">
                  <v-card-title class="display-1">登录</v-card-title>
                  <v-spacer/>
                </v-toolbar>
                <v-form ref="form" class="ma-7 mb-n5">
                  <v-text-field v-model="userID" label="userID" append-icon="mdi-account"
                                ref="userID" outlined
                                :rules="userIDRules"></v-text-field>
                  <v-text-field v-model="password" label="password" append-icon="mdi-fingerprint"
                                ref="password" outlined type="password" @keyup.enter="login"
                                :rules="passwordRules"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions class="mr-7">
                <v-spacer></v-spacer>
                <v-btn color="primary" text @click="resetForm" class="title">重置</v-btn>
                <v-btn color="primary" text @click="login" class="title">确认</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      app: this.$root.$children[0],
      imgSrc: require("../assets/background.jpeg"),
      userID: '',
      userIDRules: [v => !!v || '用户名不为空'],
      password: '',
      passwordRules: [v => !!v || '密码不为空'],
      formHasErrors: false,
    }
  },
  computed: {
    form: function () {
      return {
        userID: this.userID,
        password: this.password
      }
    }
  },
  methods: {
    test: function () {
      this.app.message("登录失败", "rgba(255,0,0,0.6)");
    },
    resetForm: function () {
      this.formHasErrors = false;
      Object.keys(this.form).forEach(f => {
        this.$refs[f].reset();
      })
    },
    checkForm: function () {
      this.formHasErrors = false;
      Object.keys(this.form).forEach(f => {
        this.$refs[f].validate(true);
        if (!this.$refs[f].valid) {
          this.formHasErrors = true;
        }
      })
    },
    login: function () {
      if (this.app.overlay === true)
        return;
      this.checkForm();
      if (!this.formHasErrors) {
        this.app.overlay = true;
        this.$axios.get("/login", {
          params: {
            userID: parseInt(this.userID),
            password: this.password
          }
        }).then(resp => {
          if (resp.status === 200) {
            this.$store.commit('login', resp.data);
            this.app.message("登录成功", "success")
            if (this.$store.state.userAuth === 1) {
                this.$router.replace('/hrHome');
            }else{
                this.$router.replace("/userHome");
            }
          } else {
            this.app.message("登陆失败", 'warning')
          }
        }).catch(err => {
          console.log(err);
          this.app.message("服务器在忙", 'rgba(255,0,0,1)');
        })
      }
      this.app.overlay = false;
    }
  }
}
</script>

<style scoped>
#login {
  background: url("../assets/background.jpeg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}

#loginBase {
  margin-top: 200px;
}
</style>