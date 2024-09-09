<template>
  <div id="app">
    <el-row :gutter="10">
      <el-col :span="6" v-for="(board, index) in hotBoards" :key="index">
        <hot-search-board
            :title="board.title"
            :icon="board.icon"
            :type="board.type"
        />
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import HotSearchBoard from "/src/components/HotSearchBoard.vue";
import baiduIcon from "/src/assets/icons/baidu-icon.svg";
import douyinIcon from "/src/assets/icons/douyin-icon.svg";

const hotBoards = ref([
  {
    title: "百度",
    icon: baiduIcon,
    type: "baidu",
  },
  {
    title: "抖音",
    icon: douyinIcon,
    type: "douyin",
  },
]);
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background: #f8f9fa;
  min-height: 100vh;
  padding: 0;
}
</style>