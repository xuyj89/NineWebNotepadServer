<template>
  <el-dialog title="注册" v-model="dialogRegisterVisible">
    <el-row>
      <el-col :span="4"><span>密码：</span></el-col>
      <el-col :span="20">
        <el-input
          v-model="registerPassword"
          placeholder="请输入密码"
          show-password
        ></el-input
      ></el-col>
    </el-row>
    <template #footer>
      <el-button type="success" v-on:click="registerClicked">注册</el-button>
    </template>
  </el-dialog>
  <el-dialog title="修改密码" v-model="dialogChangePasswordVisible">
    <template #footer> </template>
  </el-dialog>
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
import { ElMessage } from 'element-plus'
import NetUtils from "../utils/netUtils";
import md5 from 'js-md5'

export default {
  name: "Login",
  props: {
    msg: String,
  },
  methods: {
    setPassword() {
      NetUtils.get(this, "/user/havePassword", (result) => {
        if (result.code == 0) {
          if (result.data == 1) {
            //已经注册过
            this.dialogChangePasswordVisible = true;
          } else {
            //没有注册过
            this.dialogRegisterVisible = true;
          }
        } else {
          ElMessage(result.error);
        }
      });
    },
    registerClicked(){
      console.log("点击注册");
      let passwordMd5 = md5(this.registerPassword);
      NetUtils.post(this,"/user/register?password="+passwordMd5,(result)=>{
        if (result.code == 0) {
          let token = result.data;
          ElMessage("注册成功，即将进入系统主页");
        } else {
          ElMessage(result.error);
        }
      });
    }
  },
  data() {
    return {
      password: "",
      dialogRegisterVisible: false,
      dialogChangePasswordVisible: false,
      registerPassword:""
    };
  },
};
</script>
