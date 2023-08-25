<template>
  <div class="app-container">
    <div class="title-container">
      <h1 class="title">微电网日前优化调度决策结果报表</h1>
    </div>
      <div class="echart" id="mychart" :style="myChartStyle"></div>
    <el-divider /><!-- 分割线 -->
    <h1 class="title">调度执行记录</h1>
    <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
      <el-table-column label="序号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发电机" align="center" prop="Generator" />
      <el-table-column label="调度功率/kw" align="center" prop="Dispatched Power" />
      <el-table-column label="目标功率/kw" align="center" prop="Target Power" />
      <el-table-column label="调度结果" align="center" prop="Dispatch Result" />
      <el-table-column label="备注" align="center" prop="Remarks" width="220" />
      <el-table-column label="调度时间" align="center" prop="date" width="180">
        <!-- <template slot-scope="scope">
            <span>{{ parseTime(scope.row.timestamp, '{y}-{m}-{d}') }}</span>
          </template> -->
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['microgrid:history:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['microgrid:history:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getGenList" /> -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getGenList" />

    <!-- 添加或修改发电机数据表对话框 -->
    <!-- <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="终端外键" prop="terminalNo">
            <el-input v-model="form.terminalNo" placeholder="请输入终端外键" />
          </el-form-item>
          <el-form-item label="时间戳" prop="timestamp">
            <el-date-picker clearable
              v-model="form.timestamp"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择时间戳">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="电流" prop="genCurrent">
            <el-input v-model="form.genCurrent" placeholder="请输入电流" />
          </el-form-item>
          <el-form-item label="电压" prop="genVoltage">
            <el-input v-model="form.genVoltage" placeholder="请输入电压" />
          </el-form-item>
          <el-form-item label="功率" prop="genPower">
            <el-input v-model="form.genPower" placeholder="请输入功率" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog> -->
  </div>
</template>

<script>
import { listHistory, getHistory, delHistory, addHistory, updateHistory } from "@/api/microgrid/history";
import * as echarts from "echarts";


export default {
  name: "History",
  data() {
    return {

      myChart: {},
      myChartStyle: {
        width: "100%",
        height: "400px"
      },
      xAxisData: ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"],
      netLoadData: [
        1240.73, 1410.65, 1570.35, 1690.34, 1760.12, 1690.34, 1630.98, 1560.87, 1460.56, 1350.57, 1250.67, 1240.73,
        1250.67, 1350.57, 1420.12, 1580.32, 2010.34, 1890.53, 1780.55, 1680.24, 1460.56, 1330.67, 1240.73, 1230.65
      ],
      exchangeData: [
        1050.45, 1050.35, 1050.76, 1050.34, 1030.65, 1000.24, 1000.35, 1000.56, 900.45, 850.34, 850.34, 850.67,
        840.34, 840.23, 250.76, 500.34, 600.65, 900.68, 900.86, 400.23, 100.55, 0, 1000.45, 900.45
      ],
      unit2Data: [
        0, 0, 60.35, 180, 180, 60, 140, 140, 100, 80, 0, 0,
        0, 60.69, 200.24, 250.56, 620.65, 480.65, 440.75, 290.45, 380.65, 360.34, 0, 0
      ],
      energyStorageData: [
        0, 0, 0, 0, 80.34, 160.28, 40.64, 0, 0, 0, 0, 0,
        0, 0, 500.32, 380.23, 380.64, 60.32, 0, 500.32, 500.89, 480.01, 0, 0
      ],
      unit4Data: [
        500.45, 480.23, 470.78, 470.75, 460.78, 450.93, 450.98, 450.92, 460.23, 450.85, 440.89, 440.70,
        450.26, 460.63, 470.93, 470.95, 470.62, 470.92, 470.72, 470.32, 470.67, 480.78, 470.86, 470.23
      ],



      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 发电机数据表表格数据
      historyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 12,
        terminalNo: "gen",
        timestamp: null,
        genCurrent: null,
        genVoltage: null,
        genPower: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options: [  // 选择器选项的数据源
        { label: '发电机启动', value: 'wind' },
        { label: '发电机停机', value: 'pv' },
        { label: '处理故障', value: 'handle' },
        { label: '调度处理', value: 'diaodu' },
        { label: '调节功率', value: 'power' },
        { label: '设备检修', value: 'jianxiu' }
      ],
      showGen: true,
      showPv: false,
      showWind: false,
      showEnergy: false,
      showMg: false,
      showLoad: false,
      tableData: [

        { Generator: 'gen_001', date: '2023-05-01 08:00:00', 'Dispatched Power': 50, 'Target Power': 45, 'Dispatch Result': '成功', Remarks: '针对峰值负荷进行调节' },
        { Generator: 'gen_002', date: '2023-05-01 10:30:00', 'Dispatched Power': 75, 'Target Power': 80, 'Dispatch Result': '成功', Remarks: '提供紧急备用电力' },
        { Generator: 'energy_001', date: '2023-05-01 08:00:00', 'Dispatched Power': 90, 'Target Power': 100, 'Dispatch Result': '成功', Remarks: '调整储能电池' },
        { Generator: 'mg_001', date: '2023-05-01 08:00:00', 'Dispatched Power': 65, 'Target Power': 70, 'Dispatch Result': '成功', Remarks: '平衡供需关系' },

        { Generator: 'gen_001', date: '2023-05-02 08:00:00', 'Dispatched Power': 40, 'Target Power': 40, 'Dispatch Result': '成功', Remarks: '优化发电组合' },
        { Generator: 'gen_002', date: '2023-05-02 08:00:00', 'Dispatched Power': 50, 'Target Power': 50, 'Dispatch Result': '成功', Remarks: '提供紧急备用电力' },
        { Generator: 'energy_001', date: '2023-05-02 08:00:00', 'Dispatched Power': 40, 'Target Power': 35, 'Dispatch Result': '成功', Remarks: '平衡电网负荷的峰谷差异' },
        { Generator: 'mg_001', date: '2023-05-02 08:00:00', 'Dispatched Power': 95, 'Target Power': 100, 'Dispatch Result': '成功', Remarks: '调节太阳能波动' },

        { Generator: 'gen_001', date: '2023-05-03 08:00:00', 'Dispatched Power': 40, 'Target Power': 40, 'Dispatch Result': '成功', Remarks: '提供基础负荷' },
        { Generator: 'gen_002', date: '2023-05-03 08:00:00', 'Dispatched Power': 40, 'Target Power': 40, 'Dispatch Result': '成功', Remarks: '提供紧急备用电力' },
        // { Generator: 'energy_001', date: '2023-05-03 08:00:00', 'Dispatched Power': 75, 'Target Power': 80, 'Dispatch Result': '成功', Remarks: '满足峰值需求' },
        // { Generator: 'mg_001', date: '2023-05-03 08:00:00', 'Dispatched Power': 90, 'Target Power': 90, 'Dispatch Result': '成功', Remarks: '满足增加的需求' }

      ]
    };
  },
  created() {
    this.getGenList();
  },
  mounted() {
    // this.initDate(); //数据初始化
    this.drawChart();
  },
  methods: {
    drawChart() {
      this.myChart = echarts.init(document.getElementById("mychart"));

      // 计算净负荷和四个机组功率的总和
      const sum = this.netLoadData.reduce((a, b) => a + b, 0) || 0;
      const max = Math.max(sum, ...this.exchangeData, ...this.unit2Data, ...this.energyStorageData, ...this.unit4Data);

      // 调整净负荷和四个机组功率的数据，使其总和等于最大值
      const netLoadAdjusted = this.netLoadData.map(value => value + (max - sum));
      const exchangeAdjusted = this.exchangeData.map(value => value + (max - sum));
      const unit2Adjusted = this.unit2Data.map(value => value + (max - sum));
      const energyStorageAdjusted = this.energyStorageData.map(value => value + (max - sum));
      const unit4Adjusted = this.unit4Data.map(value => value + (max - sum));


      const option = {
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow"
          },
          formatter: (params) => {
            const time = params[0].axisValue; // 横轴时间值
            let content = time + "时<br/>";
            params.forEach((param) => {
              const seriesName = param.seriesName; // 系列名称
              const value = param.value; // 系列值
              content += seriesName + ": " + value + " kW<br/>";
            });
            // content += "总输出: " + value + " kW<br/>";
            return content;
          }
        },
        legend: {
          top: 10,
          data: ["净负荷", "储能充放电功率", "与电网交换功率", "发电机1出力", "发电机2出力"]
        },
        grid: {
          top: 80,
          bottom: 50,
          left: 70,
          right: 40
        },
        xAxis: {
          type: "category",
          data: this.xAxisData,
          axisLabel: {
            interval: 0,
            rotate: 45
          },
          boundaryGap: false
        },
        yAxis: [
          {
            type: "value",
            name: "功率/kw",
            axisLine: {
              show: true
            },
            axisTick: {
              show: true
            },
            splitLine: {
              show: true
            },
            min: 0,
            max: 2500
          }
        ],
        series: [
          {
            name: "净负荷",
            type: "line",
            data: netLoadAdjusted,
            smooth: true,
            lineStyle: {
              type: "dashed"
            },
            yAxisIndex: 0
          },
          {
            name: "发电机2出力",
            type: "bar",
            stack: "总量",
            data: unit2Adjusted,
            barWidth: "30%",
            itemStyle: {
              color: '#CEBBDF'
            }
          },
          {
            name: "发电机1出力",
            type: "bar",
            stack: "总量",
            data: unit4Adjusted,
            barWidth: "30%",
            itemStyle: {
              color: '#A8D580'
            }
          },
          {
            name: "与电网交换功率",
            type: "bar",
            stack: "总量",
            data: exchangeAdjusted,
            barWidth: "30%",
            itemStyle: {
              color: '#F7BF7C'
            }
          },
          {
            name: "储能充放电功率",
            type: "bar",
            stack: "总量",
            data: energyStorageAdjusted,
            barWidth: "30%",
            itemStyle: {
              color: '#bddcdc'
            }
          }
        ]
      };

      this.myChart.setOption(option);

      // Resize chart when window size changes
      window.addEventListener("resize", () => {
        this.myChart.resize();
      });
    },




    /** 查询发电机数据表列表 */
    getGenList() {
      this.loading = true;
      this.queryParams.terminalNo = "gen";
      listHistory(this.queryParams).then(response => {
        this.historyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        terminalNo: null,
        timestamp: null,
        genCurrent: null,
        genVoltage: null,
        genPower: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getGenList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加发电机数据表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getHistory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改发电机数据表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateHistory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getGenList();
            });
          } else {
            addHistory(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getGenList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除发电机数据表编号为"' + ids + '"的数据项？').then(function () {
        return delHistory(ids);
      }).then(() => {
        this.getGenList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('microgrid/history/export', {
        ...this.queryParams
      }, `history_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.echart {
    margin-top: 20px;
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
.chart-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
