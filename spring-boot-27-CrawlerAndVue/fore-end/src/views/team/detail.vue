<template>
  <div class="app-container">
    <div><h1>{{ team.name }}</h1></div>
    <div><h2>{{ team.uuid }}</h2></div>
    <div v-for="video in team.videos" >
      <h3>{{ video.name }}</h3>
      <d-player :options="dpOptions(video.id)"/>
    </div>
    <div v-for="picture in team.pictures" :key="picture.id">
      <h3>{{ picture.name }}</h3>
      <el-image
        style="max-width: 500px"
        :src="'http://localhost:8080/picture/'+picture.id"
      />
    </div>

  </div>
</template>

<script>
import { getTeam } from '@/api/team'
import VueDPlayer from 'vue-dplayer'
import 'vue-dplayer/dist/vue-dplayer.css'
export default {
  components: {
    'd-player': VueDPlayer
  },
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
    },
    dpOptions(vid){
      return {
        video: {
          url: 'http://localhost:8080/video/'+vid
        },
        autoplay: false,
        contextmenu: [
          {
            text: 'GitHub*自定义右键菜单',
            link: 'https://github.com/MoePlayer/vue-dplayer'
          }
        ],
        highlight: [
          {
            time: 5,
            text: '这是第 5 秒,这里没有亮点'
          }
        ]
      }
    }
  },
  computed:{

  }
}
</script>
