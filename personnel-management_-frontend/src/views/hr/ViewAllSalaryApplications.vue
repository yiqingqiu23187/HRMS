<template>
  <v-container>
    <v-card class="top-50">
      <v-toolbar color="primary" dark>所有未批申请</v-toolbar>
      <v-card-text class="top-20">
        <v-data-table
            :headers="headers"
            :items="allApplications"
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
          <template v-slot:item.actions="{item}">
            <v-btn color="primary" @click="viewApplicationDetails(item)">查看详情</v-btn>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>
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
          <v-row v-if="applicationDetails.status===0">
            <v-text-field v-model="refuseReason" label="评审建议" append-icon="mdi-fountain-pen" dense
                          placeholder="若拒绝请填写拒绝理由"
                          ref="newSalary" outlined></v-text-field>
          </v-row>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn @click="agreeApplication" class="success" v-if="applicationDetails.status===0">批准</v-btn>
          <v-btn @click="refuseApplication" class="warning" v-if="applicationDetails.status===0">拒绝</v-btn>
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
            <v-col cols="4">初始部门:{{ this.getDepartmentAccordingID(applicationDetails.oldValue) }}</v-col>
            <v-col cols="4">目标部门:{{ this.getDepartmentAccordingID(applicationDetails.newValue) }}</v-col>
          </v-row>
          <v-row>
            <v-col v-if="applicationDetails.status===0">目前状态: 待处理</v-col>
            <v-col v-if="applicationDetails.status===1">目前状态: 已通过</v-col>
            <v-col v-if="applicationDetails.status===2">目前状态: 已拒绝</v-col>
          </v-row>
          <v-row v-if="applicationDetails.status===0">
            <v-text-field v-model="refuseReason" label="评审建议" append-icon="mdi-fountain-pen" dense
                          placeholder="若拒绝请填写拒绝理由"
                          ref="refuseReason" outlined></v-text-field>
          </v-row>
        </v-card-text>
        <v-card-actions class="justify-end">
          <v-btn @click="agreeApplication" class="success" v-if="applicationDetails.status===0">批准</v-btn>
          <v-btn @click="refuseApplication" class="warning" v-if="applicationDetails.status===0">拒绝</v-btn>
          <v-btn @click="applicationDetailsShow = false" class="error">关闭</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-container>
</template>

<script>
export default {
  name: "ViewAllSalaryApplications",
  data() {
    return {
      app: this.$root.$children[0],
      departments: [],
      allApplications: [],
      headers: [
        {text: '申请单号', align: 'start', sortable: true, value: 'applicationID'},
        {text: '工号', align: 'start', sortable: false, value: 'userID'},
        {text: '类型', align: 'start', sortable: true, value: 'type'},
        {text: '创建时间', align: 'start', sortable: true, value: 'time'},
        {text: '状态', align: "start", sortable: true, value: 'status'},
        {text: '操作', align: 'start', sortable: false, value: 'actions'}
      ],
      applicationDetailsShow: false,
      applicationDetails: {},
      refuseReason: ''
    }
  },
  methods: {
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

    getDepartmentAccordingID: function (id) {
      let res = '';
      for (let i = 0; i < this.departments.length; i++) {
        let temp = this.departments[i];
        if (temp.departmentID == id)
          res = temp.departmentName;
      }
      return res;
    },

    viewApplicationDetails(item) {
      this.$axios.get("/department/list", {}).then(resp => {
        if (resp.status === 200) {
          this.departments = resp.data;
        }
        this.applicationDetails = item;
        this.applicationDetailsShow = true;
      }).catch(() => {
        this.app.message('服务器在忙', 'error')
      })
    },
    agreeApplication: function () {
      this.$axios.post('/salary/handler', {
        applicationID: this.applicationDetails.applicationID,
        agree: true,
        reason: ''
      }).then(resp => {
        if (resp.status === 200) {
          if (resp.data.success) {
            for (let i = 0; i < this.allApplications.length; i++) {
              if (this.allApplications[i].applicationID === this.applicationDetails.applicationID) {
                this.allApplications[i].status = 1;
              }
            }
            this.applicationDetailsShow = false;
          }
        }
      }).catch(() => {
        this.app.message('服务器在忙', 'error');
      })
    },
    refuseApplication: function () {
      if (this.refuseReason.length <= 0) {
        this.app.message("请填写拒绝理由", 'error');
        return;
      }

      this.$axios.post('/salary/handler', {
        applicationID: this.applicationDetails.applicationID,
        agree: false,
        reason: this.refuseReason
      }).then(resp => {
        if (resp.status === 200) {
          if (resp.data.success) {
            for (let i = 0; i < this.allApplications.length; i++) {
              if (this.allApplications[i].applicationID === this.applicationDetails.applicationID) {
                this.allApplications[i].status = 2;
              }
            }
            this.applicationDetailsShow = false;
          }
        }
      }).catch(() => {
        this.app.message('服务器在忙', 'error');
      })
    },
  },
  created: function () {
    this.$axios.get('/salary/allApplications', {
      params: {
        userID: this.$store.state.userID,
      }
    }).then(resp => {
      if (resp.status === 200) {
        this.allApplications = resp.data;
      }
    }).catch(() => {
      this.app.message('服务器在忙', 'error');
    })
  }
}
</script>

<style scoped>
.top-50 {
  margin-top: 50px;
}

.top-20 {
  margin-top: 20px;
}
</style>