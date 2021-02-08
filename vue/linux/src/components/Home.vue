<template>
  <div>
    <table class="table table-hover">
      <thead>
        <th>服务</th>
        <th id="status">状态</th>
      </thead>
      <tbody v-show="isShow('FireWall')">
        <td><label for="FireWall">防火墙</label></td>
        <td><input type="checkbox" name="FireWall" id="FireWall"></td>
      </tbody>
      <tbody v-show="isShow('HDFS')">
        <td><label for="HDFS">HDFS</label></td>
        <td><input type="checkbox" name="HDFS" id="HDFS"></td>
      </tbody>
      <tbody v-show="isShow('Yarn')">
        <td><label for="Yarn">Yarn</label></td>
        <td><input type="checkbox" name="Yarn" id="Yarn"></td>
      </tbody>
      <tbody v-show="isShow('Mysql')">
        <td><label for="Mysql">Mysql</label></td>
        <td><input type="checkbox" name="Mysql" id="Mysql"></td>
      </tbody>
      <tbody v-show="isShow('Hive')">
        <td><label for="Hive">Hive</label></td>
        <td><input type="checkbox" name="Hive" id="Hive"></td>
      </tbody>
      <tbody v-show="isShow('HBase')">
        <td><label for="HBase">HBase</label></td>
        <td><input type="checkbox" name="HBase" id="HBase"></td>
      </tbody>
      <tbody v-show="isShow('Zookeeper')">
        <td><label for="Zookeeper">Zookeeper</label></td>
        <td><input type="checkbox" name="Zookeeper" id="Zookeeper"></td>
      </tbody>
      <tbody v-show="isShow('Kafka')">
        <td><label for="Kafka">Kafka</label></td>
        <td><input type="checkbox" name="Kafka" id="Kafka"></td>
      </tbody>
      <tbody v-show="isShow('Spark')">
        <td><label for="Spark">Spark</label></td>
        <td><input type="checkbox" name="Spark" id="Spark"></td>
      </tbody>
      <tbody v-show="isShow('Redis')">
        <td><label for="Redis">Redis</label></td>
        <td><input type="checkbox" name="Redis" id="Redis"></td>
      </tbody>
      <tbody v-show="isShow('Flink')">
        <td><label for="Flink">Flink</label></td>
        <td><input type="checkbox" name="Flink" id="Flink"></td>
      </tbody>
    </table>
  </div>
</template>

<script>
import bootstrapSwitch from '../assets/js/bootstrap-switch'
import axios from 'axios'
axios.defaults.withCredentials = true;// 允许跨域携带cookie
export default {
  name: "Home",
  data(){
    return{
      total: [],
    }
  },
  methods:{
    SwitchInit() {
      for (let e of this.total) {
        $('#' + e.name).bootstrapSwitch({
          onText: "ON",
          offText: "OFF",
          onColor: "success",
          offColor: "danger",
          onSwitchChange(event, state) {
            console.log(state)
            let switchID = event.target.id;
            let _this = this;
            var params = new URLSearchParams();
            params.append("active", state);
            axios.post('http://localhost:8989/linux/active/' + switchID, params).then(function (res) {
              let code = res.data.code;
              let msg = res.data.message;
              let map = res.data.map;
              if (code == 401) {
                alert(msg)
              } else if (code == 500) {
                alert(msg + map.error)
              } else if (code == 200) {
                e.status=state;
              } else {
                alert("未知错误");
                console.log(code + "  " + msg + "  " + map)
              }
            })
          }
        }).bootstrapSwitch('state',e.status,true);
      }
    },
    TotalInit(){
      let _this=this;
      axios.get('http://localhost:8989/linux/status/all').then(function (res) {
        let code = res.data.code;
        let msg = res.data.message;
        let map = res.data.map;
        if (code==401){
          _this.$router.push('/login');
          alert(msg)
        }else if (code==500){
          alert(msg)
        } else {
          _this.total=map.data;
        }
      })
    },
    isShow(name){
      for (let e of this.total) {
        if (e.name == name)
          return true;
      }
      return false;
    },
  },
  mounted() {
    setTimeout(() => {
      this.SwitchInit();
    }, 2000)
  },
  created() {
    this.TotalInit();
  },
}
</script>

<style scoped>
  @import 'https://cdn.bootcdn.net/ajax/libs/bootstrap-switch/4.0.0-alpha.1/css/bootstrap-switch.css';
  #status {
    padding-left: 60px;
  }
</style>
