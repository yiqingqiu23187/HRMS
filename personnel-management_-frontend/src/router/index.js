import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from '../views/Login.vue'
import UserHome from '../views/user/Home.vue'
import ViewSalary from "../views/user/ViewSalary";
import UserWelcome from "../views/user/Welcome";
import HRHome from "../views/hr/Home.vue"
import HRWelcome from "../views/hr/Welcome.vue"
import ViewAllSalaryApplications from "../views/hr/ViewAllSalaryApplications";
import viewAllEmployees from "@/views/hr/viewAllEmployees";
import addEmployee from "@/views/hr/addEmployee";
import personalInformation from "@/views/user/personalInformation";
import ViewAndSetNotice from "../views/hr/ViewAndSetNotice";
import ViewNotice from "../views/user/ViewNotice";
import testView from "../views/hr/test";

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Login',
        component: Login
    },
    {
        path: '/userHome',
        name: 'userHome',
        component: UserHome,
        children: [
            {
                path: '',
                name: 'UserWelcome',
                component: UserWelcome
            },
            {
                path: 'viewSalary',
                name: 'ViewSalary',
                component: ViewSalary
            },
            {
                path: 'personalInformation',
                name: 'personalInformation',
                component: personalInformation,
            },
            {
                path: 'viewNotice',
                name: 'ViewNotice',
                component: ViewNotice
            },
        ]
    }, {
        path: '/hrHome',
        name: 'hrHome',
        component: HRHome,
        children: [
            {
                path: '',
                name: 'HRWelcome',
                component: HRWelcome
            },
            {
                path: 'viewAllSalaryApplications',
                name: 'ViewAllSalaryApplications',
                component: ViewAllSalaryApplications
            },
            {
                path: 'viewAllEmployees',
                name: 'viewAllEmployees',
                component: viewAllEmployees
            },
            {
                path: 'addEmployee',
                name: 'addEmployee',
                component: addEmployee
            }, {
                path: 'viewAndSetNotice',
                name: 'ViewAndSetNotice',
                component: ViewAndSetNotice
            }, {
                path: 'testView',
                name: 'testView',
                component: testView
            }]
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
