<template>
  <v-row justify="center">
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
            <v-textarea
                outlined
                name="input-7-4"
                label="*请填写具体内容"
                value=" " ></v-textarea>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">取消</v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">提交</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  data: () => ({
    dialog: false,
  }),
}
</script>