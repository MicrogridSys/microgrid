<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="操作类型" prop="location">
          <el-select v-model="queryParams.deviceNo" placeholder="请选择操作类型" @change="handleSelectChange">
            <el-option
            v-for="option in options"
            :key="option.value"
            :label="option.label"
            :value="option.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['microgrid:history:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['microgrid:history:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['microgrid:history:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['microgrid:history:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getGenList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="operationrecords" @selection-change="handleSelectionChange">
        <el-table-column label="序号" align="center" prop="id" />
        <el-table-column label="操作人员" align="center" prop="operator"/>
        <el-table-column label="操作类型" align="center" prop="action" />
        <el-table-column label="操作详情" align="center" prop="details" />
        <el-table-column label="操作时间" align="center" prop="date" width="180">
          <!-- <template slot-scope="scope">
            <span>{{ parseTime(scope.row.timestamp, '{y}-{m}-{d}') }}</span>
          </template> -->
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['microgrid:history:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['microgrid:history:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getGenList"
      />
  
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
  
  export default {
    name: "History",
    data() {
      return {
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
          pageSize: 10,
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
        operationrecords: [
            { id: '1',  date: '2023-05-01 10:00:00', operator: '周明',   action: '启动发电机',           details: '发电机1已成功启动' },
            { id: '2',  date: '2023-05-01 11:30:00', operator: '刘宇',   action: '切换供电模式',         details: '从微电网切换至主电网供电' },
            { id: '3',  date: '2023-05-02 09:15:00', operator: '刘勇',   action: '检修储能电池',         details: '更换储能电池组中的故障单体' },
            { id: '4',  date: '2023-05-03 14:20:00', operator: '陈涛',   action: '调整负荷功率',         details: '将负荷功率降低10%' },
            { id: '5',  date: '2023-05-04 13:45:00', operator: '杨华',   action: '重启微电网控制系统',   details: '系统重启完成，恢复正常运行' },
            { id: '6',  date: '2023-05-05 08:30:00', operator: '吴军',   action: '更换风力发电设备',     details: '将老旧风力发电设备更换为新型设备' },
            { id: '7',  date: '2023-05-06 16:10:00', operator: '周明',   action: '处理故障报警',         details: '检修发电机2的冷却系统' },
            { id: '8',  date: '2023-05-07 12:00:00', operator: '孙明',   action: '升级微电网监控软件',   details: '完成微电网监控软件的升级工作' },
            { id: '9',  date: '2023-05-08 09:50:00', operator: '周明', action: '调整光伏发电设备角度', details: '优化光伏发电设备的太阳能吸收效果' },
            { id: '10', date: '2023-05-09 14:30:00', operator: '郑十', action: '处理故障报警',         details: '修复储能电池充电过程中的过热问题' },
        ]
      };
    },
    created() {
      this.getGenList();
    },
    methods: {
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
        this.single = selection.length!==1
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
        this.$modal.confirm('是否确认删除发电机数据表编号为"' + ids + '"的数据项？').then(function() {
          return delHistory(ids);
        }).then(() => {
          this.getGenList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
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
  