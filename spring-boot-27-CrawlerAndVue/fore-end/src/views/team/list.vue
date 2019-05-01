<template>
  <div class="app-container">
    <!--查询条件-->
    <div class="filter-container">
      回车搜索：
      <el-input v-model="listQuery.name" placeholder="队伍名" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
    </div>
    <!--表格-->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="队伍名">
        <template slot-scope="scope">
          <!--页面跳转，打开详情页-->
          <router-link :to="'/team/detail/'+scope.row.uuid" class="link-type">
            {{ scope.row.name }}
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="UUID" width="300" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.uuid }}</span>
        </template>
      </el-table-column>
      <el-table-column label="图片数" width="95" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.picture_num }}</span>
        </template>
      </el-table-column>
      <el-table-column label="视频数" width="95" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.video_num }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="状态" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="fetchData"
    />

  </div>
</template>

<script>
import { getList } from '@/api/team'
import Pagination from '@/components/Pagination'

export default {
  components: { Pagination },
  filters: {
    statusFilter(status) {
      const statusMap = {
        success: 'success',
        working: 'gray',
        danger: 'danger'
      };
      return statusMap[status]
    }
  },
  data() {
    return {
      list: null,
      listLoading: true,
      total: 0,
      listQuery: {
        page: 1,
        limit: 10,
        name: undefined,
        sort: '-id'
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true;
      getList(this.listQuery).then(response => {
        this.list = response.data.items;
        this.total = response.data.total;
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.fetchData()
    },
  }
}
</script>
