<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="终端编号" prop="terminalNo">
        <el-select v-model="queryParams.terminalNo" placeholder="请选择终端类型" @change="handleTerminalSelectChange">
          <el-option
          v-for="option in options"
          :key="option.value"
          :label="option.label"
          :value="option.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="设备类型" prop="deviceNo">
        <el-select v-model="queryParams2.deviceNo" placeholder="请选择设备类型" @change="handleDeviceSelectChange">
          <el-option
          v-for="option in options2"
          :key="option.value"
          :label="option.label"
          :value="option.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="位置信息" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入终端位置信息"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['microgrid:terminals:add']"
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
          v-hasPermi="['microgrid:terminals:edit']"
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
          v-hasPermi="['microgrid:terminals:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['microgrid:terminals:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="terminalsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="60" align="center" />
      <!-- <el-table-column label="序号" width="55" align="center" prop="id" /> -->
      <el-table-column label="序号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="终端编号" align="center" prop="terminalNo" />
      <el-table-column label="设备编号" align="center" prop="deviceNo"/>
      <el-table-column label="终端状态" align="center" prop="terminalStatus">
        <template slot-scope="scope">
          <span>{{ getStatusText(scope.row.terminalStatus) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="位置信息" align="center" prop="location" />
      <el-table-column label="安装日期" align="center" prop="installationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.installationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['microgrid:terminals:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['microgrid:terminals:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改终端表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="终端编号" prop="terminalNo">
          <el-input v-model="form.terminalNo" placeholder="请输入终端编号" />
        </el-form-item>
        <el-form-item label="设备编号" prop="deviceNo">
          <el-input v-model="form.deviceNo" placeholder="请输入设备编号" />
        </el-form-item>
        <el-form-item label="位置信息" prop="location">
          <el-input v-model="form.location" placeholder="请输入终端位置信息" />
        </el-form-item>
        <el-form-item label="安装日期" prop="installationDate">
          <el-date-picker clearable
            v-model="form.installationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择安装日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTerminalsByType, listTerminals, getTerminals, delTerminals, addTerminals, updateTerminals } from "@/api/microgrid/terminals";
import { listDevicesByType } from "@/api/microgrid/devices";

export default {
  name: "Terminals",
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
      // 终端表表格数据
      terminalsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        terminalNo: null,
        deviceNo: null,
        terminalStatus: null,
        location: null,
        installationDate: null
      },
      // 查询参数
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        terminalNo: null,
        deviceNo: null,
        terminalStatus: null,
        location: null,
        installationDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options: [  // 选择器选项的数据源
        { label: '风力发电设备终端', value: 'wind' },
        { label: '光伏发电设备终端', value: 'pv' },
        { label: '燃机发电设备终端', value: 'gen' },
        { label: '储能设备终端', value: 'energy' }
      ],
      options2: [  // 选择器选项的数据源
        { label: '风力发电设备', value: 'wind' },
        { label: '光伏发电设备', value: 'pv' },
        { label: '燃机发电设备', value: 'gen' },
        { label: '储能设备', value: 'energy' }
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询终端表列表 */
    getList() {
      this.loading = true;
      this.queryParams.terminalNo = null;
      this.queryParams2.deviceNo = null;
      listTerminals(this.queryParams).then(response => {
        this.terminalsList = response.rows;
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
        deviceNo: null,
        terminalStatus: null,
        location: null,
        installationDate: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
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
      this.title = "添加终端表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTerminals(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改终端表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTerminals(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTerminals(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除终端表编号为"' + ids + '"的数据项？').then(function() {
        return delTerminals(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('microgrid/terminals/export', {
        ...this.queryParams
      }, `terminals_${new Date().getTime()}.xlsx`)
    },
    /** 显示终端状态 */
    getStatusText(terminalStatus) {
      switch (terminalStatus) {
        case 1:
          return '正常';
        case 0:
          return '停用';
        case -1:
          return '故障';
        default:
          return '';
      }
    },
    /** 处理选择器 */
    handleTerminalSelectChange() {
      // const selectedValue = this.selectedValue;
      this.loading = true;
      listTerminalsByType(this.queryParams).then(response => {
        this.terminalsList = response.rows;
        this.total = response.total;
        this.loading = false;
      })
      .catch(error => {
        console.error(error);
      });
    },
    /** 处理选择器 */
    handleDeviceSelectChange() {
      // const selectedValue = this.selectedValue;
      this.loading = true;
      listDevicesByType(this.queryParams2).then(response => {
        this.devicesList = response.rows;
        this.total = response.total;
        this.loading = false;
      })
      .catch(error => {
        console.error(error);
      });
    }
  }
};
</script>
