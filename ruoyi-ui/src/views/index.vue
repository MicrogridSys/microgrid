<template>
  <div class="app-container home">
    <el-row>
      <el-col :span="18" style="padding-left: 10px">
        <el-row>
          <div class="title-container">
            <h1 class="title">微电网调度监控系统</h1>
          </div>
          <el-switch
              font-size="12px"
              style="display: block"
              v-model="switch_button"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="联网模式"
              inactive-text="孤岛模式">
            </el-switch>
        </el-row>

        <el-divider /><!-- 分割线 -->
        
        <el-row :gutter="24" type="flex" justify="space-around">  <!-- 第一排卡牌 -->
          <el-col :span="6">
            <el-card class="box-card" shadow="never" v-for="powerPv in powerPvs">
              <div class="clearfix text_primary" property="name">
                <i class="el-icon-edit"></i>
                <div>光伏发电</div>
              </div>
              <!-- 第一行数据 -->
              <el-row :gutter="0" class="text_item">
                <el-col :span="8" >
                  <div class="item">电流</div>
                </el-col>
                <el-col :span="14">
                  <div class="item">
                    <span class="text_data">{{ (powerPv.pvCurrent/2).toFixed(2) }}</span>
                  </div>
                </el-col>
                <el-col :span="2">
                  <div class="item">A</div>
                </el-col>
              </el-row>
              <!-- 第二行数据 -->
              <el-row :gutter="0" class="text_item">
                <el-col :span="8" >
                  <div class="item">电压</div>
                </el-col>
                <el-col :span="14">
                  <div class="item">
                    <span class="text_data">{{ powerPv.pvVoltage.toFixed(2) }}</span>
                  </div>
                </el-col>
                <el-col :span="2">
                  <div class="item">V</div>
                </el-col>
              </el-row>
              <!-- 第三行数据 -->
              <el-row :gutter="0" class="text_item">
                <el-col :span="8" >
                  <div class="item">功率</div>
                </el-col>
                <el-col :span="14">
                  <div class="item">
                    <span class="text_data">{{ (powerPv.pvPower/2).toFixed(2) }}</span>
                  </div>
                </el-col>
                <el-col :span="2">
                  <div class="item">kw</div>
                </el-col>
              </el-row>
            </el-card>
              <div class="vect_line"></div>
          </el-col> 
          <el-col :span="6">
            <el-card class="box-card" shadow="never" v-for="powerWind in powerWinds">
              <div class="clearfix text_primary" property="name">
                <i class="el-icon-edit"></i>
                <div>风力发电</div>
              </div>
              <el-row :gutter="0" class="text_item">
                <el-col :span="6" >
                  <div class="item">
                    电流
                  </div>
                  <div class="item">
                    电压
                  </div>
                  <div class="item">
                    功率
                  </div>
                </el-col>
                <el-col :span="14" class="text_right">
                  <div class="item">
                    <span class="text_data">{{ (powerWind.windCurrent/5).toFixed(1) }}</span>
                  </div>
                  <div class="item">
                    <span class="text_data">{{ powerWind.windVoltage.toFixed(1) }}</span>
                  </div>
                  <div class="item">
                    <span class="text_data">{{ (powerWind.windPower/5).toFixed(2) }}</span>
                  </div>
                </el-col>
                <el-col :span="4" class="text_right">
                  <div class="item">
                    A
                  </div>
                  <div class="item">
                    V
                  </div>
                  <div class="item">
                    kw
                  </div>
                </el-col>
              </el-row>
            </el-card>
              <div class="vect_line"></div>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card" shadow="never" v-for="powerGen in powerGens">
              <div class="clearfix text_primary" property="name">
                <i class="el-icon-edit"></i>
                <div>大电网购电</div>
              </div>
              <el-row :gutter="0" class="text_item">
                <el-col :span="6" >
                  <div class="item">
                    电流
                  </div>
                  <div class="item">
                    电压
                  </div>
                  <div class="item">
                    功率
                  </div>
                </el-col>
                <el-col :span="14" class="text_right">
                  <div class="item">
                    <span class="text_data">{{ powerGen.genCurrent.toFixed(1) }}</span>
                    <!-- <span class="text_data">{{ (powerGen.genCurrent+Math.floor(Math.random() * 10)).toFixed(2) }}</span> -->
                  </div>
                  <div class="item">
                    <span class="text_data">{{ powerGen.genVoltage.toFixed(1) }}</span>
                    <!-- <span class="text_data">{{ (powerGen.genVoltage+Math.floor(Math.random() * 10)).toFixed(2) }}</span> -->
                  </div>
                  <div class="item">
                    <span class="text_data">{{ powerGen.genPower.toFixed(2) }}</span>
                    <!-- <span class="text_data">{{ (powerGen.genPower+Math.floor(Math.random() * 10)).toFixed(2) }}</span> -->
                  </div>
                </el-col>
                <el-col :span="4" class="text_right">
                  <div class="item">
                    A
                  </div>
                  <div class="item">
                    V
                  </div>
                  <div class="item">
                    kw
                  </div>
                </el-col>
              </el-row>
            </el-card>
              <div class="vect_line"></div>
          </el-col>
          <el-col :span="6">
            <el-card class="box-card" shadow="never" v-for="powerGen in powerGens">
              <div class="clearfix text_primary" property="name">
                <i class="el-icon-edit"></i>
                <div>燃料发电机</div>
              </div>
              <el-row :gutter="0" class="text_item">
                <el-col :span="6" >
                  <div class="item">
                    电流
                  </div>
                  <div class="item">
                    电压
                  </div>
                  <div class="item">
                    功率
                  </div>
                </el-col>
                <el-col :span="14" class="text_right">
                  <div class="item">
                    <span class="text_data">{{ (powerGen.genCurrent+1.2).toFixed(1) }}</span>
                    <!-- <span class="text_data">{{ (powerGen.genCurrent+Math.floor(Math.random() * 10)).toFixed(2) }}</span> -->
                  </div>
                  <div class="item">
                    <span class="text_data">{{ (powerGen.genVoltage+1.7).toFixed(1) }}</span>
                    <!-- <span class="text_data">{{ (powerGen.genVoltage+Math.floor(Math.random() * 10)).toFixed(2) }}</span> -->
                  </div>
                  <div class="item">
                    <span class="text_data">{{ ((powerGen.genCurrent+1.2)*(powerGen.genVoltage+1.7)).toFixed(2) }}</span>
                    <!-- <span class="text_data">{{ (powerGen.genPower+Math.floor(Math.random() * 10)).toFixed(2) }}</span> -->
                  </div>
                </el-col>
                <el-col :span="4" class="text_right">
                  <div class="item">
                    A
                  </div>
                  <div class="item">
                    V
                  </div>
                  <div class="item">
                    kw
                  </div>
                </el-col>
              </el-row>
            </el-card>
              <div class="vect_line"></div>
          </el-col>
        </el-row>

        <el-row><div class="mian_line"></div></el-row>   <!-- 主线条 -->

        <el-row :gutter="24" type="flex" justify="space-around">  <!-- 第二排卡牌 -->
          <el-col :span="6">
            <div class="vect_line"></div>
            <el-card class="box-card" shadow="never" >
              <div class="clearfix text_primary" property="name">
                <i class="el-icon-edit"></i>
                <div> 储能电池</div>
              </div>
              <el-row :gutter="0" class="text_item">
                <el-col :span="10" >
                  <div class="item">剩余电量</div>
                  <div class="item">功率</div>
                </el-col>
                <el-col :span="12">
                  <div class="item">
                    <span class="text_data">{{(powerwindtotal/5000+Math.floor(Math.random() * 10)+Math.random()).toFixed(2)}}</span>
                  </div>
                  <div class="item">
                    <span class="text_data">{{(powerpvtotal/5000+Math.floor(Math.random() * 10)+Math.random()).toFixed(2)}}</span>
                  </div>
                </el-col>
                <el-col :span="2">
                  <div class="item">kwh</div>
                  <div class="item">kw</div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="6">
            <div class="vect_line"></div>
            <el-card class="box-card" shadow="never" >
              <div class="clearfix text_primary" property="name">
                <i class="el-icon-edit"></i>
                <div>民用负载</div>
              </div>
              <el-row :gutter="0" class="text_item">
                <el-col :span="8" >
                  <div class="item">用电量</div>
                  <div class="item">功率</div>
                </el-col>
                <el-col :span="14">
                  <div class="item">
                    <span class="text_data">{{(powerwindtotal/1522+Math.floor(Math.random() * 10)+Math.random()).toFixed(2)}}</span>
                  </div>
                  <div class="item">
                    <span class="text_data">{{(powerpvtotal/2002+Math.floor(Math.random() * 10)+Math.random()).toFixed(2)}}</span>
                  </div>
                </el-col>
                <el-col :span="2">
                  <div class="item">kwh</div>
                  <div class="item">kw</div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
          <el-col :span="6">
            <div class="vect_line"></div>
            <el-card class="box-card" shadow="never" >
              <div class="clearfix text_primary" property="name">
                <i class="el-icon-edit"></i>
                <div> 工业负载</div>
              </div>
              <el-row :gutter="0" class="text_item">
                <el-col :span="8" >
                  <div class="item">用电量</div>
                  <div class="item">功率</div>
                </el-col>
                <el-col :span="14">
                  <div class="item">
                    <span class="text_data">{{(powerwindtotal/1001+Math.floor(Math.random() * 10)+Math.random()).toFixed(2)}}</span>
                  </div>
                  <div class="item">
                    <span class="text_data">{{(powerpvtotal/1001+Math.floor(Math.random() * 10)+Math.random()).toFixed(2)}}</span>
                  </div>
                </el-col>
                <el-col :span="2">
                  <div class="item">kwh</div>
                  <div class="item">kw</div>
                </el-col>
              </el-row>
            </el-card>
          </el-col>
        </el-row>
      </el-col>
      <el-col :span="6">
      
      </el-col>
    </el-row>
  </div>
