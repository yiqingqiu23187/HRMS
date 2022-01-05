<template>
  <v-app id="home">
    <v-navigation-drawer
        color="rgba(0,0,0,0)"
        enable-resize-watcher
        fixed
        app>
      <v-toolbar flat color="rgba(0,0,0,0)">
        <v-list>
          <v-list-item>
            <v-list-item-avatar>
              <img src="../../assets/logo.png" alt=""/>
            </v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title>{{this.$store.state.name}}</v-list-item-title>
              <v-list-item-subtitle>管理员</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-btn color="rgba(0,0,0,0)" @click="logout">登出</v-btn>
      </v-toolbar>
      <v-list flat>
        <v-list-item
            v-for="item in menuList"
            :key="item.id"
            router
            :to="item.path"
            @click="changeActive(item.id)"
            v-model="item.active"
            :style="isActiveList(item.id)"
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-main>
      <router-view/>
    </v-main>
  </v-app>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      dark: false,
      active: 1,
      menuList: [
        {
          "id": 1,
          "title": "首页",
          "path": "/hrHome",
          "icon": "mdi-home",
        },
        {
          "id": 2,
          "title": "评审加薪申请",
          "path": "/hrHome/viewAllSalaryApplications",
          "icon": "mdi-check-decagram",
        },
        {
          "id": 3,
          "title": "所有员工资料",
          "path": "/hrHome/viewAllEmployees",
          "icon": "mdi-account-box",
        },
        {
          "id": 4,
          "title": "新增员工",
          "path": "/hrHome/addEmployee",
          "icon": "mdi-account-multiple-plus",
        },
        {
          "id": 5,
          "title": "公告",
          "path": "/hrHome/viewAndSetNotice",
          "icon": "mdi-check-decagram",
        },
      ]
    }
  },
  methods: {
    isActiveList: function (id) {
      if (id === this.active)
        return {
          background: 'rgba(0,0,0,0.3)'
        }
      else
        return {
          background: 'rgba(0,0,0,0)'
        }
    },
    changeActive: function (id) {
      this.active = id;
    },


    logout: function () {
      this.$store.commit('logout');
      this.$router.replace("/");
    }
  }
}
</script>

<style scoped>
#home {
  background: url("../../assets/background.jpeg") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
</style>