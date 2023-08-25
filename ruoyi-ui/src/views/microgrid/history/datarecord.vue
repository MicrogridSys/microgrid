<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="设备类型" prop="location">
        <el-select v-model="queryParams.deviceNo" placeholder="请选择设备类型" @change="handleSelectChange">
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

    <el-table v-loading="loading" :data="historyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id">
       
      </el-table-column>
      <el-table-column label="终端编号" align="center" prop="terminalNo" />
      <el-table-column label="电流/A" align="center" prop="genCurrent">
        <template slot-scope="scope">
          <span>{{ scope.row.genCurrent.toFixed(1) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电压/V" align="center" prop="genVoltage">
        <template slot-scope="scope">
          <span>{{ scope.row.genVoltage.toFixed(1) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="功率/kw" align="center" prop="genPower">
        <template slot-scope="scope">
          <span>{{ scope.row.genPower.toFixed(2) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生成日期" align="center" prop="timestamp" width="180">
        <template slot-scope="scope">
          <!-- <span>{{ parseTime(scope.row.timestamp) }}</span> -->
          <span>{{ parseTime(scope.row.timestamp, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
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
      </el-table-column> -->
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
        { label: '风力发电设备', value: 'wind' },
        { label: '光伏发电设备', value: 'pv' },
        { label: '燃机发电设备', value: 'gen' },
        { label: '储能设备', value: 'energy' }
      ],
      showGen: true,
      showPv: false,
      showWind: false,
      showEnergy: false,
      showMg: false,
      showLoad: false
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
