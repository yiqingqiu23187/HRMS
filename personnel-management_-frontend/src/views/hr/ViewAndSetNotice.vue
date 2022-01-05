<template>
  <v-card
      max-width="800"
      class="mx-auto"
  >
    <v-container>
      <v-row dense>
        <v-col
            v-for="(item, i) in notices"
            :key="i"
            cols="12"
        >
          <v-card
              class="mx-auto"
              color="#26c6da"
              dark
              max-width="800"
          >
            <v-card-title>
              <v-icon
                  large
                  left
              >
                mdi-email
              </v-icon>
              <span class="subheading">Notice</span>
              <i class="icon ion-speakerphone"></i>
            </v-card-title>

            <v-card-text class="headline font-weight-bold" v-text="item.notice">
            </v-card-text>

            <v-card-actions>
              <v-list-item class="grow">
                <v-list-item-avatar color="grey darken-3">
                  <v-img
                      class="elevation-6"
                      src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                  ></v-img>
                </v-list-item-avatar>

                <v-list-item-content>
                  <v-list-item-title>管理员</v-list-item-title>
                </v-list-item-content>

                <v-row
                    align="center"
                    justify="end"
                >
                  <span class="subheading" >{{timestampToDate(item.time)}} </span>
                </v-row>
              </v-list-item>
            </v-card-actions>
          </v-card></v-col>

<!--   new Notice     -->
        <v-col align="center" >
            <v-dialog v-model="dialog" persistent max-width="600px">
              <template v-slot:activator="{ on, attrs }">
                <v-btn
                    class="mx-2" fab color="primary"
                    dark
                    v-bind="attrs"
                    v-on="on">
                  <v-icon dark>mdi-plus</v-icon>
                </v-btn>
              </template>
              <v-card>
                <v-card-title>
                  <v-icon
                      large
                      left
                  >
                    mdi-email
                  </v-icon>
                  <span>新通知</span>
                </v-card-title>
                <v-card-text>
                  <v-textarea v-model = "newContent"
                      outlined
                      name="input-7-4"
                      label="*请填写具体内容"
                      value="" ></v-textarea>
                </v-card-text>
                <v-card-actions>
                  <v-spacer></v-spacer>
                  <v-btn color="blue darken-1" text @click="dialog = false">取消</v-btn>
                  <v-btn color="blue darken-1" text @click="applyNotice">提交</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>

       </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name : "ViewAndSetNotice",
  data () {
    return {
      app: this.$root.$children[0],
      dialog:false,
      newContent:'',
      notices: [],
    }
  },
  created:function () {
    this.$axios.get('/notice/list',{
    }).then(resp => {
      if (resp.status === 200) {
        this.notices = resp.data;
      }
    }).catch(() => {
      this.app.message('服务器在忙', 'error');
    })

  },
  methods:{
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
    checkContent:function (){
      if (this.newContent.length <= 0) {
        this.app.message("请填写通知内容", 'error');
        return false;
      }
      return true;
    },
    applyNotice:function (){
      if (this.newContent.length <= 0) {
        this.app.message("请填写通知内容", 'error');
        return ;
      }else {
        this.axios.post('/notice/release',{
          userID: this.$store.state.userID,
          notice:this.newContent
        })
        this.app.message("提交成功", 'sucess');
        this.newContent = '';
        this.dialog = false;
      }

    }

  },
}
</script>