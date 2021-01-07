<template>
  <div class="login_border">
    <h1 class="login_title">9号Web记事本</h1>
    <hr />
    <div>
      <el-row class="password_border">
        <el-col :span="4"><span>密码：</span></el-col>
        <el-col :span="20"
          ><el-input
            v-model="password"
            placeholder="请输入密码"
            show-password
          ></el-input
        ></el-col>
      </el-row>
    </div>
    <div class="button_border"></div>
    <el-row :gutter="20">
      <el-col :span="12"
        ><el-button type="success" class="btn_login">登录</el-button></el-col
      >
      <el-col :span="12"
        ><el-button
          type="warning"
          class="btn_set_password"
          v-on:click="setPassword"
          >设置/修改密码</el-button
        ></el-col
      >
    </el-row>
  </div>
</template>

<style scoped>
.login_border {
  width: 500px;
  margin: 50px auto 0px auto;
}
.login_title {
  text-align: center;
}
.password_border {
  margin-top: 20px;
}
.button_border {
  margin-top: 50px;
}
.btn_login {
  width: 100%;
}
.btn_set_password {
  width: 100%;
}
</style>

<script>
export default {
  name: "Login",
  props: {
    msg: String,
  },
  methods: {
    setPassword() {
      this.$store.state.netUtils.get(this,"/user/havePassword",(result)=>{
        console.log(result);
      });
    },
  },
  data() {
    return {
      password: "",
    };
  },
};
</script>
