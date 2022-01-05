<template>
  <v-container>
    <v-card class="mx-auto top" max-width="60%">
      <v-card-title>
        当前工资详情
      </v-card-title>
      <v-card-subtitle>
        <v-row>
          <v-col cols="2">
            <v-avatar color="red">
              <span class="white--text text-h5">{{ this.firstName() }}</span>
            </v-avatar>
          </v-col>
          <v-col cols="8" class="font-weight-black font-weight-bold">
            <v-row>
              <v-col cols="4">工号: {{ this.userID }}</v-col>
            </v-row>
            <v-row>
              <v-col cols="4">姓名: {{ this.name }}</v-col>
            </v-row>
            <v-row>
              <v-col cols="4">部门: {{ this.departmentName }}</v-col>
            </v-row>
            <v-row>
              <v-col cols="4">工资总计: {{ this.totalSalary }}</v-col>
            </v-row>
          </v-col>
        </v-row>
      </v-card-subtitle>

      <v-card-actions>
        <v-btn color="orange lighten-2" text>
          查看详情
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn icon @click="salaryShow = !salaryShow">
          <v-icon>{{ salaryShow ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
        </v-btn>
      </v-card-actions>

      <v-expand-transition>
        <div v-show="salaryShow">
          <v-divider></v-divider>
          <v-card-text>
            <v-row>
              <v-col cols="6">部门底薪: {{ this.departmentSalary }}</v-col>
              <v-col cols="6">个人绩效: {{ this.personalSalary }}</v-col>
            </v-row>
          </v-card-text>
        </div>
      </v-expand-transition>
    </v-card>
    <v-card class="mx-auto top" max-width="60%">
      <v-card-title>
        如对当前工资存在疑问，可以进行以下操作?
      </v-card-title>

      <v-card-actions>
        <v-row justify="center">
          <v-col cols="3">
            <v-btn color="warning lighten-1" @click="this.openApplySalaryDialog">
              申请调薪
            </v-btn>
          </v-col>
          <v-col cols="3">
            <v-btn color="warning lighten-1" @click="this.openApplyDepartmentDialog">
              申请调动
            </v-btn>
          </v-col>
          <v-col cols="3">
            <v-btn color="primary lighten-2" @click="this.openViewHistoryDialogShow">
              查看申请
            </v-btn>
          </v-col>
        </v-row>
      </v-card-actions>
    </v-card>
    <v-dialog v-model="applySalaryDialogShow" transition="dialog-bottom-transition" max-width="600">
      <v-card>
        <v-toolbar color="primary" dark>填写加薪申请表</v-toolbar>
        <v-card-text class="top-20">
          <v-text-field v-model="newSalary" label="薪资" append-icon="mdi-cash" dense
                        ref="newSalary" outlined></v-text-field>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn @click="this.applySalary" class="primary">提交</v-btn>
          <v-btn @click="applySalaryDialogShow = false" class="error">关闭</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="applyDepartmentDialogShow" transition="dialog-bottom-transition" max-width="600">
      <v-card>
        <v-toolbar color="primary" dark>填写职位调动表</v-toolbar>
        <v-card-text class="top-20">
          <v-select
              v-model="newDepartment"
              :items="departmentNames"
              label="部门"
              outlined
          ></v-select>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn @click="this.applyDepartment" class="primary">提交</v-btn>
          <v-btn @click="applyDepartmentDialogShow = false" class="error">关闭</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="viewHistoryDialogShow" transition="dialog-bottom-transition" max-width="70%">
      <v-card>
        <v-toolbar color="primary" dark>过往申请记录</v-toolbar>
        <v-card-text class="top-20">
          <v-data-table
              :headers="headers"
              :items="myApplications"
              sort-by="calories"
              class="elevation-1">
            <template v-slot:item.type="{ item }">
              <v-chip v-if="item.type===0" class="primary">调薪申请</v-chip>
              <v-chip v-if="item.type===1" class="warning">调职申请</v-chip>
            </template>
            <template v-slot:item.time="{item}">
              <p>{{ timestampToDate(item.time) }}</p>
            </template>
            <template v-slot:item.status="{item}">
              <v-chip v-if="item.status===0" class="warning">待处理</v-chip>
              <v-chip v-if="item.status===1" class="success">已通过</v-chip>
              <v-chip v-if="item.status===2" class="error">已拒绝</v-chip>
            </template>
            <template v-slot:item.actions="{ item }">
              <v-btn color="primary" @click="viewApplicationDetails(item)">查看详情</v-btn>
            </template>
          </v-data-table>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn @click="viewHistoryDialogShow = false" class="error">关闭</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="applicationDetailsShow" transition="dialog-top-transition" max-width="50%">
      <v-card v-if="applicationDetails.type===0">
        <v-toolbar color="primary" dark>申请详情</v-toolbar>
        <v-card-text class="top-20">
          <v-row>
            <h1>调薪申请</h1>
          </v-row>
          <v-row>
            <v-col cols="4">初始薪资:{{ applicationDetails.oldValue }}</v-col>
            <v-col cols="4">目标薪资:{{ applicationDetails.newValue }}</v-col>
          </v-row>
          <v-row>
            <v-col v-if="applicationDetails.status===0">目前状态: 待处理</v-col>
            <v-col v-if="applicationDetails.status===1">目前状态: 已通过</v-col>
            <v-col v-if="applicationDetails.status===2">目前状态: 已拒绝</v-col>
          </v-row>
          <v-row v-if="applicationDetails.status===1">
            <h1 style="color: greenyellow">恭喜！申请成功</h1>
          </v-row>
          <v-row v-if="applicationDetails.status===2">
            <p>拒绝理由:{{ applicationDetails.reason }}</p>
          </v-row>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn @click="applicationDetailsShow = false" class="error">关闭</v-btn>
        </v-card-actions>
      </v-card>
      <v-card v-if="applicationDetails.type===1">
        <v-toolbar color="primary" dark>申请详情</v-toolbar>
        <v-card-text class="top-20">
          <v-row>
            <h1>调职申请</h1>
          </v-row>
          <v-row>
            <v-col cols="4">初始部门:{{ getDepartmentAccordingID(applicationDetails.oldValue) }}</v-col>
            <v-col cols="4">目标部门:{{ getDepartmentAccordingID(applicationDetails.newValue) }}</v-col>
          </v-row>
          <v-row>
            <v-col v-if="applicationDetails.status===0">目前状态: 待处理</v-col>
            <v-col v-if="applicationDetails.status===1">目前状态: 已通过</v-col>
            <v-col v-if="applicationDetails.status===2">目前状态: 已拒绝</v-col>
          </v-row>
          <v-row v-if="applicationDetails.status===1">
            <v-col><h1 style="color: greenyellow">恭喜！申请成功</h1></v-col>
          </v-row>
          <v-row v-if="applicationDetails.status===2">
            <v-col>拒绝理由:{{ applicationDetails.reason }}</v-col>
          </v-row>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn @click="applicationDetailsShow = false" class="error">关闭</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
export default {
  name: "ViewSalary",
  data() {
    return {
      app: this.$root.$children[0],
      salaryShow: false,
      userID: 11111,
      name: '胡桃',
      departmentID: 1,
      departmentName: '往生堂',
      departmentSalary: 10000,
      //positionDialog
      newDepartment: '销售部',
      departmentNames: ['销售部', '采购部'],
      departments: [],
      // departments: [{
      //   id: 1,
      //   name: '销售部'
      // }, {
      //   id: 2,
      //   name: '采购部'
      // }],
      personalSalary: 3000,
      totalSalary: 13000,
      applySalaryDialogShow: false,
      newSalary: 0,
      applyDepartmentDialogShow: false,
      viewHistoryDialogShow: false,
      myApplications: [],
      // myApplications: [{
      //   applicationID: 1,
      //   userID: 11111,
      //   name: '胡桃',
      //   type: 0,
      //   oldValue: 13000,
      //   newValue: 13500,
      //   time: (new Date()).getTime(),
      //   status: 0,
      //   reason: ''
      // }, {
      //   applicationID: 2,
      //   userID: 11111,
      //   name: '胡桃',
      //   type: 1,
      //   oldValue: 1,
      //   newValue: 2,
      //   time: (new Date()).getTime(),
      //   status: 1,
      //   reason: ''
      // }, {
      //   applicationID: 3,
      //   userID: 11111,
      //   name: '胡桃',
      //   type: 1,
      //   oldValue: 1,
      //   newValue: 2,
      //   time: (new Date()).getTime(),
      //   status: 2,
      //   reason: '给你脸了是吧，还调岗'
      // }, {
      //   applicationID: 1,
      //   userID: 11111,
      //   name: '胡桃',
      //   type: 0,
      //   oldValue: 13000,
      //   newValue: 13500,
      //   time: (new Date()).getTime(),
      //   status: 0,
      //   reason: ''
      // }, {
      //   applicationID: 2,
      //   userID: 11111,
      //   name: '胡桃',
      //   type: 1,
      //   oldValue: 1,
      //   newValue: 2,
      //   time: (new Date()).getTime(),
      //   status: 1,
      //   reason: ''
      // }, {
      //   applicationID: 3,
      //   userID: 11111,
      //   name: '胡桃',
      //   type: 1,
      //   oldValue: 1,
      //   newValue: 2,
      //   time: (new Date()).getTime(),
      //   status: 2,
      //   reason: '给你脸了是吧，还调岗'
      // }],
      headers: [
        {text: '申请单号', align: 'start', sortable: true, value: 'applicationID'},
        {text: '工号', align: 'start', sortable: false, value: 'userID'},
        {text: '姓名', align: 'start', sortable: false, value: 'name'},
        {text: '类型', align: 'start', sortable: true, value: 'type'},
        {text: '创建时间', align: 'start', sortable: true, value: 'time'},
        {text: '状态', align: "start", sortable: false, value: 'status'},
        {text: '操作', align: 'start', sortable: false, value: 'actions'}
      ],
      applicationDetailsShow: false,
      applicationDetails: {},
    }
  },
  methods: {
    firstName: function () {
      return this.name.charAt(0);
    },
    openApplySalaryDialog: function () {
      this.applySalaryDialogShow = true;
    },
    checkSalary: function () {
      let reg = /^\d+(?=\.{0,1}\d+$|$)/
      return reg.test(this.newSalary);
    },
    applySalary: function () {
      let isValid = this.checkSalary();
      if (!isValid) {
        this.app.message('请按照正确薪资格式输入', 'error');
        return;
      }
      this.$axios.post('/salary/apply', {
        userID: this.$store.state.userID,
        type: 0,
        newValue: parseInt(this.newSalary),
        time: (new Date()).getTime()
      }).then(resp => {
        if (resp.status === 200) {
          this.app.message("调薪申请已经发送，请等待通知", 'success');
          this.applySalaryDialogShow = false;
          this.newSalary = 0;
        } else {
          this.app.message("申请发送失败", 'error');
        }
      }).catch(() => {
        this.app.message('服务器在忙', 'error');
      })
    },
    //获取所有部门 id以及对应的name
    getAllDepartments: function () {
      this.$axios.get("/department/list", {}).then(resp => {
        if (resp.status === 200) {
          this.departments = resp.data;
          this.departmentNames.splice(0, this.departmentNames.length);
          for (let i = 0; i < this.departments.length; i++) {
            this.departmentNames.push(this.departments[i].departmentName);
          }
        }
      }).catch(() => {
        this.app.message('服务器在忙', 'error')
      })
    },
    openApplyDepartmentDialog: function () {
      this.getAllDepartments();
      this.applyDepartmentDialogShow = true;
    },

    applyDepartment: function () {
      let newDepartmentID = 0;
      for (let i = 0; i < this.departments.length; i++) {
        let temp = this.departments[i];
        if (temp.name === this.newDepartment)
          newDepartmentID = temp.id;
      }
      this.$axios.post('/salary/apply', {
        userID: this.$store.state.userID,
        type: 1,
        newValue: newDepartmentID,
        time: ((new Date()).getTime())
      }).then(resp => {
        if (resp.status === 200) {
          this.app.message("部门调动申请已经发送，请等待通知", 'success');
          this.applyDepartmentDialogShow = false;
        } else {
          this.app.message("申请发送失败", 'error');
        }
      })
    },

    openViewHistoryDialogShow: function () {
      this.viewHistoryDialogShow = true;
      this.$axios.get('/salary/allApplications', {
        params: {
          userID: this.$store.state.userID,
        }
      }).then(resp => {
        console.log(resp)
        if (resp.status === 200) {
          this.myApplications = resp.data;
        }
        console.log(this.myApplications);
      }).catch(() => {
        this.app.message('服务器在忙', 'error');
      })

    },

    viewApplicationDetails: function (item) {
      this.applicationDetails = item;
      this.applicationDetailsShow = true;
      this.getAllDepartments();
      // this.$axios.get('/salary/allApplications', {
      //   params: {
      //     userID: this.$store.state.userID
      //   }
      // }).then(resp => {
      //   console.log(resp);
      //   if (resp.status === 200) {
      //     this.myApplications = resp.data.applications;
      //   }
      // }).catch(() => {
      //   this.app.message('服务器在忙', 'error');
      // })
    },

    getDepartmentAccordingID: function (id) {
      let res = '';
      console.log(id);
      for (let i = 0; i < this.departments.length; i++) {
        let temp = this.departments[i];
        if (temp.departmentID == id)
          res = temp.departmentName;
      }
      return res;
    },

    timestampToDate(timestamp) {
      Date.prototype.format = function (format) {
        let o = {
          "M+": this.getMonth() + 1, //month
          "d+": this.getDate(),    //day
          "h+": this.getHours(),   //hour
          "m+": this.getMinutes(), //minute
          "s+": this.getSeconds(), //second
          "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
          "S": this.getMilliseconds() //millisecond
        }
        if (/(y+)/.test(format)) format = format.replace(RegExp.$1,
            (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o) if (new RegExp("(" + k + ")").test(format))
          format = format.replace(RegExp.$1,
              RegExp.$1.length === 1 ? o[k] :
                  ("00" + o[k]).substr(("" + o[k]).length));
        return format;
      }
      let d = new Date(timestamp);
      return d.format('yyyy-MM-dd hh:mm:ss');
    },
  },
  created: function () {
    this.$axios.get('/salary/get', {
      params: {
        userID: this.$store.state.userID
      },
    }).then(resp => {
      if (resp.status === 200) {
        this.name = resp.data.name;
        this.departmentID = resp.data.departmentID;
        this.departmentName = resp.data.departmentName;
        this.departmentSalary = resp.data.departmentSalary;
        this.personalSalary = resp.data.personalSalary;
        this.totalSalary = resp.data.totalSalary;
      }
    }).catch(() => {
      // this.app.message('服务器在忙','error');
    })
  }

}
</script>

<style scoped>
.top {
  margin-top: 100px;
}

.top-20 {
  margin-top: 20px;
}
</style>