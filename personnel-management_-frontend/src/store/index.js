import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        userID: localStorage.getItem('userID') || null,
        name: localStorage.getItem('name') || null,
        userAuth: localStorage.getItem('userAuth') || null,
    },
    mutations: {
        login(state, data) {
            localStorage.setItem('userID', data.userID);
            localStorage.setItem('name', data.name);
            localStorage.setItem('userAuth', data.userAuth);
            state.userID = data.userID;
            state.name = data.name;
            state.userAuth = data.userAuth;
        },
        logout(state) {
            //移除相关信息
            localStorage.removeItem('userID');
            localStorage.removeItem('name');
            localStorage.removeItem('userAuth');
            state.userID = null;
            state.name = null;
            state.userAuth = null;
        }
    },
    actions: {},
    modules: {},
    getters:{
    }
})
