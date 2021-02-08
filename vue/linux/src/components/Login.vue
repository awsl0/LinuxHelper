<template>
  <div id="background">
    <div class="container">
      <div class="col-md-4" style="background-color:#eee;;margin-top:200px;margin-left: 350px">
        <center class="h2">Linux小助手</center>
        <form action="">
          <div class="form-group">
            <label for="ip">IP地址:</label>
            <input type="text" class="form-control" id="ip" v-model="loginObject.ipAddr">
          </div>
          <div class="form-group">
            <label for="port">端口:</label>
            <input type="text" class="form-control" id="port" v-model="loginObject.port">
          </div>
          <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" class="form-control" id="username" v-model="loginObject.userName">
          </div>
          <div class="form-group">
            <label for="password">密码</label>
            <input type="password" class="form-control" id="password" v-model="loginObject.password">
          </div>
          <div class="form-group">
            <input type="submit" value="登录" @click="login" class="btn btn-success form-control">
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
axios.defaults.withCredentials = true;// 允许跨域携带cookie
export default {
  name: "Login",
  data(){
    return{
      loginObject: {ipAddr:"",port:"",userName:"",password:""},
    }
  },
  methods:{
    login(){
      let _this=this;
      axios.post("http://localhost:8989/linux/login",_this.loginObject)
        .then(function (res){
          let code = res.data.code;
          let msg = res.data.message;
          console.log(code+"  "+msg)
          if (code!=200){
            alert(msg)
          } else {
            _this.$router.push('/home');
          }
      })
    },
  },
}
</script>
<style scoped>
#background {
  position:absolute;
  background-image: url("../assets/bakeground.jpg");
  padding-bottom: 70%;
  width:100%;
  height:100%;
  background-position:center;
}
</style>
