<template>
  <div>
    <v-card>
      <v-card-title>
        <v-btn small raised color="primary">所有员工资料</v-btn>
        <v-spacer></v-spacer>
        <v-text-field
            v-model="search"
            append-icon="mdi-search"
            label="Search"
            single-line
            hide-details
            @keyup.enter="searchChanged"
            @click:append="searchChanged"
        ></v-text-field>
      </v-card-title>
      <v-data-table
          :headers="headers"
          :items="employees"
          :items-per-page="10"
          class="elevation-1"
      >
      </v-data-table>
    </v-card>
  </div>
</template>

<script>
export default {
  data: () => ({
    search: "",
    employees: [],
    headers: [
      { text: "姓名", value: "name" },
      { text: "性别", value: "sex"},
      { text: "联系电话", value: "phoneNumber" },
      { text: "家庭电话", value: "address" },
      { text: "所在部门", value: "departmentName"},
      { text: "工资", value: "totalSalary"}
    ]
  }),

  created() {
    this.getEmployeeList();
  },

  methods: {
    // 获取员工列表
    getEmployeeList() {
      console.log(this.search)
      this.axios
          .get("/employee/allInformation", {
            params: {
              search: this.search,
            }
          })
          .then(resp => {
            if (resp.status !== 200) {
              // 报错提示
            }
            this.employees = resp.data;
          });
    },

    searchChanged() {
      if (this.search !== "") {
        // console.log(this.search);
        this.getEmployeeList();
      }
    },
  },
  components: {}
};
</script>
<style lang='scss' scoped>
</style>
