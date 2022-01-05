<template>
  <v-app style="overflow: hidden!important;">
    <v-overlay :value="overlay" z-index="500">
      <v-progress-circular indeterminate size="64"></v-progress-circular>
    </v-overlay>
    <v-snackbar v-model="showSnackbar" :timeout="2000" :color="snackbarType" top>{{ snackbarMessage }}</v-snackbar>
    <router-view/>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  data: () => ({
    overlay: false,
    showSnackbar: false,
    snackbarMessage: '',
    snackbarType: '',
  }),

  methods: {
    //此处类似ElementUI做了一个类比
    //后续大家需要用到提示框的时候，记得在data中存贮 app=this.$root.$children[0]
    //然后调用this.app.message("tips","red")
    message(message, type) {
      this.showSnackbar = true;
      this.snackbarMessage = message;
      this.snackbarType = type;
    },
  }
};
</script>
