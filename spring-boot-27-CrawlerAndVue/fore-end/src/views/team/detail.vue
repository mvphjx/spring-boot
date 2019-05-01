<template>
  <div class="app-container">
    <div><h1>{{ team.name }}</h1></div>
    <div><h2>{{ team.uuid }}</h2></div>
    <div v-for="video in team.videos" :key="video.id">
      <h3>{{ video.name }}</h3>
      <video :src="'http://localhost:8080/video/'+video.id" style="max-width: 100%" controls="controls">
        您的浏览器不支持 video 标签。
      </video>
    </div>
    <div v-for="picture in team.pictures" :key="picture.id">
      <h3>{{ picture.name }}</h3>
      <el-image
        style="max-width: 500px"
        :src="'http://localhost:8080/picture/'+picture.id"
        :fit="fit"
      />
    </div>

  </div>
</template>

<script>
import { getTeam } from '@/api/team'
export default {
  data() {
    return {
      team: {}
    }
  },
  created() {
    const uuid = this.$route.params && this.$route.params.uuid
    this.fetchData(uuid)
  },
  methods: {
    fetchData(uuid) {
      getTeam(uuid).then(response => {
        this.team = response.data
      })
    }
  }
}
</script>
