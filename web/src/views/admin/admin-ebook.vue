<template>
  <a-layout>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-table
              :columns="columns"
              :row-key="record => record.id"
              :data-source="ebooks"
              :pagination="pagination"
              :loading="loading"
              @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
              <a-button type="primary">
                编辑
              </a-button>
              <a-button type="danger">
                删除
              </a-button>
          </a-space>
        </template>
      </a-table>

    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue';
  import axios from 'axios';

  export default defineComponent({
    name: 'AdminEbook',
    setup() {
      const ebooks = ref();
      const pagination = ref({
        current: 1,
        pageSize: 2,
        total: 0
      });
      const loading = ref(false);

      const columns = [
        {
          title: '封面',
          dataIndex: 'cover',
          slots: { customRender: 'cover' }
        },
        {
          title: '名称',
          dataIndex: 'name'
        },
        {
          title: '分类一',
          key: 'category1Id',
          dataIndex: 'category1Id'
        },
        {
          title: '分类二',
          dataIndex: 'category2Id'
        },
        {
          title: '文档数',
          dataIndex: 'docCount'
        },
        {
          title: '阅读数',
          dataIndex: 'viewCount'
        },
        {
          title: '点赞数',
          dataIndex: 'voteCount'
        },
        {
          title: 'Action',
          key: 'action',
          slots: { customRender: 'action' }
        }
      ];

      /**
       * 数据查询
       **/
      const handleQuery = (params: any) => {
        loading.value = true;
        axios.get("/ebook/list", params).then((response) => {
          loading.value = false;
          const data = response.data;
          ebooks.value = data.content;
            // 重置分页按钮
            pagination.value.current = params.page;
        });
      };

      /**
       * 表格点击页码时触发
       */
      const handleTableChange = (pagination: any) => {
        console.log("看看自带的分页参数都有啥：" + pagination);
        handleQuery({
          page: pagination.current,
          size: pagination.pageSize
        });
      };
      //
      // // -------- 表单 ---------
      // /**
      //  * 数组，[100, 101]对应：前端开发 / Vue
      //  */
      // const categoryIds = ref();
      // const ebook = ref();
      // const modalVisible = ref(false);
      // const modalLoading = ref(false);
      // const handleModalOk = () => {
      //   modalLoading.value = true;
      //   ebook.value.category1Id = categoryIds.value[0];
      //   ebook.value.category2Id = categoryIds.value[1];
      //   axios.post("/ebook/save", ebook.value).then((response) => {
      //     modalLoading.value = false;
      //     const data = response.data; // data = commonResp
      //     if (data.success) {
      //       modalVisible.value = false;
      //
      //       // 重新加载列表
      //       handleQuery({
      //         page: pagination.value.current,
      //         size: pagination.value.pageSize,
      //       });
      //     } else {
      //       message.error(data.message);
      //     }
      //   });
      // };
      //
      // /**
      //  * 编辑
      //  */
      // const edit = (record: any) => {
      //   modalVisible.value = true;
      //   ebook.value = Tool.copy(record);
      //   categoryIds.value = [ebook.value.category1Id, ebook.value.category2Id]
      // };
      //
      // /**
      //  * 新增
      //  */
      // const add = () => {
      //   modalVisible.value = true;
      //   ebook.value = {};
      // };
      //
      // const handleDelete = (id: number) => {
      //   axios.delete("/ebook/delete/" + id).then((response) => {
      //     const data = response.data; // data = commonResp
      //     if (data.success) {
      //       // 重新加载列表
      //       handleQuery({
      //         page: pagination.value.current,
      //         size: pagination.value.pageSize,
      //       });
      //     } else {
      //       message.error(data.message);
      //     }
      //   });
      // };
      //
      // const level1 =  ref();
      // let categorys: any;
      // /**
      //  * 查询所有分类
      //  **/
      // const handleQueryCategory = () => {
      //   loading.value = true;
      //   axios.get("/category/all").then((response) => {
      //     loading.value = false;
      //     const data = response.data;
      //     if (data.success) {
      //       categorys = data.content;
      //       console.log("原始数组：", categorys);
      //
      //       level1.value = [];
      //       level1.value = Tool.array2Tree(categorys, 0);
      //       console.log("树形结构：", level1.value);
      //
      //       // 加载完分类后，再加载电子书，否则如果分类树加载很慢，则电子书渲染会报错
      //       handleQuery({
      //         page: 1,
      //         size: pagination.value.pageSize,
      //       });
      //     } else {
      //       message.error(data.message);
      //     }
      //   });
      // };
      //
      // const getCategoryName = (cid: number) => {
      //   // console.log(cid)
      //   let result = "";
      //
      //   categorys.forEach((item: any) => {
      //     if (item.id === cid) {
      //       // return item.name; // 注意，这里直接return不起作用
      //       result = item.name;
      //     }
      //   });
      //   return result;
      // };

      onMounted(() => {
        handleQuery({});
      });

      return {
        ebooks,
        pagination,
        columns,
        loading,
        handleTableChange,
      }
    }
  });
</script>

<style scoped>
  img {
    width: 50px;
    height: 50px;
  }
</style>