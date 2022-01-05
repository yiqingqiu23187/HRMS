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
                  <span class="subheading">{{ timestampToDate(item.time) }} </span>
                </v-row>
              </v-list-item>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-card>
</template>

<script>
export default {
  name: "ViewAndSetNotice",
  data() {
    return {
      app: this.$root.$children[0],
      notices: [],
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
  },
  created: function () {
    this.$axios.get('/notice/list', {}).then(resp => {
      console.log(resp);
      if (resp.status === 200) {
        this.notices = resp.data;
      }
    }).catch(() => {
      this.app.message('服务器在忙', 'error');
    })

  },
}
</script>