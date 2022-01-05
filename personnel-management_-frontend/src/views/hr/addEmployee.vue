<template>
  <v-form ref="form" v-model="valid" lazy-validation>
    <v-text-field
        v-model="employee.name"
        :rules="nameRules"
        :counter="10"
        label="员工姓名"
        required
    ></v-text-field>
    <v-select
        v-model="employee.sex"
        :items="sex"
        :rules="[v => !!v || '必须填写员工性别']"
        label="员工性别"
        required
    ></v-select>
    <v-text-field
        v-model="employee.phoneNumber"
        :rules="phoneNumberRules"
        label="电话号码"
        required
    ></v-text-field>
    <v-text-field
        v-model="employee.address"
        :rules="[v => !!v || '必须填写员工家庭住址']"
        label="员工住址"
        required
    ></v-text-field>
    <v-text-field
        v-model="employee.totalSalary"
        :rules="salaryRules"
        label="员工薪水"
        required
    ></v-text-field>
    <v-select
        v-model="employee.departmentName"
        :items="departmentName"
        :rules="[v => !!v || '必须选择员工部门']"
        label="分配部门"
        required
    ></v-select>

    <v-btn
        :disabled="!valid"
        @click="submit"
    >submit
    </v-btn>

  </v-form>
</template>

<script>
export default {
  name: "addEmployee",
  data() {
    return {
      valid: false, // 表单校验结果标记
      sex: ["男", "女"],
      departmentName: [],
      employee: {
        name: '', // 员工姓名
        sex: '',
        phoneNumber: '',
        address: '',
        departmentName: '',
        totalSalary: '',
      },
      nameRules: [
        v => !!v || "员工姓名不能为空",
        v => v.length > 1 || "员工姓名至少2位"
      ],
      phoneNumberRules: [
        v => !!v || "员工电话不能为空",
        v => /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/.test(v) || "请输入有效手机号"
      ],
      salaryRules: [
        v => !!v || "员工薪水不能为空",
      ],

    }
  },
  created:function () {
        let that=this;
        this.$axios.get('/department/nameList')
            .then(resp => {
              if (resp.status === 200) {
                that.departmentName = resp.data;
              } else {
                this.$message.error('后端响应不是200');
              }
            })
            .catch(error => {
              console.log(error);
              this.$message.error('获取部门列表时出错');
            })
      },

  methods: {
    submit() {
      this.$axios.post('/employee/addEmployee', {
        name: this.employee.name,
        sex: this.employee.sex,
        phoneNumber: this.employee.phoneNumber,
        address: this.employee.address,
        departmentName:this.employee.departmentName,
        totalSalary: this.employee.totalSalary
      })
          .then(resp => {
            if (resp.status === 200) {
              this.$message.success("员工添加成功")
              this.$router.replace('/hrHome')
            } else
              this.$message.error('员工添加失败');
          })
          .catch(error => {
            console.log(error);
            this.$message.error('捕捉到错误');
          })
    },

  }
}
</script>

<style scoped>

</style>
