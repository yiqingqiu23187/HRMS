<template>
  <div>
    <v-data-table
        :headers="headers"
        :items="personalInformation"
        :items-per-page="10"
        class="elevation-1"
    >
    </v-data-table>
  </div>
</template>

<script>
export default {
  name: "personalInformation",
  data: () => ({
    personalInformation: [],
    headers: [
      {text: "姓名", value: "name"},
      {text: "性别", value: "sex"},
      {text: "联系电话", value: "phoneNumber"},
      {text: "地址", value: "address"},
      {text: "所在部门", value: "departmentName"},
      {text: "工资", value: "totalSalary"}
    ]
  }),
  created() {
    this.getPersonalInformation();
  },
  methods: {
    // 获取员工列表
    getPersonalInformation() {
      this.axios.get("/employee/personalInformation", {
        params: {
          userID: this.$store.state.userID,
        }
      })
          .then(resp => {
            if (resp.status !== 200) {
              // 报错提示
            }
            this.personalInformation = resp.data;
          });
    }
  }

}
</script>

<style scoped>

</style>