</template>


<script>
// 导入需要使用的函数
import { gensData,pvsData,windsData,pvtotalData,windtotalData } from "@/api/microgrid/test";

export default {
  name: "Index",
  data() {
    return {
      names: [
        '大电网购电', '发电机'
      ],
      powerGens: [], // 存储gen数据的数组
      powerPvs: [], // 存储pv数据的数组
      powerWinds: [], // 存储wind数据的数组
      powerpvtotal: '',
      powerwindtotal: '',

      switch_button: true,

      timer: null,
    };
  },
  mounted() {
    // 发起请求，获取数据
    this.powerdata();
    this.powertotaldata();
    // setInterval(this.powerdata, 5000); // 定时器
    setInterval(() => {
      this.powerdata();
      
      // 实时更新总发电量数据
      this.powerpvtotal = this.powerpvtotal + this.powerPvs[0].pvPower;
      this.powerwindtotal = this.powerwindtotal + this.powerWinds[0].windPower;

      console.log(this.powerwindtotal);
    }, 5000);
  },
  methods:{
    // 获取数据
    powerdata() {

      // 获取gen数据
      gensData().then(response => {
        response.forEach(data => {
          this.powerGens = [data];
        });
      })
      .catch(error => {
        console.error(error);
      });

      // 获取pv数据
      pvsData().then(response => {
        response.forEach(data => {
          this.powerPvs = [data];
        });
      })
      .catch(error => {
        console.error(error);
      });

      // 获取wind数据
      windsData().then(response => {
        response.forEach(data => {
          this.powerWinds  = [data];
        });
      })
      .catch(error => {
        console.error(error);
      });
    },

    // 获取total数据
    powertotaldata() {
      pvtotalData().then(response => {
        this.powerpvtotal = response;
      })
      .catch(error => {
        console.error(error);
      });

      windtotalData().then(response => {
        this.powerwindtotal = response;
      })
      .catch(error => {
        console.error(error);
      });
    }
  }
};
</script>


<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }

  .text {
    font-size: 14px;
  }

  .text_primary {
    font-weight: bold;
    text-align: center;
    color: #ffffff;
    margin-bottom: 20px;
    cursor: pointer;
  }

  .text_item {
    font-size: 12px;
    color: #0c3a67;
  }
  
  .text_data {
    font-size: 16px;
    font-weight: bold;
    color: #ffffff;
  }

  .item {
    height: 20px;
    margin-bottom: 15px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    font-size: 16px;
    width: 180px;
    height: 200px;
    background-color: #64adf7;
    border-radius: 20px;
    margin: auto;
    text-align: center;
  }

  .box-card:hover {
    font-size: 20px;
    background-color: #2b85df;
    border-radius: 30px;
  }

  body > .el-container {
    margin-bottom: 40px;
    margin: auto;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }

  .vect_line {
    border: 3px solid gray;
    height: 70px;
    width: 0;
    margin: auto;
  }
  .mian_line {
    border: 3px solid gray;
    height:0;
    border-radius: 30px;
  }
  .text_right {
    text-align: right;
  }
  .centered-text {
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .title-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100h;
  }
  .title {
    text-align: center;
    font-size: 24px;
    font-weight: bold;
  }
}
</style>