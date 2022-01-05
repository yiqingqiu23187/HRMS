import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import Message from './components/Message/index.js'
// import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.prototype.$message = Message
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
